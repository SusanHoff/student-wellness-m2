/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author susan
 */
public class Counselor {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private String specialisation;
    private String available;

    public Counselor(int id, String name, String surname, String email, String phone, String password, String specialisation, String available) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.specialisation = specialisation;
        this.available = available;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSurname(){return surname;}
    public String getEmail(){return email;}
    public String getPhone() {return phone;}
    public String getPassword() {return password;}
    public String getSpecialisation() { return specialisation; }
    public String isAvailable() { return available; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) {this.surname=surname;}
    public void setEmail(String email){this.email = email;}
    public void setPhone(String phone){this.phone=phone;}
    public void setPassword(String password){this.password = password;}
    public void setSpecialisation(String specialisation) { this.specialisation = specialisation; }
    public void setAvailable(String available) { this.available = available; }
}
