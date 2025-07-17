/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package student.wellness.m2;

/**
 *
 * @author mabuz
 */

import View.MainNavigation.MainNavigation;
import Database.CreateTables;
import Database.DatabaseInitialiser;


public class StudentWellnessM2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         try {
        // 1. Optional: Create tables if not existing 
        //Database.CreateTables.main(null); 

        // 2. Populate sample data if tables are empty
        DatabaseInitialiser.main(null); // Calling main method directly to use internal getConnection() method.
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(null,
            "Database setup failed:\n" + e.getMessage(),
            "Startup Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        return; // Prevent app from continuing
    }

    // 3. Launch UI if database setup was successful - open MainNavigation
        
        java.awt.EventQueue.invokeLater(() -> {
            new MainNavigation().setVisible(true);
        });
    }
}