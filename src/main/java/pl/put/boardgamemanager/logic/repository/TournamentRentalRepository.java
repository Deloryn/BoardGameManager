package pl.put.boardgamemanager.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.boardgamemanager.model.TournamentRental;

import java.util.List;

public interface TournamentRentalRepository extends JpaRepository<TournamentRental, Long> {

    List<TournamentRental> findAllByTournamentId(Long tournamentId);

}