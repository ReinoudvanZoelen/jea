package entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "tbl_Match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private int SittingTableNumber;
    @OneToMany
    private List<TeamScore> TeamScores;
    @ManyToOne
    private Tournament Tournament;
    private int RoundNumber;

    public Match() {
    }

    // Getters and Setters

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getSittingTableNumber() {
        return SittingTableNumber;
    }

    public void setSittingTableNumber(int sittingTableNumber) {
        SittingTableNumber = sittingTableNumber;
    }

    public List<TeamScore> getTeamScores() {
        return TeamScores;
    }

    public void setTeamScores(List<TeamScore> teamScores) {
        TeamScores = teamScores;
    }

    public Tournament getTournament() {
        return Tournament;
    }

    public void setTournament(Tournament tournament) {
        Tournament = tournament;
    }

    public int getRoundNumber() {
        return RoundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        RoundNumber = roundNumber;
    }
}