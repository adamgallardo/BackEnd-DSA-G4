package edu.upc.dsa.dao.implementations;

import edu.upc.dsa.dao.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FactorySession {
    public static Session openSession() {


        Connection conn = getConnection();

        Session session = new SessionImpl(conn);

        return session;
    }
    public static Connection getConnection() {
        Connection conn = null;
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","Mazinger72");
        // mariadb Mazinger72
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/santadb", properties);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;
    }
}
