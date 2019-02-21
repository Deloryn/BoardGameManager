package pl.put.boardgamemanager.person.client;

import pl.put.boardgamemanager.person.Person;
import pl.put.boardgamemanager.tournament.Tournament;
import pl.put.boardgamemanager.tournament_participant.TournamentParticipant;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("c")
public class Client extends Person {

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "tournamentparticipants",
            joinColumns = @JoinColumn(name = "clientid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tournamentid", referencedColumnName = "id"))
    private List<Tournament> tournaments;

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }


    public void updateParamsFrom(ClientDTO dto) {
        this.setName(dto.getName());
        this.setSurname(dto.getSurname());
        this.setEmail(dto.getEmail());
        this.setPhoneNumber(dto.getPhoneNumber());
    }

    public ClientDTO toDTO() {
        ClientDTO dto = new ClientDTO();

        dto.setId(id);
        dto.setName(name);
        dto.setSurname(surname);
        dto.setEmail(email);
        dto.setPhoneNumber(phoneNumber);

        return dto;
    }

}
