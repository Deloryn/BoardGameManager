package pl.put.boardgamemanager.person.client;

import pl.put.boardgamemanager.person.Person;

import javax.persistence.*;

@Entity
@Table(name = "clients", schema = "public", catalog = "postgres")
@DiscriminatorValue("c")
public class Client extends Person {

    public void updateParams(Client newClient) {
        this.setName(newClient.getName());
        this.setSurname(newClient.getSurname());
        this.setEmail(newClient.getEmail());
        this.setPhoneNumber(newClient.getPhoneNumber());
    }

    public static Client fromDTO(ClientDTO dto) {

        if(dto == null) return null;

        Client client = new Client();

        client.setId(dto.getId());
        client.setName(dto.getName());
        client.setSurname(dto.getSurname());
        client.setEmail(dto.getEmail());
        client.setPhoneNumber(dto.getPhoneNumber());

        return client;
    }

    public static ClientDTO toDTO(Client client) {

        if(client == null) return null;

        ClientDTO dto = new ClientDTO();

        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setSurname(client.getSurname());
        dto.setEmail(client.getEmail());
        dto.setPhoneNumber(client.getPhoneNumber());

        return dto;
    }

}
