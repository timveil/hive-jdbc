package veil.hdp.hive.jdbc;

import org.apache.hive.jdbc.HiveDriver;
import veil.hdp.hive.jdbc.test.AbstractConnectionTest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class OriginalHiveDriverBinaryKerberosConnectionTest extends AbstractConnectionTest {

    /*
        -Dsun.security.krb5.debug=true
        -Djava.security.krb5.conf=C:\ProgramData\MIT\Kerberos5\krb5.ini
        -Djavax.security.auth.useSubjectCredsOnly=false

        must be kinited first locally

        must have JCE installed in proper directory <java_home>/jre/lib/security

     */

    @Override
    public Connection createConnection(String host) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", "hive");

        String url = "jdbc:hive2://" + host + ":10000/tests;principal=hive/" + host + "@HDP.LOCAL";

        return new HiveDriver().connect(url, properties);
    }
}
