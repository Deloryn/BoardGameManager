package pl.put.boardgamemanager.logic.tournament;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.boardgamemanager.model.tournament.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

}
