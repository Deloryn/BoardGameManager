package pl.put.boardgamemanager.model.rental.private_rental;

import pl.put.boardgamemanager.dto.DTO;
import pl.put.boardgamemanager.dto.PrivateRentalDTO;
import pl.put.boardgamemanager.model.Model;
import pl.put.boardgamemanager.model.TimeEvent;
import pl.put.boardgamemanager.model.rental.Rental;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "privaterentals", schema = "public", catalog = "postgres")
@DiscriminatorValue("p")
public class PrivateRental extends Rental implements Model, TimeEvent {

    @Column(name = "clientid", nullable = false)
    private Long clientId;

    @Column(name = "rentaltime", nullable = false)
    private Timestamp startTime;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @NotBlank
    @Column(name = "status", nullable = false, length = 30)
    private String status;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp rentaltime) {
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
        if(!super.equals(o)) return false;
        else if (getClass() != o.getClass()) return false;
        else {
            PrivateRental that = (PrivateRental) o;
            return Objects.equals(clientId, that.clientId) &&
                    Objects.equals(startTime, that.startTime) &&
                    Objects.equals(duration, that.duration) &&
                    Objects.equals(status, that.status);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, copyId, clientId, startTime, duration, status);
    }

    public void updateParamsFrom(DTO dto) {
        if(dto instanceof PrivateRentalDTO) {
            PrivateRentalDTO privateRentalDTO = (PrivateRentalDTO) dto;
            this.setClientId(privateRentalDTO.getClientId());
            this.setCopyId(privateRentalDTO.getCopyId());
            this.setDuration(privateRentalDTO.getDuration());
            this.setStartTime(Timestamp.valueOf(privateRentalDTO.getRentalTime()));
            this.setStatus(privateRentalDTO.getStatus());
        }
    }

    public PrivateRentalDTO toDTO() {
        PrivateRentalDTO dto = new PrivateRentalDTO();

        dto.setId(id);
        dto.setClientId(clientId);
        dto.setCopyId(copyId);
        dto.setDuration(duration);
        dto.setRentalTime(startTime.toLocalDateTime());
        dto.setStatus(status);

        return dto;
    }
}
