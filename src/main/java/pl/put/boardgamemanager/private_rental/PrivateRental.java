package pl.put.boardgamemanager.private_rental;

import pl.put.boardgamemanager.TimeEvent;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "privaterentals", schema = "public", catalog = "postgres")
public class PrivateRental implements TimeEvent {

    @SequenceGenerator(name = "privaterentals_seq", sequenceName = "privaterentals_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "privaterentals_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "copyid", nullable = false)
    private Long copyId;

    @Column(name = "clientid", nullable = false)
    private Long clientId;

    @Column(name = "starttime", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @NotBlank
    @Column(name = "status", nullable = false, length = 30)
    private String status;

    public void setId(Long id) { this.id = id; }

    public Long getId() { return id; }

    public void setCopyId(Long copyid) {
        this.copyId = copyid;
    }

    public Long getCopyId() {
        return copyId;
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

    public void setStartTime(LocalDateTime rentaltime) {
        this.startTime = rentaltime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (getClass() != o.getClass()) return false;
        else {
            PrivateRental that = (PrivateRental) o;
            return Objects.equals(id, that.id) &&
                    Objects.equals(copyId, that.copyId) &&
                    Objects.equals(clientId, that.clientId) &&
                    Objects.equals(startTime, that.startTime) &&
                    Objects.equals(duration, that.duration) &&
                    Objects.equals(status, that.status);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, copyId, clientId, startTime, duration, status);
    }

    public void updateParamsFrom(PrivateRentalDTO dto) {
        this.setClientId(dto.getClientId());
        this.setCopyId(dto.getCopyId());
        this.setDuration(dto.getDuration());
        this.setStartTime(dto.getRentalTime());
        this.setStatus(dto.getStatus());
    }

    public PrivateRentalDTO toDTO() {
        PrivateRentalDTO dto = new PrivateRentalDTO();

        dto.setId(id);
        dto.setClientId(clientId);
        dto.setCopyId(copyId);
        dto.setDuration(duration);
        dto.setRentalTime(startTime);
        dto.setStatus(status);

        return dto;
    }
}
