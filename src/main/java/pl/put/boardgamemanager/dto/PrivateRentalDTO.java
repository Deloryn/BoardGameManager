package pl.put.boardgamemanager.dto;

import java.time.LocalDateTime;

public class PrivateRentalDTO {

    private Long id;

    private Long clientId;

    private Long copyId;

    private LocalDateTime rentalTime;

    private Integer duration;

    private String status;

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

    public LocalDateTime getRentalTime() {
        return rentalTime;
    }

    public void setRentalTime(LocalDateTime rentalTime) {
        this.rentalTime = rentalTime;
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

}
