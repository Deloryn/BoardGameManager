package pl.put.boardgamemanager.person.client;

import pl.put.boardgamemanager.DTO;
import pl.put.boardgamemanager.private_reservation.PrivateReservation;

public class ClientReservationDTO extends DTO {

    private PrivateReservation privateReservation;

    private Short numberOfSits;

    public boolean validate() {
        if(privateReservation == null) {
            this.setErrorMessage("Private reservation cannot be null");
            return false;
        }
        if(numberOfSits == null) {
            this.setErrorMessage("Number of sits cannot be null");
            return false;
        }
        if(numberOfSits <= 0) {
            this.setErrorMessage("Number of sits must be greater than 0");
            return false;
        }
        return true;
    }

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
