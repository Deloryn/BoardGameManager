package pl.put.boardgamemanager.dto;

import pl.put.boardgamemanager.model.reservation.private_reservation.PrivateReservation;

public class ClientReservationDTO extends DTO {

    private PrivateReservation privateReservation;

    private Short numberOfSits;

    public PrivateReservation getPrivateReservation() {
        return privateReservation;
    }

    public void setPrivateReservation(PrivateReservation privateReservation) {
        this.privateReservation = privateReservation;
    }

    public Short getNumberOfSits() {
        return numberOfSits;
    }

    public void setNumberOfSits(Short numberOfSits) {
        this.numberOfSits = numberOfSits;
    }
}