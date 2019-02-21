package pl.put.boardgamemanager.model.person.client;

import pl.put.boardgamemanager.dto.ClientDTO;
import pl.put.boardgamemanager.dto.DTO;
import pl.put.boardgamemanager.model.Model;
import pl.put.boardgamemanager.model.person.Person;
import pl.put.boardgamemanager.model.tournament.Tournament;

import javax.persistence.*;
import java.util.List;

@Entity
@javax.persistence.Table(name = "clients", schema = "public", catalog = "postgres")
@DiscriminatorValue("c")
public class Client extends Person implements Model {

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


    public void updateParamsFrom(DTO dto) {
        if(dto instanceof ClientDTO) {
            ClientDTO clientDTO = (ClientDTO) dto;
            this.setName(clientDTO.getName());
            this.setSurname(clientDTO.getSurname());
            this.setEmail(clientDTO.getEmail());
            this.setPhoneNumber(clientDTO.getPhoneNumber());
        }
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
