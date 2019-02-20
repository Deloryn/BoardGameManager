package pl.put.boardgamemanager.person.client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    public Client findByEmail(String email);

}