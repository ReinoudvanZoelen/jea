package entities;

import entities.Team;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity(name = "tbl_Player")
public class Player {

    @Id
    @GeneratedValue()
    private int id;

    @NotEmpty(message = "Please enter a valid name for this player.")
    private String fullName;

    @Email(message = "Please enter a valid email address for this player.")
    private String emailAddress;

    @NotEmpty
    @Size(min = 8, message = "Please enter a valid password for this player. A password must be at least 8 characters long.")
    private String password;

    @NotEmpty
    private String role;
    
    @ManyToOne
    @JsonBackReference
    private Team team;

    public Player() {
    }

    public Player(int id, String fullName, String emailAddress, String password, String role, Team team) {
        this.id = id;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.role = role;
        this.team = team;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", fullName='" + getFullName() + "'" + ", emailAddress='"
                + getEmailAddress() + "'" + ", password='" + getPassword() + "'" + ", role='" + getRole() + "'"
                + ", team='" + getTeam() + "'" + "}";
    }

}
