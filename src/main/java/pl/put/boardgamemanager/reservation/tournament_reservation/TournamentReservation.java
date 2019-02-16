package pl.put.boardgamemanager.reservation.tournament_reservation;

import pl.put.boardgamemanager.reservation.Reservation;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tournamentreservations", schema = "public", catalog = "postgres")
@DiscriminatorValue("TournamentReservation")
public class TournamentReservation extends Reservation {

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
            TournamentReservation that = (TournamentReservation) o;
            return Objects.equals(tournamentId, that.tournamentId);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId, tutorId, type);
    }

}
