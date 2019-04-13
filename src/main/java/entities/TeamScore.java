package entities;

import javax.persistence.*;

@Entity(name = "tbl_TeamScores")
public class TeamScore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @ManyToOne
    private Team Team;
    @ManyToOne
    private Match Match;
    private int Score;

    public TeamScore() {
    }

    // Getters and Setters

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Team getTeam() {
        return Team;
    }

    public void setTeam(Team team) {
        Team = team;
    }

    public Match getMatch() {
        return Match;
    }

    public void setMatch(Match match) {
        Match = match;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}
