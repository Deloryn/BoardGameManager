package pl.put.boardgamemanager.boardgamecopy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "boardgame_copies", schema = "public", catalog = "postgres")
public class BoardgameCopy {
    private int copyId;

    @Id
    @Column(name = "copy_id")
    public int getCopyId() {
        return copyId;
    }

    public void setCopyId(int copyId) {
        this.copyId = copyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardgameCopy that = (BoardgameCopy) o;
        return copyId == that.copyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(copyId);
    }
}
