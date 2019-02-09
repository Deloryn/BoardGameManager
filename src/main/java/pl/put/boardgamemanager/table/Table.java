package pl.put.boardgamemanager.table;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "tables", schema = "public", catalog = "postgres")
public class Table {
    private int tableId;
    private short numberOfSits;

    @Id
    @Column(name = "table_id")
    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    @Basic
    @Column(name = "number_of_sits")
    public short getNumberOfSits() {
        return numberOfSits;
    }

    public void setNumberOfSits(short numberOfSits) {
        this.numberOfSits = numberOfSits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return tableId == table.tableId &&
                numberOfSits == table.numberOfSits;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId, numberOfSits);
    }
}
