package pl.put.boardgamemanager.reservation.tournament_reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TournamentReservationRepository extends JpaRepository<TournamentReservation, Long> {

    List<TournamentReservation> findAllByTournamentId(Long tournamentId);

}
