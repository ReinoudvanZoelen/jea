package entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "tbl_Team")
public class Team {

    @Id
    @GeneratedValue()
    private int id;
    private String name;
    @ManyToMany
    @JoinTable(name = "tbl_Players_In_Team", joinColumns = @JoinColumn(name = "team_Id", referencedColumnName = "Id"), inverseJoinColumns = @JoinColumn(name = "player_Id", referencedColumnName = "Id"))
    private List<Player> Players;

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
        return Players;
    }

    public void setPlayers(List<Player> players) {
        this.Players = players;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
