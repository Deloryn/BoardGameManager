package pl.put.boardgamemanager.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rentals", schema = "public", catalog = "postgres")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Rental {

    @SequenceGenerator(name = "rentals_seq", sequenceName = "rentals_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rentals_seq")
    @Column(name = "id", nullable = false)
    protected Long id;

    @Column(name = "copyid", nullable = false)
    protected Long copyId;

    public void setId(Long id) { this.id = id; }

    public Long getId() { return id; }

    public void setCopyId(Long copyid) {
        this.copyId = copyid;
    }

    public Long getCopyId() {
        return copyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental that = (Rental) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(copyId, that.copyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, copyId);
    }

}
