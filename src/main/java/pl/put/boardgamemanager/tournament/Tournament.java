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

    public void updateParams(Tournament tournament) {
        this.setGameId(tournament.getGameId());
        this.setDuration(tournament.getDuration());
        this.setTime(tournament.getTime());
        this.setMaxPlayers(tournament.getMaxPlayers());
    }

    public static Tournament fromDTO(TournamentDTO dto) {

        if(dto == null) return null;

        Tournament tournament = new Tournament();

        tournament.setId(dto.getId());
        tournament.setGameId(dto.getGameId());
        tournament.setDuration(dto.getDuration());
        tournament.setTime(dto.getTime());
        tournament.setMaxPlayers(dto.getMaxPlayers());

        return tournament;
    }

    public static TournamentDTO toDTO(Tournament tournament) {

        if(tournament == null) return null;

        TournamentDTO dto = new TournamentDTO();

        dto.setGameId(tournament.getGameId());
        dto.setDuration(tournament.getDuration());
        dto.setTime(tournament.getTime());
        dto.setMaxPlayers(tournament.getMaxPlayers());

        return dto;
    }

}
