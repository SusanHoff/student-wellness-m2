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
        // Adjust for Derby or your DB type
        String url = "jdbc:postgresql://localhost:5432/wellness_app";
        String user = "your_db_username";
        String password = "your_db_password";
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    public void addCounselor(Counselor c) throws Exception {
        String sql = "INSERT INTO counselors (name, surname, email, phone, password, specialisation, available) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getName());
            stmt.setString(2, c.getSurname());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getPhone());
            stmt.setString(5, c.getPassword());
            stmt.setString(6, c.getSpecialisation());
            stmt.setString(7, c.isAvailable());
            stmt.executeUpdate();
        }
    }

    public void updateCounselor(Counselor c) throws Exception {
        String sql = "UPDATE counselors SET name=?, surname=?, email=?, phone=?, password=?, specialisation=?, available=? WHERE id=?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getName());
            stmt.setString(2, c.getSurname());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getPhone());
            stmt.setString(5, c.getPassword());
            stmt.setString(6, c.getSpecialisation());
            stmt.setString(7, c.isAvailable());
            stmt.setInt(8, c.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteCounselor(int id) throws Exception {
        String sql = "DELETE FROM counselors WHERE id=?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Counselor> getAllCounselors() throws Exception {
        List<Counselor> list = new ArrayList<>();
        String sql = "SELECT * FROM counselors";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Counselor c = new Counselor(
                    rs.getInt("id"),
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
