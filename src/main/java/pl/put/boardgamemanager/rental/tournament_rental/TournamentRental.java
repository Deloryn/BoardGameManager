package pl.put.boardgamemanager.rental.tournament_rental;

import pl.put.boardgamemanager.rental.Rental;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tournamentrentals", schema = "public", catalog = "postgres")
@DiscriminatorValue("TournamentRental")
public class TournamentRental extends Rental {

    @Column(name = "tournamentid", nullable = false, unique = true)
    private Long tournamentId;

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o)) return false;
        else if(getClass() != o.getClass()) return false;
        else {
            TournamentRental that = (TournamentRental) o;
            return Objects.equals(tournamentId, that.tournamentId);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(copyId, type, tournamentId);
    }

}
