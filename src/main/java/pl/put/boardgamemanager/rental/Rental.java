package pl.put.boardgamemanager.rental;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "rentals", schema = "public", catalog = "postgres")
public class Rental {
    private int gameCopyId;
    private String type;

    @Id
    @Column(name = "boardgame_copies_copy_id")
    public int getGameCopyId() {
        return gameCopyId;
    }

    public void setGameCopyId(int boardgameCopiesCopyId) {
        this.gameCopyId = boardgameCopiesCopyId;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return gameCopyId == rental.gameCopyId &&
                Objects.equals(type, rental.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameCopyId, type);
    }
}
