package pl.put.boardgamemanager.logic.rental.private_rental;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.boardgamemanager.model.rental.private_rental.PrivateRental;

public interface PrivateRentalRepository extends JpaRepository<PrivateRental, Long> {

}