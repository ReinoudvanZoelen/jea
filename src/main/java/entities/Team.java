package entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "tbl_Team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @ManyToMany
    private List<Player> Players;
    @OneToMany
    private List<TeamScore> TeamScores;

    public Team() {
    }

    // Getters and Setters

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public List<Player> getPlayers() {
        return Players;
    }

    public void setPlayers(List<Player> players) {
        Players = players;
    }

    public List<TeamScore> getTeamScores() {
        return TeamScores;
    }

    public void setTeamScores(List<TeamScore> teamScores) {
        TeamScores = teamScores;
    }
}