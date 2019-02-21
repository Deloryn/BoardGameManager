package pl.put.boardgamemanager.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.boardgamemanager.model.TournamentReservation;

import java.util.List;

public interface TournamentReservationRepository extends JpaRepository<TournamentReservation, Long> {

    List<TournamentReservation> findAllByTournamentId(Long tournamentId);

}
