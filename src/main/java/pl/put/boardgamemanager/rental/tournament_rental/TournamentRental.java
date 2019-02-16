package pl.put.boardgamemanager.rental.tournament_rental;

import pl.put.boardgamemanager.rental.Rental;
import pl.put.boardgamemanager.tournament.Tournament;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tournamentrentals", schema = "public", catalog = "postgres")
@DiscriminatorValue("TournamentRental")
public class TournamentRental extends Rental {

    @OneToOne
    @JoinColumn(name = "tournamentid", referencedColumnName = "id", nullable = false)
    private Tournament tournament;

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o)) return false;
        else if(getClass() != o.getClass()) return false;
        else {
            TournamentRental that = (TournamentRental) o;
            return Objects.equals(tournament, that.tournament);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(copyId, type, tournament);
    }

}
