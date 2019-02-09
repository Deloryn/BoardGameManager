package pl.put.boardgamemanager.privaterental;

import javax.persistence.*;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "private_rentals", schema = "public", catalog = "postgres")
public class PrivateRental {
    private int copyId1;
    private Timestamp rentalTime;
    private Timestamp duration;
    private String status;

    @Id
    @Column(name = "copy_id1")
    public int getCopyId1() {
        return copyId1;
    }

    public void setCopyId1(int copyId1) {
        this.copyId1 = copyId1;
    }

    @Basic
    @Column(name = "rental_time")
    public Timestamp getRentalTime() {
        return rentalTime;
    }

    public void setRentalTime(Timestamp rentalTime) {
        this.rentalTime = rentalTime;
    }

    @Basic
    @Column(name = "duration")
    public Timestamp getDuration() {
        return duration;
    }

    public void setDuration(Timestamp duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrivateRental that = (PrivateRental) o;
        return copyId1 == that.copyId1 &&
                Objects.equals(rentalTime, that.rentalTime) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(copyId1, rentalTime, duration, status);
    }
}
