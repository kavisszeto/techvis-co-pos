package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection conn = null;

    private static final String URL = "jdbc:mariadb://localhost:3306/smtph_db";
    private static final String USER = "Kavis";
    private static final String PWD = "Kavis1111";

    public static Connection getConnection() {

        try {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Got a static obejct 'Connection' that connected to 'smtph_db' database");
                return conn;
            } else {
                conn = DriverManager.getConnection(URL, USER, PWD);
                System.out.println("Logging in using the account and its password...");
            }
        } catch (SQLException ex) {
            System.out.println("Connection Error!");
            System.out.println(ex.toString());
            //ex.printStackTrace(); 
        }
        return conn;
    }
}
