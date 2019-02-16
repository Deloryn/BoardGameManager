package pl.put.boardgamemanager.reservation;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "reservations", schema = "public", catalog = "postgres")
@DiscriminatorColumn(name = "tableid")
public abstract class Reservation {

    @SequenceGenerator(name = "reservations_seq", sequenceName = "reservations_seq")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservations_seq")
    @Column(name = "tableid", nullable = false)
    protected Long tableId;

    @Column(name = "type", length = 1)
    protected Character type;

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(tableId, that.tableId) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId, type);
    }

}
