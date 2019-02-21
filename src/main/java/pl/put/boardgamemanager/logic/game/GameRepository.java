package pl.put.boardgamemanager.logic.game;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.boardgamemanager.model.game.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
