package pl.put.boardgamemanager.person.tutor;

import pl.put.boardgamemanager.person.Person;

import javax.persistence.*;

@Entity
@Table(name = "tutors", schema = "public", catalog = "postgres")
@DiscriminatorValue("Tutor")
public class Tutor extends Person{

}
