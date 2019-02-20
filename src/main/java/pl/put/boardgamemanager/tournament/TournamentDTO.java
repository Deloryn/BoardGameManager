package pl.put.boardgamemanager.tournament;

import java.time.LocalDateTime;

public class TournamentDTO {

    private Long id;

    private Long gameId;

    private LocalDateTime startTime;

    private Integer duration;

    private Short maxPlayers;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
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

}
