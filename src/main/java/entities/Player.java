package entities;

import entities.Team;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.net.URI;

import javax.persistence.*;
import javax.ws.rs.core.Link;

@Entity(name = "tbl_Player")
public class Player {

    @Id
    @GeneratedValue()
    private int id;
    private String fullName;
    private String emailAddress;
    private String password;
    private String role;
    @ManyToOne
    @JsonBackReference
    private Team team;

    // Hateoas
    @Transient
    private Link self;

    public Player() {
    }

    public Player(String fullName, String emailAddress, String password) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.role = "NONE";
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

    public Link getLink() {
        return this.self;
    }

    public void setLink(Link link) {
        this.self = link;
    }
}
