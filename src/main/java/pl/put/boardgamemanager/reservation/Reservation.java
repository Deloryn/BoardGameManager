package pl.put.boardgamemanager.reservation;

import pl.put.boardgamemanager.person.tutor.Tutor;
import pl.put.boardgamemanager.table.Table;

import javax.persistence.*;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "reservations", schema = "public", catalog = "postgres")
@DiscriminatorColumn(name = "tableid")
public abstract class Reservation {

    @Id
    @Column(name = "tableid", nullable = false)
    protected Long tableId;

    @OneToOne
    @JoinColumn(name = "tableid", referencedColumnName = "id", nullable = false)
    protected Table reservedTable;

    @ManyToOne
    @JoinColumn(name = "tutorid", referencedColumnName = "id")
    protected Tutor tutor;

    @Column(name = "type", length = 1)
    protected Character type;

    public Long getTableId() {
        return tableId;
    }

    public Table getReservedTable() {
        return reservedTable;
    }

    public void setReservedTable(Table table) { this.reservedTable = table; }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Tutor getTutor() { return tutor; }

    public void setTutor(Tutor tutor) { this.tutor = tutor; }

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
