package pl.put.boardgamemanager.model.reservation;

import javax.persistence.*;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "reservations", schema = "public", catalog = "postgres")
@DiscriminatorColumn(name = "type")
public abstract class Reservation {

    @SequenceGenerator(name = "reservations_seq", sequenceName = "reservations_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservations_seq")
    @Column(name = "id", nullable = false)
    protected Long id;

    @Column(name = "tableid", nullable = false)
    protected Long tableId;

    @Column(name = "tutorid")
    protected Long tutorId;

    public void setId(Long id) { this.id = id; }

    public Long getId() { return id; }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getTutorId() { return tutorId; }

    public void setTutorId(Long tutorId) { this.tutorId = tutorId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(tableId, that.tableId) &&
                Objects.equals(tutorId, that.tutorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tableId, tutorId);
    }

}
