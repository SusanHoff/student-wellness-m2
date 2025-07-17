
package Model;

/**
 *
 * @author susan
 */
import java.sql.Date;
import java.sql.Time;

public class Appointment {
    private String appointmentId;
    private String studentId;
    private String counselorId;
    private Date date;
    private Time time;
    private String status;

    public Appointment(String appointmentId, String studentId, String counselorId, Date date, Time time, String status) {
        this.appointmentId = appointmentId;
        this.studentId = studentId;
        this.counselorId = counselorId;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public String getAppointmentId() { return appointmentId; }
    public String getStudentId() { return studentId; }
    public String getCounselorId() { return counselorId; }
    public Date getDate() { return date; }
    public Time getTime() { return time; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
