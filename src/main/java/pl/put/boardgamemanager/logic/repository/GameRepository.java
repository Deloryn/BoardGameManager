package pl.put.boardgamemanager.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.boardgamemanager.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
