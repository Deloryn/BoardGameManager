package pl.put.boardgamemanager.tournamentrental;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "tournament_rentals", schema = "public", catalog = "postgres")
public class TournamentRental {
    private int copyId1;

    @Id
    @Column(name = "copy_id1")
    public int getCopyId1() {
        return copyId1;
    }

    public void setCopyId1(int copyId1) {
        this.copyId1 = copyId1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TournamentRental that = (TournamentRental) o;
        return copyId1 == that.copyId1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(copyId1);
    }
}
