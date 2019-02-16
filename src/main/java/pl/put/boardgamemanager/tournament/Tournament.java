package pl.put.boardgamemanager.tournament;

import pl.put.boardgamemanager.game.Game;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tournaments", schema = "public", catalog = "postgres")
public class Tournament {

    @SequenceGenerator(name = "tournaments_seq", sequenceName = "tournaments_seq")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tournaments_seq")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "time", nullable = false)
    private Timestamp time;

    @Column(name = "duration", nullable = false)
    private Timestamp duration;

    @Column(name = "maxplayers", nullable = false)
    private Short maxPlayers;

    @ManyToOne
    @JoinColumn(name = "gameid", referencedColumnName = "id", nullable = false)
    private Game game;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Timestamp getDuration() {
        return duration;
    }

    public void setDuration(Timestamp duration) {
        this.duration = duration;
    }

    public Short getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Short maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament that = (Tournament) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(time, that.time) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(maxPlayers, that.maxPlayers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, duration, maxPlayers);
    }

}
