package pl.put.boardgamemanager.reservation.private_reservation;

import pl.put.boardgamemanager.person.client.Client;
import pl.put.boardgamemanager.reservation.Reservation;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "privatereservations", schema = "public", catalog = "postgres")
@DiscriminatorValue("PrivateReservation")
public class PrivateReservation extends Reservation {

    @Column(name = "tutorid", nullable = true)
    private Integer tutorId;

    @Column(name = "reservationtime", nullable = false)
    private Timestamp reservationTime;

    @Column(name = "duration", nullable = false)
    private Timestamp duration;

    @ManyToOne
    @JoinColumn(name = "clientid", referencedColumnName = "id", nullable = false)
    private Client client;

    public Integer getTutorId() {
        return tutorId;
    }

    public void setTutorId(Integer tutorId) {
        this.tutorId = tutorId;
    }

    public Timestamp getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Timestamp reservationTime) {
        this.reservationTime = reservationTime;
    }

    public Timestamp getDuration() {
        return duration;
    }

    public void setDuration(Timestamp duration) {
        this.duration = duration;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o)) return false;
        else if(getClass() != o.getClass()) return false;
        else {
            PrivateReservation that = (PrivateReservation) o;
            return Objects.equals(tutorId, that.tutorId) &&
                    Objects.equals(reservationTime, that.reservationTime) &&
                    Objects.equals(duration, that.duration);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId, tutorId, type, reservationTime, duration);
    }
}
