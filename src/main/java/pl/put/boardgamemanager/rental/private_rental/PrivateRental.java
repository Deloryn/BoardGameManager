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
    private Timestamp duration;

    @NotBlank
    @Column(name = "status", nullable = false, length = 30)
    private String status;

    public Timestamp getRentalTime() {
        return rentalTime;
    }

    public void setRentalTime(Timestamp rentaltime) {
        this.rentalTime = rentaltime;
    }

    public Timestamp getDuration() {
        return duration;
    }

    public void setDuration(Timestamp duration) {
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
        return Objects.hash(copyId, rentalTime, duration, status);
    }

    public void updateParams(PrivateRental privateRental) {
        this.setDuration(privateRental.getDuration());
        this.setRentalTime(privateRental.getRentalTime());
        this.setStatus(privateRental.getStatus());
    }

    public static PrivateRental fromDTO(PrivateRentalDTO dto) {

        if(dto == null) return null;

        PrivateRental privateRental = new PrivateRental();

        privateRental.setCopyId(dto.getCopyId());
        privateRental.setDuration(dto.getDuration());
        privateRental.setRentalTime(dto.getRentalTime());
        privateRental.setStatus(dto.getStatus());

        return privateRental;
    }

    public static PrivateRentalDTO toDTO(PrivateRental privateRental) {

        if(privateRental == null) return null;

        PrivateRentalDTO dto = new PrivateRentalDTO();

        dto.setCopyId(privateRental.getCopyId());
        dto.setDuration(privateRental.getDuration());
        dto.setRentalTime(privateRental.getRentalTime());
        dto.setStatus(privateRental.getStatus());

        return dto;
    }

}
