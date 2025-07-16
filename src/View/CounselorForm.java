/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.CounselorController;
import Model.Counselor;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author susan
 */
 
public class CounselorForm extends javax.swing.JFrame {

    CounselorController controller = new CounselorController();

    public CounselorForm() {
        initComponents();
        loadCounselors();
    }

    private void loadCounselors() {
        DefaultTableModel model = (DefaultTableModel) tblCounselors.getModel();
        model.setRowCount(0); // Clear previous data

        for (Counselor c : controller.getAllCounselors()) {
            model.addRow(new Object[]{
                c.getId(),
                c.getName(),
                c.getSurname(),
                c.getEmail(),
                c.getPhone(),
                "null", // hide password
                c.getSpecialisation(),
                c.isAvailable()
            });
        }
    }

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
        String name = txtName.getText();
        String surname = txtSurname.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String password = new String(txtPassword.getPassword());
        String specialisation = comboSpecialisation.getSelectedItem().toString();
        String available = checkAvailable.isSelected() ? "Yes" : "No";

        controller.addCounselor(name, surname, email, phone, password, specialisation, available);
        loadCounselors();
    }

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        int row = tblCounselors.getSelectedRow();
        if (row != -1) {
            int id = Integer.parseInt(tblCounselors.getValueAt(row, 0).toString());
            String name = txtName.getText();
            String surname = txtSurname.getText();
            String email = txtEmail.getText();
            String phone = txtPhone.getText();
            String password = new String(txtPassword.getPassword());
            String specialisation = comboSpecialisation.getSelectedItem().toString();
            String available = checkAvailable.isSelected() ? "Yes" : "No";

            controller.updateCounselor(id, name, surname, email, phone, password, specialisation, available);
            loadCounselors();
        }
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        int row = tblCounselors.getSelectedRow();
        if (row != -1) {
            int id = Integer.parseInt(tblCounselors.getValueAt(row, 0).toString());
            controller.deleteCounselor(id);
            loadCounselors();
        }
    }

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {
        txtName.setText("");
        txtSurname.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtPassword.setText("");
        comboSpecialisation.setSelectedIndex(0);
        checkAvailable.setSelected(false);
    }
}
