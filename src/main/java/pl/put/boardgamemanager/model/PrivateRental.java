package pl.put.boardgamemanager.model;

import pl.put.boardgamemanager.dto.PrivateRentalDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "privaterentals", schema = "public", catalog = "postgres")
@DiscriminatorValue("p")
public class PrivateRental extends Rental {

    @Column(name = "clientid", nullable = false)
    private Long clientId;

    @Column(name = "rentaltime", nullable = false)
    private Timestamp rentalTime;

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

    public Timestamp getRentalTime() {
        return rentalTime;
    }

    public void setRentalTime(Timestamp rentaltime) {
        this.rentalTime = rentaltime;
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
                    Objects.equals(rentalTime, that.rentalTime) &&
                    Objects.equals(duration, that.duration) &&
                    Objects.equals(status, that.status);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, copyId, clientId, rentalTime, duration, status);
    }

    public void updateParamsFrom(PrivateRentalDTO dto) {
        this.setClientId(dto.getClientId());
        this.setCopyId(dto.getCopyId());
        this.setDuration(dto.getDuration());
        this.setRentalTime(Timestamp.valueOf(dto.getRentalTime()));
        this.setStatus(dto.getStatus());
    }

    public PrivateRentalDTO toDTO() {
        PrivateRentalDTO dto = new PrivateRentalDTO();

        dto.setId(id);
        dto.setClientId(clientId);
        dto.setCopyId(copyId);
        dto.setDuration(duration);
        dto.setRentalTime(rentalTime.toLocalDateTime());
        dto.setStatus(status);

        return dto;
    }
}
