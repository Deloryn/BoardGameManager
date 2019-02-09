package pl.put.boardgamemanager.tournament;

import javax.persistence.*;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tournaments", schema = "public", catalog = "postgres")
public class Tournament {
    private int tournamentId;
    private Timestamp tournamentTime;
    private Timestamp duration;
    private short maxPlayers;

    @Id
    @Column(name = "tournament_id")
    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    @Basic
    @Column(name = "tournament_time")
    public Timestamp getTournamentTime() {
        return tournamentTime;
    }

    public void setTournamentTime(Timestamp tournamentTime) {
        this.tournamentTime = tournamentTime;
    }

    @Basic
    @Column(name = "duration")
    public Timestamp getDuration() {
        return duration;
    }

    public void setDuration(Timestamp duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "max_players")
    public short getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(short maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament that = (Tournament) o;
        return tournamentId == that.tournamentId &&
                maxPlayers == that.maxPlayers &&
                Objects.equals(tournamentTime, that.tournamentTime) &&
                Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tournamentId, tournamentTime, duration, maxPlayers);
    }
}
