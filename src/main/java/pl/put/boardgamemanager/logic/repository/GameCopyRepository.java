package pl.put.boardgamemanager.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.boardgamemanager.model.GameCopy;

import java.util.List;

public interface GameCopyRepository extends JpaRepository<GameCopy, Long> {

    List<GameCopy> findAllByGameId(Long gameId);

}
