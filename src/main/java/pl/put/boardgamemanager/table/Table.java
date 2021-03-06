package pl.put.boardgamemanager.table;

import javax.persistence.*;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "tables", schema = "public", catalog = "postgres")
public class Table {

    @SequenceGenerator(name = "tables_seq", sequenceName = "tables_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tables_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "numberofsits", nullable = false)
    private Short numberOfSits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void updateParamsFrom(TableDTO dto) {
        this.setNumberOfSits(dto.getNumberOfSits());
    }

    public TableDTO toDTO() {
        TableDTO dto = new TableDTO();

        dto.setId(id);
        dto.setNumberOfSits(numberOfSits);

        return dto;
    }
}
