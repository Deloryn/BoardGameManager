package pl.put.boardgamemanager.rental.private_rental;

import java.sql.Timestamp;

public class PrivateRentalDTO {

    private Long copyId;

    private Timestamp rentalTime;

    private Timestamp duration;

    private String status;

    public Long getCopyId() {
        return copyId;
    }

    public void setCopyId(Long copyId) {
        this.copyId = copyId;
    }

    public Timestamp getRentalTime() {
        return rentalTime;
    }

    public void setRentalTime(Timestamp rentalTime) {
        this.rentalTime = rentalTime;
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
}
