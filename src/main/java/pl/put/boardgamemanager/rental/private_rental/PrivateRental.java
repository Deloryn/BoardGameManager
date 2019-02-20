package pl.put.boardgamemanager.rental.private_rental;

import pl.put.boardgamemanager.rental.Rental;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "privaterentals", schema = "public", catalog = "postgres")
@DiscriminatorValue("p")
public class PrivateRental extends Rental {

    @Column(name = "rentaltime", nullable = false)
    private Timestamp rentalTime;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @NotBlank
    @Column(name = "status", nullable = false, length = 30)
    private String status;

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
            return Objects.equals(rentalTime, that.rentalTime) &&
                    Objects.equals(duration, that.duration) &&
                    Objects.equals(status, that.status);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, copyId, rentalTime, duration, status);
    }

    public void updateParamsFrom(PrivateRentalDTO dto) {
        this.setCopyId(dto.getCopyId());
        this.setDuration(dto.getDuration());
        this.setRentalTime(Timestamp.valueOf(dto.getRentalTime()));
        this.setStatus(dto.getStatus());
    }

    public PrivateRentalDTO toDTO() {
        PrivateRentalDTO dto = new PrivateRentalDTO();

        dto.setId(id);
        dto.setCopyId(copyId);
        dto.setDuration(duration);
        dto.setRentalTime(rentalTime.toLocalDateTime());
        dto.setStatus(status);

        return dto;
    }

}
