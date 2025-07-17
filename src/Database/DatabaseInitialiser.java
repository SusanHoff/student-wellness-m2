package Database;

/**
 *
 * @author susan
 * Class for adding 5 entries to each table
 */
import Database.DBConnection;
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
            INSERT INTO wellnessapp.counselor (counselor_id, name, surname, email, phone, password, specialisation, availability)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            String[][] counselors = {
                {"c1234", "John", "Smith", "john.smith@example.com", "0711234567", "pass123", "Anxiety", "Mon–Fri 09:00–17:00"},
                {"c1235", "Lebo", "Nkosi", "lebo.nkosi@example.com", "0722345678", "lebo2024", "Depression", "Mon–Wed 08:00–16:00"},
                {"c1236","Maya", "Naidoo", "maya.naidoo@example.com", "0733456789", "mayaPass", "Academic Stress", "Tue–Fri 10:00–14:00"},
                {"c1237","Peter", "Jones", "peter.jones@example.com", "0744567890", "pjones!", "Career Guidance", "Mon,Wed,Fri 12:00–18:00"},
                {"c1238","Fatima", "Khan", "fatima.khan@example.com", "0755678901", "fKhan#2024", "Trauma", "Mon–Thu 09:00–13:00"}
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
            INSERT INTO wellnessapp.appointment (appointment_id, student_number, counselor_id, date, time, status)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            Object[][] appointments = {
                {"a1234","s1234", "c1234",  java.sql.Date.valueOf("2025-07-18"), java.sql.Time.valueOf("09:30:00"), "pending"},
                {"a1235","s1235", "c1235", java.sql.Date.valueOf("2025-07-19"), java.sql.Time.valueOf("10:00:00"), "confirmed"},
                {"a1236","s1236", "c1236", java.sql.Date.valueOf("2025-07-20"), java.sql.Time.valueOf("11:30:00"), "cancelled"},
                {"a1237","s1237", "c1237", java.sql.Date.valueOf("2025-07-21"), java.sql.Time.valueOf("12:45:00"), "confirmed"},
                {"a1238","s1238", "c1235", java.sql.Date.valueOf("2025-07-22"), java.sql.Time.valueOf("10:15:00"), "pending"}
            };

            for (Object[] a : appointments) {
                ps.setString(1, (String)a[0]);
                ps.setString(2, (String) a[1]);
                ps.setString(3, (String) a[2]);
                ps.setDate(4, (java.sql.Date) a[3]);
                ps.setTime(5, (java.sql.Time) a[4]);
                ps.setString(6, (String) a[5]);
                ps.executeUpdate();
            }
        }
    }

    private static void insertSampleFeedback(Connection conn) throws Exception {
        String sql = """
            INSERT INTO wellnessapp.feedback (feedback_id, appointment_id, student_number, rating, comments)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            Object[][] feedback = {
                {"f1234", "a1234","s1234", 4,"The counselor was helpful and understanding."},
                {"f1235", "a1235","s1235", 5,"Excellent experience, very professional."},
                {"f1236", "a1238","s1236", 2,"Counselor seemed distracted."},
                {"f1237", "a1237","s1237", 5,"Got practical advice, very thankful."},
                {"f1238", "a1236","s1238", 3, "It was okay, still unsure about the next steps."}
            };

            for (Object[] f : feedback) {
                ps.setString(1, (String) f[0]);
                ps.setString(2, (String) f[1]);
                ps.setString(3, (String)f[2]); 
                ps.setInt(4, (int) f[3]);
                ps.setString(5, (String) f[4]);
                ps.executeUpdate();
            }
        }
    }
}