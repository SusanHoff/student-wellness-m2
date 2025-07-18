package Controller;

/**
 *
 * @author susan
 */

import DataAccessObject.CounselorDAO;
import Model.Counselor;
import java.util.ArrayList;
import java.util.List;

public class CounselorController {
   //Collection to store counselors in 
  private List<Counselor> counselorList = new ArrayList<>();
  
  private CounselorDAO dao = new CounselorDAO();
  
  //Constructor to add seed data to counselorList
      public CounselorController() {
        
        counselorList.add(new Counselor("c1234", "John", "Smith", "john.smith@example.com", "0711234567", "pass123", "Anxiety", "Mon–Fri 09:00–17:00"));
        counselorList.add(new Counselor("c1235", "Lebo", "Nkosi", "lebo.nkosi@example.com", "0722345678", "lebo2024", "Depression", "Mon–Wed 08:00–16:00"));
        counselorList.add(new Counselor("c1236", "Maya", "Naidoo", "maya.naidoo@example.com", "0733456789", "mayaPass", "Academic Stress", "Tue–Fri 10:00–14:00"));
        counselorList.add(new Counselor("c1237", "Peter", "Jones", "peter.jones@example.com", "0744567890", "pjones!", "Career Guidance", "Mon,Wed,Fri 12:00–18:00"));
        counselorList.add(new Counselor("c1238", "Fatima", "Khan", "fatima.khan@example.com", "0755678901", "fKhan#2024", "Trauma", "Mon–Thu 09:00–13:00"));
    }

  
      //method to add counselor (based on fields created in counselor class (in Model)).
    public void addCounselor(String id, String name, String surname, String email, String phone,
                             String password, String specialisation, String available) {
        
        try {
            Counselor c = new Counselor(id, name, surname, email, phone, password, specialisation, available);
            //adds to list
            counselorList.add(c);
            //adds to database
            dao.addCounselor(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCounselor(String id, String name, String surname, String email, String phone,
                                String password, String specialisation, String available) {
        //To update Arraylist
         for (int i = 0; i < counselorList.size(); i++) {
            if (counselorList.get(i).getId().equals(id)) {
                Counselor updated = new Counselor(id, name, surname, email, phone, password, specialisation, available);
                counselorList.set(i, updated);
                break;
            }
        }
        //to update database
        try {
            Counselor c = new Counselor(id, name, surname, email, phone, password, specialisation, available);
            dao.updateCounselor(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCounselor(String id) {
        //list
        for (int i = 0; i < counselorList.size(); i++) {
        if (counselorList.get(i).getId().equals(id)) {
            counselorList.remove(i);
            break;
        }
    }
        //database
//        counselorList.removeIf(c -> c.getId().equals(id));
//        try {
//            dao.deleteCounselor(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public List<Counselor> getAllCounselors() {
        
        try {
            //return dao.getAllCounselors();
            return counselorList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        
    }
}