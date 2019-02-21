package pl.put.boardgamemanager.model.copy;

import pl.put.boardgamemanager.dto.DTO;
import pl.put.boardgamemanager.dto.CopyDTO;
import pl.put.boardgamemanager.model.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "gamecopies", schema = "public", catalog = "postgres")
public class Copy implements Model {

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
        Copy that = (Copy) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(gameId, that.gameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameId);
    }

    public void updateParamsFrom(DTO dto) {
        if(dto instanceof CopyDTO) {
            this.setGameId(((CopyDTO) dto).getGameId());
        }
    }

    public CopyDTO toDTO() {
        CopyDTO dto = new CopyDTO();

        dto.setId(id);
        dto.setGameId(gameId);

        return dto;
    }

}
