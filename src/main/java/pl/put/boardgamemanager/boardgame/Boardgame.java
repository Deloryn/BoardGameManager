package pl.put.boardgamemanager.boardgame;

import javax.persistence.*;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "boardgames", schema = "public", catalog = "postgres")
public class Boardgame {
    private int gameId;
    private String name;
    private String publisher;
    private short minPlayers;
    private short maxPlayers;
    private Timestamp avgTime;
    private String description;

    @Id
    @Column(name = "game_id")
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "publisher")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Basic
    @Column(name = "min_players")
    public short getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(short minPlayers) {
        this.minPlayers = minPlayers;
    }

    @Basic
    @Column(name = "max_players")
    public short getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(short maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    @Basic
    @Column(name = "avg_time")
    public Timestamp getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(Timestamp avgTime) {
        this.avgTime = avgTime;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boardgame boardgame = (Boardgame) o;
        return gameId == boardgame.gameId &&
                minPlayers == boardgame.minPlayers &&
                maxPlayers == boardgame.maxPlayers &&
                Objects.equals(name, boardgame.name) &&
                Objects.equals(publisher, boardgame.publisher) &&
                Objects.equals(avgTime, boardgame.avgTime) &&
                Objects.equals(description, boardgame.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, name, publisher, minPlayers, maxPlayers, avgTime, description);
    }
}
