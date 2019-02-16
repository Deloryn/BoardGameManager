package pl.put.boardgamemanager.reservation.tournament_reservation;

import pl.put.boardgamemanager.reservation.Reservation;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tournamentreservations", schema = "public", catalog = "postgres")
@DiscriminatorValue("TournamentReservation")
public class TournamentReservation extends Reservation {

    @Column(name = "tutorid", nullable = true)
    private Integer tutorId;

    public Integer getTutorId() {
        return tutorId;
    }

    public void setTutorId(Integer tutorId) {
        this.tutorId = tutorId;
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o)) return false;
        else if(getClass() != o.getClass()) return false;
        else {
            TournamentReservation that = (TournamentReservation) o;
            return Objects.equals(tutorId, that.tutorId);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId, tutorId, type);
    }

}
