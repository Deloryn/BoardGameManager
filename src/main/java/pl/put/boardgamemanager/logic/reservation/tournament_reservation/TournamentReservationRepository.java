package pl.put.boardgamemanager.logic.reservation.tournament_reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.boardgamemanager.model.reservation.tournament_reservation.TournamentReservation;

import java.util.List;

public interface TournamentReservationRepository extends JpaRepository<TournamentReservation, Long> {

    List<TournamentReservation> findAllByTournamentId(Long tournamentId);

}
