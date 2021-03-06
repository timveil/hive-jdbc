/*
 *    Copyright 2018 Timothy J Veil
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package veil.hdp.hive.jdbc.security;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ietf.jgss.*;
import veil.hdp.hive.jdbc.HiveDriverProperty;
import veil.hdp.hive.jdbc.HiveException;
import veil.hdp.hive.jdbc.utils.PlatformUtils;
import veil.hdp.hive.jdbc.utils.PrincipalUtils;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag.REQUIRED;

public final class KerberosService {

    private static final Logger log = LogManager.getLogger(KerberosService.class);
    private static final String KRB5_OID = "1.2.840.113554.1.2.2";
    private static final String KRB5_NAME_OID = "1.2.840.113554.1.2.2.1";

    private static final Oid MECHANISM = buildOid(KRB5_OID);
    private static final Oid NAME_TYPE = buildOid(KRB5_NAME_OID);

    private KerberosService() {
    }

    private static Oid buildOid(String name) {
        try {
            return new Oid(name);
        } catch (GSSException e) {
            throw new HiveException("Unable to create Oid for name [" + name + "].  This is highly unusual.", e);
        }
    }


    public static Subject getPreAuthenticatedSubject() {
        AccessControlContext preAuthContext = AccessController.getContext();
        Subject subject = Subject.getSubject(preAuthContext);

        log.debug("pre-auth subject [{}]", subject);

        return subject;
    }

    public static byte[] getToken(ServicePrincipal servicePrincipal) {

        GSSContext context = null;

        try {

            GSSManager manager = GSSManager.getInstance();
            GSSName name = manager.createName(servicePrincipal.toString(), NAME_TYPE);

            context = manager.createContext(name, MECHANISM, null, GSSContext.DEFAULT_LIFETIME);
            context.requestMutualAuth(false);

            byte[] inToken = new byte[0];

            return context.initSecContext(inToken, 0, inToken.length);
        } catch (GSSException e) {
            throw new HiveException(e);
        } finally {

            if (context != null) {
                try {
                    context.dispose();
                } catch (GSSException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }

    }

    public static Subject getSubject(Properties properties) throws LoginException {

        KerberosMode kerberosMode = KerberosMode.valueOf(HiveDriverProperty.KERBEROS_MODE.get(properties));

        log.debug("kerberos mode [{}]", kerberosMode);

        boolean debugJaas = HiveDriverProperty.JAAS_DEBUG_ENABLED.getBoolean(properties);

        if (kerberosMode == KerberosMode.PREAUTH) {
            return getPreAuthenticatedSubject();
        } else if (kerberosMode == KerberosMode.OS) {
            return loginFromOperatingSystem(debugJaas);
        } else {
            UserPrincipal userPrincipal = PrincipalUtils.parseUserPrincipal(HiveDriverProperty.USER.get(properties));

            log.debug("user principal [{}]", userPrincipal);

            if (kerberosMode == KerberosMode.KEYTAB) {
                String keyTab = HiveDriverProperty.KERBEROS_USER_KEYTAB.get(properties);

                return loginWithKeytab(userPrincipal, keyTab, debugJaas);
            } else if (kerberosMode == KerberosMode.PASSWORD) {
                String password = HiveDriverProperty.PASSWORD.get(properties);

                return loginWithPassword(userPrincipal, password, debugJaas);
            }
        }

        throw new IllegalArgumentException("kerberos mode [" + kerberosMode + "] is not supported!");


    }

    public static Subject loginWithPassword(UserPrincipal principal, String password, boolean debugJaas) throws LoginException {

        String configName = "fromPassword";

        Map<String, String> options = buildOptions(debugJaas, false, false,
                null, false, false,
                false, null, false, principal.getUser(),
                true, false, false,
                false, true);

        JaasConfiguration config = new JaasConfiguration();
        config.addAppConfigEntry(configName, PlatformUtils.getKrb5LoginModuleClassName(), REQUIRED, options);

        LoginContext context = new LoginContext(configName, null, new UsernamePasswordCallbackHandler(principal.getUser(), password), config);
        context.login();

        Subject subject = context.getSubject();

        log.debug("successfully logged in subject with password [{}]", subject);

        return subject;
    }

    public static Subject loginFromOperatingSystem(boolean debugJaas) throws LoginException {

        String configName = "fromOS";

        Map<String, String> options = buildOptions(debugJaas, false, false,
                null, false, false,
                false, null, false, null,
                true, false, false,
                false, true);

        JaasConfiguration config = new JaasConfiguration();
        config.addAppConfigEntry(configName, PlatformUtils.getOSLoginModuleClassName(), REQUIRED, options);

        LoginContext context = new LoginContext(configName, null, new NoOpCallbackHandler(), config);
        context.login();

        Subject subject = context.getSubject();

        log.debug("successfully logged in subject with OS module [{}]", subject);

        return subject;
    }

    public static Subject loginWithKeytab(UserPrincipal principal, String keyTab, boolean debugJaas) throws LoginException {


        String configName = "fromKeytab";


        Map<String, String> options = buildOptions(debugJaas, true, false,
                null, false, true,
                true, keyTab, true, principal.getUser(),
                true, false, false,
                false, true);

        JaasConfiguration config = new JaasConfiguration();
        config.addAppConfigEntry(configName, PlatformUtils.getKrb5LoginModuleClassName(), REQUIRED, options);

        LoginContext context = new LoginContext(configName, null, new NoOpCallbackHandler(), config);
        context.login();

        Subject subject = context.getSubject();

        log.debug("successfully logged in subject with keytab [{}]", subject);

        return subject;
    }

    private static Map<String, String> buildOptions(boolean debug, boolean refreshKrb5Config, boolean useTicketCache, String ticketCache,
                                                    boolean renewTGT, boolean doNotPrompt, boolean useKeyTab,
                                                    String keyTab, boolean storeKey, String principal,
                                                    boolean isInitiator, boolean useFirstPass, boolean tryFirstPass,
                                                    boolean storePass, boolean clearPass) {
        Map<String, String> options = new HashMap<>(16);
        options.put(LoginModuleConstants.DEBUG, Boolean.toString(debug));

        if (PlatformUtils.isWindows()) {
            options.put(LoginModuleConstants.DEBUG_NATIVE, Boolean.toString(debug));
        }

        options.put(LoginModuleConstants.REFRESH_KRB_5_CONFIG, Boolean.toString(refreshKrb5Config));
        options.put(LoginModuleConstants.USE_TICKET_CACHE, Boolean.toString(useTicketCache));

        if (StringUtils.isNotBlank(ticketCache)) {
            options.put(LoginModuleConstants.TICKET_CACHE, ticketCache);
        }

        options.put(LoginModuleConstants.RENEW_TGT, Boolean.toString(renewTGT));
        options.put(LoginModuleConstants.DO_NOT_PROMPT, Boolean.toString(doNotPrompt));
        options.put(LoginModuleConstants.USE_KEY_TAB, Boolean.toString(useKeyTab));

        if (StringUtils.isNotBlank(keyTab)) {
            options.put(LoginModuleConstants.KEY_TAB, keyTab);
        }

        options.put(LoginModuleConstants.STORE_KEY, Boolean.toString(storeKey));

        if (StringUtils.isNotBlank(principal)) {
            options.put(LoginModuleConstants.PRINCIPAL, principal);
        }

        options.put(LoginModuleConstants.IS_INITIATOR, Boolean.toString(isInitiator));
        options.put(LoginModuleConstants.USE_FIRST_PASS, Boolean.toString(useFirstPass));
        options.put(LoginModuleConstants.TRY_FIRST_PASS, Boolean.toString(tryFirstPass));
        options.put(LoginModuleConstants.STORE_PASS, Boolean.toString(storePass));
        options.put(LoginModuleConstants.CLEAR_PASS, Boolean.toString(clearPass));

        return options;
    }

    private static final class LoginModuleConstants {
        static final String DEBUG = "debug";
        static final String DEBUG_NATIVE = "debugNative";
        static final String DO_NOT_PROMPT = "doNotPrompt";
        static final String KEY_TAB = "keyTab";
        static final String PRINCIPAL = "principal";
        static final String USE_KEY_TAB = "useKeyTab";
        static final String STORE_KEY = "storeKey";
        static final String REFRESH_KRB_5_CONFIG = "refreshKrb5Config";
        static final String USE_TICKET_CACHE = "useTicketCache";
        static final String USE_FIRST_PASS = "useFirstPass";
        static final String TRY_FIRST_PASS = "tryFirstPass";
        static final String STORE_PASS = "storePass";
        static final String CLEAR_PASS = "clearPass";
        static final String RENEW_TGT = "renewTGT";
        static final String TICKET_CACHE = "ticketCache";
        static final String IS_INITIATOR = "isInitiator";
    }
}
