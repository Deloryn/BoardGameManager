package pl.put.boardgamemanager.person;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "persons", schema = "public", catalog = "postgres")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "id")
public abstract class Person {

    @SequenceGenerator(name = "persons_seq", sequenceName = "persons_seq")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persons_seq")
    @Column(name = "id", nullable = false)
    protected Long id;

    @NotBlank
    @Column(name = "name", nullable = false, length = 100)
    protected String name;

    @NotBlank
    @Column(name = "surname", nullable = false, length = 100)
    protected String surname;

    @NotBlank
    @Column(name = "email", nullable = false, length = 100)
    protected String email;

    @NotBlank
    @Column(name = "phonenumber", nullable = false, length = 20)
    protected String phoneNumber;

    @Column(name = "role", length = 1)
    protected Character role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Character getRole() {
        return role;
    }

    public void setRole(Character role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(name, person.name) &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(email, person.email) &&
                Objects.equals(phoneNumber, person.phoneNumber) &&
                Objects.equals(role, person.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, phoneNumber, role);
    }

}
