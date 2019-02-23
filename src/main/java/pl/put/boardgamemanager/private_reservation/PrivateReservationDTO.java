package pl.put.boardgamemanager.private_reservation;

import pl.put.boardgamemanager.DTO;

import java.time.LocalDateTime;

public class PrivateReservationDTO extends DTO {

    private Long id;

    private Long tableId;

    private Long tutorId;

    private Long clientId;

    private LocalDateTime startTime;

    private Integer duration;

    public boolean validate() {
        if(tableId == null) {
            this.setErrorMessage("tableId cannot be null");
            return false;
        }

        if(clientId == null) {
            this.setErrorMessage("clientId cannot be null");
            return false;
        }

        if(startTime == null) {
            this.setErrorMessage("Start time cannot be null");
            return false;
        }

        if(duration == null) {
            this.setErrorMessage("Duration cannot be null");
            return false;
        }

        if(duration <= 0) {
            this.setErrorMessage("Duration must be greater than 0");
            return false;
        }

        return true;
    }

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

}
