package pl.put.boardgamemanager.logic.rental.tournament_rental;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.boardgamemanager.model.rental.tournament_rental.TournamentRental;

import java.util.List;

public interface TournamentRentalRepository extends JpaRepository<TournamentRental, Long> {

    List<TournamentRental> findAllByTournamentId(Long tournamentId);

}