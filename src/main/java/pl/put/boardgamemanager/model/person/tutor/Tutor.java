package pl.put.boardgamemanager.model.person.tutor;

import pl.put.boardgamemanager.dto.DTO;
import pl.put.boardgamemanager.dto.TutorDTO;
import pl.put.boardgamemanager.model.Model;
import pl.put.boardgamemanager.model.person.Person;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "tutors", schema = "public", catalog = "postgres")
@DiscriminatorValue("t")
public class Tutor extends Person implements Model {

    public void updateParamsFrom(DTO dto) {
        if(dto instanceof TutorDTO)
        {
            TutorDTO tutorDTO = (TutorDTO) dto;
            this.setName(tutorDTO.getName());
            this.setSurname(tutorDTO.getSurname());
            this.setEmail(tutorDTO.getEmail());
            this.setPhoneNumber(tutorDTO.getPhoneNumber());
        }
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
