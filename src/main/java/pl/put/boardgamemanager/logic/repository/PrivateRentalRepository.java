package pl.put.boardgamemanager.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.boardgamemanager.model.PrivateRental;

public interface PrivateRentalRepository extends JpaRepository<PrivateRental, Long> {

}