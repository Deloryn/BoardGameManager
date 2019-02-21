package pl.put.boardgamemanager.model;

import pl.put.boardgamemanager.dto.PrivateReservationDTO;

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
    private Integer duration;

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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
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
        return Objects.hash(id, tableId, reservationTime, duration, clientId);
    }

    public void updateParamsFrom(PrivateReservationDTO dto) {
        this.setTableId(dto.getTableId());
        this.setTutorId(dto.getTutorId());
        this.setClientId(dto.getClientId());
        this.setReservationTime(Timestamp.valueOf(dto.getReservationTime()));
        this.setDuration(dto.getDuration());
    }

    public PrivateReservationDTO toDTO() {
        PrivateReservationDTO dto = new PrivateReservationDTO();

        dto.setId(id);
        dto.setTableId(tableId);
        dto.setTutorId(tutorId);
        dto.setClientId(clientId);
        dto.setReservationTime(reservationTime.toLocalDateTime());
        dto.setDuration(duration);

        return dto;
    }
}
