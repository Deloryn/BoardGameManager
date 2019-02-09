package pl.put.boardgamemanager.boardgamecopy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "boardgame_copies", schema = "public", catalog = "postgres")
public class BoardgameCopy {
    private int id;

    @Id
    @Column(name = "copy_id")
    public int getId() {
        return id;
    }

    public void setId(int copyId) {
        this.id = copyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardgameCopy that = (BoardgameCopy) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
