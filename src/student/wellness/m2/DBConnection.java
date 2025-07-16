/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.wellness.m2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mabuz
 */
public class DBConnection {
    private static final String URL = "jdbc:derby:DerbyDB/Wellness_Management_db;create=true";
    private static final String USER = "wellnessapp";
    private static final String PASSWORD = "app";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
