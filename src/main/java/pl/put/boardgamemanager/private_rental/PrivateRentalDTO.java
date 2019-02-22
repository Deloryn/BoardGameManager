package pl.put.boardgamemanager.private_rental;

import java.time.LocalDateTime;

public class PrivateRentalDTO {

    private Long id;

    private Long clientId;

    private Long copyId;

    private LocalDateTime startTime;

    private Integer duration;

    private String status;

    private String readOnlyGameName;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReadOnlyGameName() {
        return readOnlyGameName;
    }

    public void setReadOnlyGameName(String readOnlyGameName) {
        this.readOnlyGameName = readOnlyGameName;
    }
}
