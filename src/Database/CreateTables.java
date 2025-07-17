package Database;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTables {

    //This can be called from anywhere to create tables - used on project startup.
    public static void create() {
        System.out.println(">> CreateTables started...");
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();

            // Create schema (if not exists)
            try {
                stmt.executeUpdate("CREATE SCHEMA wellnessapp AUTHORIZATION wellnessapp");
                System.out.println("Schema 'wellnessapp' created.");
            } catch (Exception e) {
                System.out.println("Schema 'wellnessapp' might already exist. Continuing...");
            }

            // Drop tables (reverse order - to not get FK conflicts)
            try { stmt.executeUpdate("DROP TABLE wellnessapp.feedback"); } 
            catch (Exception e) { System.out.println("Feedback table might not exist."); }

            try { stmt.executeUpdate("DROP TABLE wellnessapp.appointment"); } 
            catch (Exception e) { System.out.println("Appointment table might not exist."); }

            try { stmt.executeUpdate("DROP TABLE wellnessapp.counselor"); } 
            catch (Exception e) { System.out.println("Counselor table might not exist."); }

            System.out.println("Old tables dropped (if existed).");

            // Create new tables
            stmt.executeUpdate("""
                CREATE TABLE wellnessapp.counselor (
                    counselor_id VARCHAR(10) PRIMARY KEY,
                    name VARCHAR(50) NOT NULL,
                    surname VARCHAR(50) NOT NULL,
                    email VARCHAR(100) NOT NULL UNIQUE,
                    phone VARCHAR(100),
                    password VARCHAR(255) NOT NULL,
                    specialisation VARCHAR(100),
                    availability VARCHAR(100)
                )
            """);

            stmt.executeUpdate("""
                CREATE TABLE wellnessapp.appointment (
                    appointment_id VARCHAR(10) PRIMARY KEY,
                    student_number VARCHAR(10) NOT NULL,
                    counselor_id VARCHAR(10) NOT NULL,
                    date DATE NOT NULL,
                    time TIME NOT NULL,
                    status VARCHAR(20) NOT NULL DEFAULT 'pending',
                    FOREIGN KEY (counselor_id) REFERENCES wellnessapp.counselor(counselor_id)
                )
            """);

            stmt.executeUpdate("""
                CREATE TABLE wellnessapp.feedback (
                    feedback_id VARCHAR(10) PRIMARY KEY,
                    appointment_id VARCHAR(10) NOT NULL,
                    student_number VARCHAR(10) NOT NULL,
                    rating INTEGER CHECK (rating BETWEEN 1 AND 5),
                    comments VARCHAR(500),
                    FOREIGN KEY (appointment_id) REFERENCES wellnessapp.appointment(appointment_id)
                )
            """);

            System.out.println("New tables created successfully!");

        } catch (Exception e) {
            System.err.println("Error creating tables:");
            e.printStackTrace();
        }
    }

    //to run the file directly for testing
    public static void main(String[] args) {
        create();
    }
}
