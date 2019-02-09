package pl.put.boardgamemanager.reservation;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "reservations", schema = "public", catalog = "postgres")
public class Reservation {
    private int tableId;
    private String type;

    @Id
    @Column(name = "table_table_id")
    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableTableId) {
        this.tableId = tableTableId;
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
        Reservation that = (Reservation) o;
        return tableId == that.tableId &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId, type);
    }
}
