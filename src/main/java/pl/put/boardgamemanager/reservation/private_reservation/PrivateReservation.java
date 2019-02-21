package pl.put.boardgamemanager.reservation.private_reservation;

import pl.put.boardgamemanager.TimeEvent;
import pl.put.boardgamemanager.reservation.Reservation;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "privatereservations", schema = "public", catalog = "postgres")
@DiscriminatorValue("p")
public class PrivateReservation extends Reservation implements TimeEvent {

    @Column(name = "clientid", nullable = false)
    private Long clientId;

    @Column(name = "reservationtime", nullable = false)
    private Timestamp startTime;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
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
            return Objects.equals(startTime, that.startTime) &&
                    Objects.equals(duration, that.duration) &&
                    Objects.equals(clientId, that.clientId);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tableId, startTime, duration, clientId);
    }

    public void updateParamsFrom(PrivateReservationDTO dto) {
        this.setTableId(dto.getTableId());
        this.setTutorId(dto.getTutorId());
        this.setClientId(dto.getClientId());
        this.setStartTime(Timestamp.valueOf(dto.getReservationTime()));
        this.setDuration(dto.getDuration());
    }

    public PrivateReservationDTO toDTO() {
        PrivateReservationDTO dto = new PrivateReservationDTO();

        dto.setId(id);
        dto.setTableId(tableId);
        dto.setTutorId(tutorId);
        dto.setClientId(clientId);
        dto.setReservationTime(startTime.toLocalDateTime());
        dto.setDuration(duration);

        return dto;
    }
}
