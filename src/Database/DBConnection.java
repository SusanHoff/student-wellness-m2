package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mabuz
 */
public class DBConnection {
    private static final String URL = "jdbc:derby:DerbyDB/Wellness_Management_db;create=true";
    //private static final String USER = "wellnessapp";
    //private static final String PASSWORD = "app";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
