package libraries.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static Connection conn;
    private static DataReader data = new DataReader("dbconfig.properties");


    //---Connect libraries
    public static Connection getMyConnection_SQLServer_JDBC() throws SQLException, ClassNotFoundException {

        return getSQLServerConnection_JDBC();
    }

    public static Connection getMyConnection_SQLServer_JTDS() throws SQLException, ClassNotFoundException {

        return getSQLServerConnection_JDTS();
    }

    public static Connection getMyConnection_MySQL() throws SQLException, ClassNotFoundException {

        return getMySQLConnection();
    }

    public static Connection getMyConnection_OracleXE() throws SQLException, ClassNotFoundException {

        return getOracleConnection();
    }

    //---

    /**
     * Make connection to DriverManager
     */
    public static final Connection getConn(String URL, String UserName, String Password) {

        try {
            conn = DriverManager.getConnection(URL, UserName, Password);
        } catch (SQLException e) {
            System.out.println("- Error --- Connnection failed");
        }
        return conn;
    }

    /**
     * MySQL connection
     */
    public static Connection getMySQLConnection(String hostName,
                                                String portConn,
                                                String dbName,
                                                String userName,
                                                String passWord)
            throws ClassNotFoundException, SQLException {

        String connectionURL = "jdbc:mysql://"
                                + hostName + "/"
                                + dbName
                                + "?characterEncoding=UTF-8&useSSL=false";

        // Connection
        conn = getConn(connectionURL, userName, passWord);
        System.out.println(connectionURL);
        System.out.println("--- MySQL database connected ---");
        return conn;
    }

    public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {

        String hostName = data.getProperty("mysql.hostname");
        String portConn = data.getProperty("mysql.port");
        String dbName = data.getProperty("mysql.dbname");
        String userName = data.getProperty("mysql.user");
        String passWord = data.getProperty("mysql.password");

        return getMySQLConnection(hostName, portConn, dbName, userName, passWord);
    }

    /**
     * SQL-Server connection by JDTS
     */
    public static Connection getSQLServerConnection_JDTS(String hostName,
                                                         String sqlInstanceName,
                                                         String database,
                                                         String userName,
                                                         String passWord,
                                                         String portConn)
                            throws ClassNotFoundException, SQLException {

        /*
         * Syntax: "jdbc:jtds:sqlserver://localhost:1433/testsimple;instance=SQLEXPRESS"
         */
        String connectionURL = "jdbc:jtds:sqlserver://"
                                + hostName + ":"
                                + portConn + "/"
                                + database
                                + ";instance="
                                + sqlInstanceName;

        conn = getConn(connectionURL, userName, passWord);
        System.out.println(connectionURL);
        System.out.println("--- SQLSERVER JTDS connected ---");
        return conn;
    }

    public static Connection getSQLServerConnection_JDTS()
                            throws SQLException, ClassNotFoundException {

        String hostName = data.getProperty("sqlserver.hostname");
        String portConn = data.getProperty("sqlserver.port");
        String sqlInstanceName = data.getProperty("sqlserver.instancename");
        String dbName = data.getProperty("sqlserver.dbname");
        String userName = data.getProperty("sqlserver.user");
        String passWord = data.getProperty("sqlserver.password");

        return getSQLServerConnection_JDTS( hostName,
                                            sqlInstanceName,
                                            dbName,
                                            userName,
                                            passWord,
                                            portConn);
    }

    /**
     * SQL-Server connection by JDBC
     */
    public static Connection getSQLServerConnection_JDBC(String hostName,
                                                         String portConn,
                                                         String sqlInstanceName,
                                                         String database,
                                                         String userName,
                                                         String passWord)
                            throws ClassNotFoundException, SQLException {

        /*
         * Syntax: "jdbc:sqlserver://ServerIp:1433;instance=SQLEXPRESS;databaseName=testmydb"
         */
        String connectionURL = "jdbc:sqlserver://"
                                + hostName + ":"
                                + portConn + ";"
                                + sqlInstanceName + ";"
                                + "databaseName="
                                + database;

        conn = getConn(connectionURL, userName, passWord);
        System.out.println(connectionURL);
        System.out.println("--- SQLSERVER JDBC connected ---");
        return conn;
    }

    public static Connection getSQLServerConnection_JDBC()
                            throws SQLException, ClassNotFoundException {

        String hostName = data.getProperty("sqlserver.hostname");
        String portConn = data.getProperty("sqlserver.port");
        String sqlInstanceName = "instance=" + data.getProperty("sqlserver.instancename");
        String dbName = data.getProperty("sqlserver.dbname");
        String userName = data.getProperty("sqlserver.user");
        String password = data.getProperty("sqlserver.password");

        return getSQLServerConnection_JDBC(hostName,
                                           portConn,
                                           sqlInstanceName,
                                           dbName,
                                           userName,
                                           password);
    }

    /**
     * Oracle connection
     */
    public static Connection getOracleConnection(String hostName,
                                                 String sid,
                                                 String userName,
                                                 String password,
                                                 String port)
                            throws ClassNotFoundException, SQLException {

        //Syntax: "jdbc:oracle:thin:@localhost:1521:db11g"
        String connectionURL = "jdbc:oracle:thin:@"
                                + hostName + ":"
                                + port + ":"
                                + sid;

        conn = getConn(connectionURL, userName, password);
        System.out.println(connectionURL);
        System.out.println("--- ORACLE database connected ---");
        return conn;
    }

    public static Connection getOracleConnection() throws SQLException,
                                                          ClassNotFoundException {
        String hostName = data.getProperty("oracle.hostname");
        String port = "1521";data.getProperty("oracle.port");
        String sid = data.getProperty("oracle.sid");
        String userName = data.getProperty("oracle.user");
        String password = data.getProperty("oracle.password");

        return getOracleConnection(hostName, sid, userName, password, port);
    }
}
