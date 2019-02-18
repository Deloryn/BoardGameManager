package pl.put.boardgamemanager.person.tutor;

import pl.put.boardgamemanager.person.Person;

import javax.persistence.*;

@Entity
@Table(name = "tutors", schema = "public", catalog = "postgres")
@DiscriminatorValue("t")
public class Tutor extends Person{
    public void updateParams(Tutor newTutor) {
        this.setName(newTutor.getName());
        this.setSurname(newTutor.getSurname());
        this.setEmail(newTutor.getEmail());
        this.setPhoneNumber(newTutor.getPhoneNumber());
    }

    public static Tutor fromDTO(TutorDTO dto) {

        if(dto == null) return null;

        Tutor tutor = new Tutor();

        tutor.setId(dto.getId());
        tutor.setName(dto.getName());
        tutor.setSurname(dto.getSurname());
        tutor.setEmail(dto.getEmail());
        tutor.setPhoneNumber(dto.getPhoneNumber());

        return tutor;
    }

    public static TutorDTO toDTO(Tutor tutor) {

        if(tutor == null) return null;

        TutorDTO dto = new TutorDTO();

        dto.setName(tutor.getName());
        dto.setSurname(tutor.getSurname());
        dto.setEmail(tutor.getEmail());
        dto.setPhoneNumber(tutor.getPhoneNumber());

        return dto;
    }
}
