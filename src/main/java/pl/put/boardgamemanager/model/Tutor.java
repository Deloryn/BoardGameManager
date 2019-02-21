package pl.put.boardgamemanager.model;

import pl.put.boardgamemanager.dto.TutorDTO;

import javax.persistence.*;

@Entity
@Table(name = "tutors", schema = "public", catalog = "postgres")
@DiscriminatorValue("t")
public class Tutor extends Person {

    public void updateParamsFrom(TutorDTO dto) {
        this.setName(dto.getName());
        this.setSurname(dto.getSurname());
        this.setEmail(dto.getEmail());
        this.setPhoneNumber(dto.getPhoneNumber());
    }

    public TutorDTO toDTO() {
        TutorDTO dto = new TutorDTO();

        dto.setId(id);
        dto.setName(name);
        dto.setSurname(surname);
        dto.setEmail(email);
        dto.setPhoneNumber(phoneNumber);

        return dto;
    }
}
