package pl.put.boardgamemanager.private_reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrivateReservationRepository extends JpaRepository<PrivateReservation, Long> {

    List<PrivateReservation> findAllByClientId(Long clientId);

}
