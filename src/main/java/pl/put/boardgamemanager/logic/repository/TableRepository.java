package pl.put.boardgamemanager.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.boardgamemanager.model.Table;

public interface TableRepository extends JpaRepository<Table, Long> {

}
