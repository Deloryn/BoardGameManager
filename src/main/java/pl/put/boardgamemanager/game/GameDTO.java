package pl.put.boardgamemanager.game;

import java.sql.Timestamp;

public class GameDTO {

    private Long id;

    private String name;

    private String publisher;

    private Short minPlayers;

    private Short maxPlayers;

    private Timestamp avgTime;

    private String description;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Short getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(Short minPlayers) {
        this.minPlayers = minPlayers;
    }

    public Short getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Short maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Timestamp getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(Timestamp avgTime) {
        this.avgTime = avgTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
