package pl.put.boardgamemanager.dto;

import java.time.LocalDateTime;

public class PrivateReservationDTO {

    private Long id;

    private Long tableId;

    private Long tutorId;

    private Long clientId;

    private LocalDateTime reservationTime;

    private Integer duration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getTableId() {
        return tableId;
    }

    public Long getTutorId() { return tutorId; }

    public void setTutorId(Long tutorId) { this.tutorId = tutorId; }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

}
