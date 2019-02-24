package pl.put.boardgamemanager.tournament;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    @Procedure(name = "calculatefinishtime")
    LocalDateTime calculateFinishTime(@Param("startTime") LocalDateTime startTime, @Param("duration") Integer duration);

}
