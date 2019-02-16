package pl.put.boardgamemanager.reservation.tournament_reservation;

import pl.put.boardgamemanager.reservation.Reservation;
import pl.put.boardgamemanager.tournament.Tournament;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tournamentreservations", schema = "public", catalog = "postgres")
@DiscriminatorValue("TournamentReservation")
public class TournamentReservation extends Reservation {

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
            TournamentReservation that = (TournamentReservation) o;
            return Objects.equals(tournament, that.tournament);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId, type, reservedTable, tutor, tournament);
    }

}
