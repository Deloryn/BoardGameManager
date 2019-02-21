package pl.put.boardgamemanager.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.boardgamemanager.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    public Client findByEmail(String email);

}