package pl.put.boardgamemanager.logic.person.client;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.boardgamemanager.model.person.client.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    public Client findByEmail(String email);

}