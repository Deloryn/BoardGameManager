package pl.put.boardgamemanager.tournament_rental;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRentalRepository extends JpaRepository<TournamentRental, Long> {

    List<TournamentRental> findAllByTournamentId(Long tournamentId);

}