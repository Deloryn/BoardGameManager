package pl.put.boardgamemanager.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TournamentDTO extends DTO {

    private Long id;

    private Long gameId;

    private LocalDateTime startTime;

    private Integer duration;

    private Short maxPlayers;

    private List<Long> tableIds;

    private List<Long> copyIds;

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

    public List<Long> getTableIds() {
        return tableIds;
    }

    public void setTableIds(List<Long> tableIds) {
        this.tableIds = tableIds;
    }

    public List<Long> getCopyIds() {
        return copyIds;
    }

    public void setCopyIds(List<Long> copyIds) {
        this.copyIds = copyIds;
    }
}