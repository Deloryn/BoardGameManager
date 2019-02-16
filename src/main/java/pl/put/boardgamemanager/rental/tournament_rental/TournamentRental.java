package pl.put.boardgamemanager.rental.tournament_rental;

import pl.put.boardgamemanager.rental.Rental;

import javax.persistence.*;

@Entity
@Table(name = "tournamentrentals", schema = "public", catalog = "postgres")
@DiscriminatorValue("TournamentRental")
public class TournamentRental extends Rental {

}
