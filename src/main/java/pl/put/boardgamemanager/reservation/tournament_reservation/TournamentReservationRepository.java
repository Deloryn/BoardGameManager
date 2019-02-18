package pl.put.boardgamemanager.reservation.tournament_reservation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentReservationRepository extends JpaRepository<TournamentReservation, Long> {

}
