package pl.put.boardgamemanager.logic.table;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.boardgamemanager.model.table.Table;

public interface TableRepository extends JpaRepository<Table, Long> {

}
