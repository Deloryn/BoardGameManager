package pl.put.boardgamemanager.private_rental;

import pl.put.boardgamemanager.DTO;

import java.time.LocalDateTime;

public class PrivateRentalDTO extends DTO {

    private Long id;

    private Long clientId;

    private Long copyId;

    private LocalDateTime startTime;

    private Integer duration;

    private String readOnlyGameName;

    @Override
    public boolean validate() {
        if(clientId == null) {
            this.setErrorMessage("clientId cannot be null");
            return false;
        }
        if(copyId == null) {
            this.setErrorMessage("copyId cannot be null");
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setCopyId(Long copyId) {
        this.copyId = copyId;
    }

    public Long getCopyId() {
        return copyId;
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

    public String getReadOnlyGameName() {
        return readOnlyGameName;
    }

    public void setReadOnlyGameName(String readOnlyGameName) {
        this.readOnlyGameName = readOnlyGameName;
    }
}
