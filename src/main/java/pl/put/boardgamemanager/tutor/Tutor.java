package pl.put.boardgamemanager.tutor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "tutors", schema = "public", catalog = "postgres")
public class Tutor {
    private int personId;

    @Id
    @Column(name = "person_id")
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tutor tutor = (Tutor) o;
        return personId == tutor.personId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId);
    }
}
