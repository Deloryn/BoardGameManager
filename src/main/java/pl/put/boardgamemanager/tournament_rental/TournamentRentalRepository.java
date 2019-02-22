package pl.put.boardgamemanager.tournament_rental;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TournamentRentalRepository extends JpaRepository<TournamentRental, Long> {

    List<TournamentRental> findAllByTournamentId(Long tournamentId);

}