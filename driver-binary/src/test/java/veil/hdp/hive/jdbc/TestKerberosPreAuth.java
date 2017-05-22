package veil.hdp.hive.jdbc;

import veil.hdp.hive.jdbc.security.KerberosService;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class TestKerberosPreAuth extends BaseConnectionTest {

        /*
        on windows:
            -Djava.security.krb5.conf=C:\ProgramData\MIT\Kerberos5\krb5.ini

        must have JCE installed in proper directory <java_home>/jre/lib/security

     */

    @Override
    public Connection createConnection(String host) throws SQLException {
        Properties properties = new Properties();

        String url = "jdbc:hive2://" + host + ":10500/jdbc_test?authMode=KERBEROS&krb5Mode=PREAUTH&krb5ServerPrincipal=hive/hdp2.lab.local@LAB.LOCAL";

        try {
            return Subject.doAs(KerberosService.loginWithPassword("timve@LAB.LOCAL", "password", true), new PrivilegedExceptionAction<Connection>() {
                @Override
                public Connection run() throws Exception {
                    return new BinaryHiveDriver().connect(url, properties);
                }
            });
        } catch (PrivilegedActionException e) {
            throw new SQLException(e);
        } catch (LoginException e) {
            throw new SQLException(e);
        }


    }

}