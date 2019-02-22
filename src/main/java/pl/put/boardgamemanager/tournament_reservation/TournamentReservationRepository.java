package pl.put.boardgamemanager.tournament_reservation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentReservationRepository extends JpaRepository<TournamentReservation, Long> {

}
