package pl.put.boardgamemanager.private_reservation;

import pl.put.boardgamemanager.TimeEvent;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "privatereservations", schema = "public", catalog = "postgres")
@DiscriminatorValue("p")
public class PrivateReservation implements TimeEvent {

    @SequenceGenerator(name = "privatereservations_seq", sequenceName = "privatereservations_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "privatereservations_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tableid", nullable = false)
    private Long tableId;

    @Column(name = "tutorid")
    private Long tutorId;

    @Column(name = "clientid", nullable = false)
    private Long clientId;

    @Column(name = "starttime", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getTutorId() {
        return tutorId;
    }

    public void setTutorId(Long tutorId) {
        this.tutorId = tutorId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrivateReservation that = (PrivateReservation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(tableId, that.tableId) &&
                Objects.equals(tutorId, that.tutorId) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tableId, tutorId, startTime, duration, clientId);
    }

    public void updateParamsFrom(PrivateReservationDTO dto) {
        this.setTableId(dto.getTableId());
        this.setTutorId(dto.getTutorId());
        this.setClientId(dto.getClientId());
        this.setStartTime(dto.getStartTime());
        this.setDuration(dto.getDuration());
    }

    public PrivateReservationDTO toDTO() {
        PrivateReservationDTO dto = new PrivateReservationDTO();

        dto.setId(id);
        dto.setTableId(tableId);
        dto.setTutorId(tutorId);
        dto.setClientId(clientId);
        dto.setStartTime(startTime);
        dto.setDuration(duration);

        return dto;
    }
}
