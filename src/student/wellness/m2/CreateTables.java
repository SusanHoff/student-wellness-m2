/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.wellness.m2;

/**
 *
 * @author susan
 * Class for creating tables
 */
import java.sql.Connection;
import java.sql.Statement;

public class CreateTables {

    public static void main(String[] args) {
       
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            try{
            stmt.executeUpdate("CREATE SCHEMA wellnessapp AUTHORIZATION wellnessapp");
            System.out.println("Schema 'wellnessapp' created.");
            } catch (Exception e) {
        System.out.println("Schema 'wellnessapp' might already exist. Continuing...");
    }
            // Create counselor table
            stmt.executeUpdate("""
                CREATE TABLE wellnessapp.counselor (
                    counselor_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                    name VARCHAR(50) NOT NULL,
                    surname VARCHAR(50) NOT NULL,
                    email VARCHAR(100) NOT NULL UNIQUE,
                    phone VARCHAR(100),
                    password VARCHAR(255) NOT NULL,
                    specialisation VARCHAR(100),
                    availability VARCHAR(100)
                )
            """);

            // Create appointment table
            stmt.executeUpdate("""
                CREATE TABLE wellnessapp.appointment (
                    appointment_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                    student_number INTEGER NOT NULL,
                    counselor_id INTEGER NOT NULL,
                    date DATE NOT NULL,
                    time TIME NOT NULL,
                    status VARCHAR(20) NOT NULL DEFAULT 'pending',
                    FOREIGN KEY (counselor_id) REFERENCES counselor(counselor_id)
                )
            """);

            // Create feedback table
            stmt.executeUpdate("""
                CREATE TABLE wellnessapp.feedback (
                    feedback_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                    appointment_id INTEGER NOT NULL,
                    student_number INTEGER NOT NULL,
                    rating INTEGER CHECK (rating BETWEEN 1 AND 5),
                    comments VARCHAR(500),
                    FOREIGN KEY (appointment_id) REFERENCES appointment(appointment_id)
                )
            """);

            System.out.println("Tables created successfully!");

        } catch (Exception e) {
            System.err.println("Error creating tables:");
            e.printStackTrace();
        }
    }
}

