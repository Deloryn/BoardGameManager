package pl.put.boardgamemanager.person.client;

import pl.put.boardgamemanager.person.Person;

import javax.persistence.*;

@Entity
@Table(name = "clients", schema = "public", catalog = "postgres")
@DiscriminatorValue("c")
public class Client extends Person {

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
