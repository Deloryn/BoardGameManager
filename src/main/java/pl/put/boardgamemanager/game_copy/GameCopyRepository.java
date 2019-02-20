package pl.put.boardgamemanager.game_copy;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameCopyRepository extends JpaRepository<GameCopy, Long> {

    List<GameCopy> findAllByGameId(Long gameId);

}
