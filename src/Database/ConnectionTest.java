package Database;

import Database.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 *
 * @author susan
 */
public class ConnectionTest {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("Connected successfully!");
            conn.close();
        } catch (Exception e) {
            System.err.println("Failed to connect:");
            e.printStackTrace();
        }
    }
}
