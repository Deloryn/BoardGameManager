package pl.put.boardgamemanager.person.client;

import pl.put.boardgamemanager.reservation.private_reservation.PrivateReservation;

public class ClientReservationDTO {

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
