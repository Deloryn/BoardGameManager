package pl.put.boardgamemanager.person.client;

import pl.put.boardgamemanager.person.Person;

import javax.persistence.*;

@Entity
@Table(name = "clients", schema = "public", catalog = "postgres")
@DiscriminatorValue("c")
public class Client extends Person {

    public static Client fromDTO(ClientDTO dto) {
        Client client = new Client();

        client.setId(dto.getId());
        client.setName(dto.getName());
        client.setSurname(dto.getSurname());
        client.setEmail(dto.getEmail());
        client.setPhoneNumber(dto.getPhoneNumber());

        return client;
    }

    public static ClientDTO toDTO(Client client) {
        ClientDTO dto = new ClientDTO();

        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setSurname(client.getSurname());
        dto.setEmail(client.getEmail());
        dto.setPhoneNumber(client.getPhoneNumber());

        return dto;
    }

}
