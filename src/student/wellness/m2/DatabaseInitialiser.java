/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.wellness.m2;

/**
 *
 * @author susan
 * Class for adding 5 entries to each table
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseInitialiser {

    public static void main(String[] args) {
        
        System.out.println("DatabaseInitialiser is running...");

        try (Connection conn = DBConnection.getConnection()) {

            if (isTableEmpty(conn, "counselor")) {
                insertSampleCounselors(conn);
                System.out.println("Inserted sample counselors.");
            } else {
                System.out.println("Counselor table already has data. Skipping.");
            }

            if (isTableEmpty(conn, "appointment")) {
                insertSampleAppointments(conn);
                System.out.println("Inserted sample appointments.");
            } else {
                System.out.println("Appointment table already has data. Skipping.");
            }

            if (isTableEmpty(conn, "feedback")) {
                insertSampleFeedback(conn);
                System.out.println("Inserted sample feedback.");
            } else {
                System.out.println("Feedback table already has data. Skipping.");
            }

        } catch (Exception e) {
            System.err.println("Failed to initialize database:");
            e.printStackTrace();
        }
    }

    private static boolean isTableEmpty(Connection conn, String tableName) {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM wellnessapp." + tableName)) {

            rs.next();
            return rs.getInt(1) == 0;

        } catch (Exception e) {
            System.err.println("Could not check table: " + tableName);
            e.printStackTrace();
            return false;
        }
    }

    private static void insertSampleCounselors(Connection conn) throws Exception {
        String sql = """
            INSERT INTO counselor (name, surname, email, phone, password, specialisation, availability)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            String[][] counselors = {
                {"John", "Smith", "john.smith@example.com", "0711234567", "pass123", "Anxiety", "Mon–Fri 09:00–17:00"},
                {"Lebo", "Nkosi", "lebo.nkosi@example.com", "0722345678", "lebo2024", "Depression", "Mon–Wed 08:00–16:00"},
                {"Maya", "Naidoo", "maya.naidoo@example.com", "0733456789", "mayaPass", "Academic Stress", "Tue–Fri 10:00–14:00"},
                {"Peter", "Jones", "peter.jones@example.com", "0744567890", "pjones!", "Career Guidance", "Mon,Wed,Fri 12:00–18:00"},
                {"Fatima", "Khan", "fatima.khan@example.com", "0755678901", "fKhan#2024", "Trauma", "Mon–Thu 09:00–13:00"}
            };

            for (String[] c : counselors) {
                for (int i = 0; i < c.length; i++) {
                    ps.setString(i + 1, c[i]);
                }
                ps.executeUpdate();
            }
        }
    }

    private static void insertSampleAppointments(Connection conn) throws Exception {
        String sql = """
            INSERT INTO appointment (student_number, counselor_id, date, time, status)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            Object[][] appointments = {
                {20231001, 1, java.sql.Date.valueOf("2025-07-18"), java.sql.Time.valueOf("09:30:00"), "pending"},
                {20231002, 2, java.sql.Date.valueOf("2025-07-19"), java.sql.Time.valueOf("10:00:00"), "confirmed"},
                {20231003, 3, java.sql.Date.valueOf("2025-07-20"), java.sql.Time.valueOf("11:30:00"), "cancelled"},
                {20231004, 4, java.sql.Date.valueOf("2025-07-21"), java.sql.Time.valueOf("12:45:00"), "confirmed"},
                {20231005, 5, java.sql.Date.valueOf("2025-07-22"), java.sql.Time.valueOf("10:15:00"), "pending"}
            };

            for (Object[] a : appointments) {
                ps.setInt(1, (int) a[0]);
                ps.setInt(2, (int) a[1]);
                ps.setDate(3, (java.sql.Date) a[2]);
                ps.setTime(4, (java.sql.Time) a[3]);
                ps.setString(5, (String) a[4]);
                ps.executeUpdate();
            }
        }
    }

    private static void insertSampleFeedback(Connection conn) throws Exception {
        String sql = """
            INSERT INTO feedback (appointment_id, student_number, rating, comments)
            VALUES (?, ?, ?, ?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            Object[][] feedbacks = {
                {1, 20231001, 4, "The counselor was helpful and understanding."},
                {2, 20231002, 5, "Excellent experience, very professional."},
                {3, 20231003, 2, "Counselor seemed distracted."},
                {4, 20231004, 5, "Got practical advice, very thankful."},
                {5, 20231005, 3, "It was okay, still unsure about the next steps."}
            };

            for (Object[] f : feedbacks) {
                ps.setInt(1, (int) f[0]);
                ps.setInt(2, (int) f[1]);
                ps.setInt(3, (int) f[2]);
                ps.setString(4, (String) f[3]);
                ps.executeUpdate();
            }
        }
    }
}