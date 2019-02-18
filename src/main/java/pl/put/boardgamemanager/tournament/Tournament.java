package pl.put.boardgamemanager.tournament;

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
    private Long id;

    @Column(name = "gameid", nullable = false)
    private Long gameId;

    @Column(name = "time", nullable = false)
    private Timestamp time;

    @Column(name = "duration", nullable = false)
    private Timestamp duration;

    @Column(name = "maxplayers", nullable = false)
    private Short maxPlayers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament that = (Tournament) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(time, that.time) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(maxPlayers, that.maxPlayers) &&
                Objects.equals(gameId, that.gameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, duration, maxPlayers, gameId);
    }

    public void updateParamsFrom(TournamentDTO dto) {
        this.setGameId(dto.getGameId());
        this.setDuration(dto.getDuration());
        this.setTime(dto.getTime());
        this.setMaxPlayers(dto.getMaxPlayers());
    }

    public TournamentDTO toDTO() {
        TournamentDTO dto = new TournamentDTO();

        dto.setId(id);
        dto.setGameId(gameId);
        dto.setDuration(duration);
        dto.setTime(time);
        dto.setMaxPlayers(maxPlayers);

        return dto;
    }

}
