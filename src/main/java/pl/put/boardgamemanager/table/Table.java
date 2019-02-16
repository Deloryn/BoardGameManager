package pl.put.boardgamemanager.table;

import javax.persistence.*;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "tables", schema = "public", catalog = "postgres")
public class Table {

    @SequenceGenerator(name = "tables_seq", sequenceName = "tables_seq")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tables_seq")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "numberofsits", nullable = false)
    private Short numberOfSits;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getNumberOfSits() {
        return numberOfSits;
    }

    public void setNumberOfSits(Short numberOfSits) {
        this.numberOfSits = numberOfSits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return Objects.equals(id, table.id) &&
                Objects.equals(numberOfSits, table.numberOfSits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfSits);
    }
}
