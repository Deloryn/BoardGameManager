package pl.put.boardgamemanager.dto;

public class CopyDTO extends DTO {

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    private Long gameId;

    public Long getId() {
        return id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

}
