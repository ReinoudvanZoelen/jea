package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "tbl_Player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String FullName;
    private String EmailAddress;
    private String Password;
    // private List<Team> Teams;
    private String Role;

    public Player() {
    }

    public Player(String fullName, String emailAddress, String password, String role) {
        FullName = fullName;
        EmailAddress = emailAddress;
        Password = password;
        Role = role;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
