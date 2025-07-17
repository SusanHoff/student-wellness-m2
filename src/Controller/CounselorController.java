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
  private CounselorDAO dao = new CounselorDAO();

    public void addCounselor(String id, String name, String surname, String email, String phone,
                             String password, String specialisation, String available) {
        try {
            Counselor c = new Counselor(id, name, surname, email, phone, password, specialisation, available);
            dao.addCounselor(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCounselor(String id, String name, String surname, String email, String phone,
                                String password, String specialisation, String available) {
        try {
            Counselor c = new Counselor(id, name, surname, email, phone, password, specialisation, available);
            dao.updateCounselor(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCounselor(String id) {
        try {
            dao.deleteCounselor(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Counselor> getAllCounselors() {
        try {
            return dao.getAllCounselors();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}