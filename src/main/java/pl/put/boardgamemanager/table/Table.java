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

    public void updateParams(Table table) {
        this.setNumberOfSits(table.getNumberOfSits());
    }

    public static Table fromDTO(TableDTO dto) {

        if(dto == null) return null;

        Table table = new Table();

        table.setId(dto.getId());
        table.setNumberOfSits(dto.getNumberOfSits());

        return table;
    }

    public static TableDTO toDTO(Table table) {

        if(table == null) return null;

        TableDTO dto = new TableDTO();

        dto.setNumberOfSits(table.getNumberOfSits());

        return dto;
    }
}
