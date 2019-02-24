package pl.put.boardgamemanager.tournament_reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentReservationRepository extends JpaRepository<TournamentReservation, Long> {

    List<TournamentReservation> findAllByTournamentId(Long tournamentId);

}
