package pl.put.boardgamemanager.person.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);

    @Procedure(name = "getregularclientsemails")
    List<String> getRegularClientsEmails();

    @Procedure(name = "copypersons")
    void copyPersons();

}