package pl.put.boardgamemanager.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.boardgamemanager.model.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

}
