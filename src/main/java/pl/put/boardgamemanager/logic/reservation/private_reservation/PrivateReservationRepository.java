package pl.put.boardgamemanager.logic.reservation.private_reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.boardgamemanager.model.reservation.private_reservation.PrivateReservation;

import java.sql.Timestamp;
import java.util.List;

public interface PrivateReservationRepository extends JpaRepository<PrivateReservation, Long> {

    List<PrivateReservation> findAllByClientId(Long clientId);

    List<PrivateReservation> findAllByReservationTimeAndDuration(Timestamp reservationTime, Integer duration);

    List<PrivateReservation> findAllByReservationTimeBetween(Timestamp startTime, Timestamp finishTime);

    List<PrivateReservation> findAllByReservationTimeBefore(Timestamp before);


}
