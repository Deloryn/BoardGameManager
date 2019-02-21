package pl.put.boardgamemanager.logic.copy;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.boardgamemanager.model.copy.Copy;

import java.util.List;

public interface CopyRepository extends JpaRepository<Copy, Long> {

    List<Copy> findAllByGameId(Long gameId);

}
