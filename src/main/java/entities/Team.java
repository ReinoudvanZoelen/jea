package entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import entities.Player;

@Entity(name = "tbl_Team")
public class Team {

    @Id
    @GeneratedValue()
    private int id;

    @NotEmpty(message = "Please enter a valid name for this team.")
    private String name;
    
    @OneToMany(fetch = FetchType.EAGER)
    @Size(min = 2, max = 2, message = "Please enter valid players for this team. A Team consists of exactly two players.")
    @JsonManagedReference
    private List<Player> players;

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
}
