package pl.put.boardgamemanager.model;

import pl.put.boardgamemanager.dto.GameCopyDTO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "gamecopies", schema = "public", catalog = "postgres")
public class GameCopy {

    @SequenceGenerator(name = "gamecopies_seq", sequenceName = "gamecopies_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gamecopies_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "gameid", nullable = false)
    private Long gameId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCopy that = (GameCopy) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(gameId, that.gameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameId);
    }

    public void updateParamsFrom(GameCopyDTO dto) {
        this.setGameId(dto.getGameId());
    }

    public GameCopyDTO toDTO() {
        GameCopyDTO dto = new GameCopyDTO();

        dto.setId(id);
        dto.setGameId(gameId);

        return dto;
    }

}
