package entities;

import javax.persistence.*;
import javax.ws.rs.core.Link;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import entities.Player;

@Entity(name = "tbl_Team")
public class Team {

    @Id
    @GeneratedValue()
    private int id;
    private String name;
    @OneToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Player> players;

    // Hateoas
    @Transient
    private Link self;

    public Team() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Link getLink() {
        return this.self;
    }

    public void setLink(Link link) {
        this.self = link;
    }
}
