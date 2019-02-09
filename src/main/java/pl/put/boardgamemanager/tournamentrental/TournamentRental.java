package pl.put.boardgamemanager.tournamentrental;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "tournament_rentals", schema = "public", catalog = "postgres")
public class TournamentRental {
    private int gameCopyId;

    @Id
    @Column(name = "copy_id1")
    public int getGameCopyId() {
        return gameCopyId;
    }

    public void setGameCopyId(int copyId1) {
        this.gameCopyId = copyId1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TournamentRental that = (TournamentRental) o;
        return gameCopyId == that.gameCopyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameCopyId);
    }
}
