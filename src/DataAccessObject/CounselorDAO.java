/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessObject;

/**
 *
 * @author susan
 */

import Model.Counselor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CounselorDAO {
private Connection connect() throws Exception {
        // connect to embedded derby - no un or pw needed
        String url = "jdbc:derby:DerbyDB/Wellness_Management_db;create=true";
        //String user = "wellnessapp";
        //String password = "app";
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        return DriverManager.getConnection(url);
    }

    public void addCounselor(Counselor c) throws Exception {
        String sql = "INSERT INTO counselors (id, name, surname, email, phone, password, specialisation, available) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(0, c.getId());
            stmt.setString(1, c.getName());
            stmt.setString(2, c.getSurname());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getPhone());
            stmt.setString(5, c.getPassword());
            stmt.setString(6, c.getSpecialisation());
            stmt.setString(7, c.getAvailable());
            stmt.executeUpdate();
        }
    }

    public void updateCounselor(Counselor c) throws Exception {
        String sql = "UPDATE counselors SET id=?, name=?, surname=?, email=?, phone=?, password=?, specialisation=?, available=? WHERE id=?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(0, c.getId());
            stmt.setString(1, c.getName());
            stmt.setString(2, c.getSurname());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getPhone());
            stmt.setString(5, c.getPassword());
            stmt.setString(6, c.getSpecialisation());
            stmt.setString(7, c.getAvailable());
            stmt.executeUpdate();
        }
    }

    public void deleteCounselor(String id) throws Exception {
        String sql = "DELETE FROM counselors WHERE id=?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Counselor> getAllCounselors() throws Exception {
        List<Counselor> list = new ArrayList<>();
        String sql = "SELECT * FROM counselors";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Counselor c = new Counselor(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("password"),
                    rs.getString("specialisation"),
                    rs.getString("available")
                );
                list.add(c);
            }
        }
        return list;
    }
}
