package pl.put.boardgamemanager.tournament;

import pl.put.boardgamemanager.TimeEvent;
import pl.put.boardgamemanager.person.client.Client;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tournaments", schema = "public", catalog = "postgres")
public class Tournament implements TimeEvent {

    @SequenceGenerator(name = "tournaments_seq", sequenceName = "tournaments_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tournaments_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "gameid", nullable = false)
    private Long gameId;

    @Column(name = "starttime", nullable = false)
    private Timestamp startTime;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "maxplayers", nullable = false)
    private Short maxPlayers;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "tournamentparticipants",
            joinColumns = @JoinColumn(name = "tournamentid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "clientid", referencedColumnName = "id"))
    private List<Client> participants;

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

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Short getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Short maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public List<Client> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Client> participants) {
        this.participants = participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament that = (Tournament) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(maxPlayers, that.maxPlayers) &&
                Objects.equals(gameId, that.gameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, duration, maxPlayers, gameId);
    }

    public void updateParamsFrom(TournamentDTO dto) {
        this.setGameId(dto.getGameId());
        this.setDuration(dto.getDuration());
        this.setStartTime(Timestamp.valueOf(dto.getStartTime()));
        this.setMaxPlayers(dto.getMaxPlayers());
    }

    public TournamentDTO toDTO() {
        TournamentDTO dto = new TournamentDTO();

        dto.setId(id);
        dto.setGameId(gameId);
        dto.setDuration(duration);
        dto.setStartTime(startTime.toLocalDateTime());
        dto.setMaxPlayers(maxPlayers);

        return dto;
    }
}
