package entities;

import javax.persistence.*;

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

    public Player(String fullName, String emailAddress, String password) {
        this.FullName = fullName;
        this.EmailAddress = emailAddress;
        this.Password = password;
        this.Role = "NONE";
    }

    // Getters and Setters

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

    // public List<Team> getTeams() {
    // return Teams;
    // }

    // public void setTeams(List<Team> teams) {
    // Teams = teams;
    // }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
