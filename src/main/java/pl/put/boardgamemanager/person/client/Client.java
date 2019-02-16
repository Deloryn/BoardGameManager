package pl.put.boardgamemanager.person.client;

import pl.put.boardgamemanager.person.Person;

import javax.persistence.*;

@Entity
@Table(name = "clients", schema = "public", catalog = "postgres")
@DiscriminatorValue("Client")
public class Client extends Person {

}
