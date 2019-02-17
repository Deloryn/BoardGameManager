package pl.put.boardgamemanager.reservation.private_reservation;

import pl.put.boardgamemanager.person.client.Client;
import pl.put.boardgamemanager.reservation.Reservation;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "privatereservations", schema = "public", catalog = "postgres")
@DiscriminatorValue("p")
public class PrivateReservation extends Reservation {

    @Column(name = "clientid", nullable = false)
    private Long clientId;

    @Column(name = "reservationtime", nullable = false)
    private Timestamp reservationTime;

    @Column(name = "duration", nullable = false)
    private Timestamp duration;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
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

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o)) return false;
        else if(getClass() != o.getClass()) return false;
        else {
            PrivateReservation that = (PrivateReservation) o;
            return Objects.equals(reservationTime, that.reservationTime) &&
                    Objects.equals(duration, that.duration) &&
                    Objects.equals(clientId, that.clientId);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId, reservationTime, duration, clientId);
    }

    public void updateParams(PrivateReservation privateReservation) {
        this.setTutorId(privateReservation.getTutorId());
        this.setClientId(privateReservation.getClientId());
        this.setReservationTime(privateReservation.getReservationTime());
        this.setDuration(privateReservation.getDuration());
    }

    public static PrivateReservation fromDTO(PrivateReservationDTO dto) {

        if(dto == null) return null;

        PrivateReservation privateReservation = new PrivateReservation();

        privateReservation.setTableId(dto.getTableId());
        privateReservation.setTutorId(dto.getTutorId());
        privateReservation.setClientId(dto.getClientId());
        privateReservation.setReservationTime(dto.getReservationTime());
        privateReservation.setDuration(dto.getDuration());

        return privateReservation;
    }

    public static PrivateReservationDTO toDTO(PrivateReservation privateReservation) {

        if(privateReservation == null) return null;

        PrivateReservationDTO dto = new PrivateReservationDTO();

        dto.setTableId(privateReservation.getTableId());
        dto.setTutorId(privateReservation.getTutorId());
        dto.setClientId(privateReservation.getClientId());
        dto.setReservationTime(privateReservation.getReservationTime());
        dto.setDuration(privateReservation.getDuration());

        return dto;
    }
}
