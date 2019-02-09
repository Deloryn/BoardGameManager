package pl.put.boardgamemanager.reservation.private_reservation;

import javax.persistence.*;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "private_reservations", schema = "public", catalog = "postgres")
public class PrivateReservation {
    private int tableId;
    private Timestamp reservationTime;
    private Timestamp duration;

    @Id
    @Column(name = "table_id1")
    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId1) {
        this.tableId = tableId1;
    }

    @Basic
    @Column(name = "reservation_time")
    public Timestamp getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Timestamp reservationTime) {
        this.reservationTime = reservationTime;
    }

    @Basic
    @Column(name = "duration")
    public Timestamp getDuration() {
        return duration;
    }

    public void setDuration(Timestamp duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrivateReservation that = (PrivateReservation) o;
        return tableId == that.tableId &&
                Objects.equals(reservationTime, that.reservationTime) &&
                Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId, reservationTime, duration);
    }
}
