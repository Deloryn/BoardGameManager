package pl.put.boardgamemanager.tournament;

import pl.put.boardgamemanager.DTO;

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

    private String readOnlyGameName;

    private Integer readOnlyPlayersNumber;

    public boolean validate() {
        if(gameId == null) {
            this.setErrorMessage("GameId cannot be null");
            return false;
        }
        if(startTime == null) {
            this.setErrorMessage("Start time cannot be null");
            return false;
        }
        if(duration == null) {
            this.setErrorMessage("Duration cannot be null");
            return false;
        }
        if(duration <= 0) {
            this.setErrorMessage("Duration must be greater than 0");
            return false;
        }
        if(maxPlayers == null) {
            this.setErrorMessage("Max players cannot be null");
            return false;
        }
        if(maxPlayers <= 0) {
            this.setErrorMessage("Max players must be greater than 0");
            return false;
        }

        return true;
    }

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

    public String getReadOnlyGameName() {
        return readOnlyGameName;
    }

    public void setReadOnlyGameName(String readOnlyGameName) {
        this.readOnlyGameName = readOnlyGameName;
    }

    public Integer getReadOnlyPlayersNumber() {
        return readOnlyPlayersNumber;
    }

    public void setReadOnlyPlayersNumber(Integer readOnlyPlayersNumber) {
        this.readOnlyPlayersNumber = readOnlyPlayersNumber;
    }
}
