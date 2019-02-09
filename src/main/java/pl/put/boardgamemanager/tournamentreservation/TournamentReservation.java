package pl.put.boardgamemanager.tournamentreservation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "tournament_reservations", schema = "public", catalog = "postgres")
public class TournamentReservation {
    private int tableId1;

    @Id
    @Column(name = "table_id1")
    public int getTableId1() {
        return tableId1;
    }

    public void setTableId1(int tableId1) {
        this.tableId1 = tableId1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TournamentReservation that = (TournamentReservation) o;
        return tableId1 == that.tableId1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId1);
    }
}
