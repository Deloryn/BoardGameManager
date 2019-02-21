package pl.put.boardgamemanager.util;

import pl.put.boardgamemanager.model.PrivateRental;
import pl.put.boardgamemanager.model.PrivateReservation;

import java.sql.Timestamp;
import java.util.Calendar;

public class Utils {

    public static boolean isEventDuringAnother(Object object, Object another) {
        if(object instanceof PrivateReservation && another instanceof PrivateReservation) {
            PrivateReservation reservation = (PrivateReservation) object;
            PrivateReservation anotherReservation = (PrivateReservation) another;
            if (reservation.getReservationTime().before(anotherReservation.getReservationTime()))
                return calculateFinishTime(reservation).after(anotherReservation.getReservationTime());
            else
                return reservation.getReservationTime().before(calculateFinishTime(anotherReservation));
        }

        else if(object instanceof PrivateRental && another instanceof PrivateRental) {
            PrivateRental rental = (PrivateRental) object;
            PrivateRental anotherRental = (PrivateRental) another;
            if (rental.getRentalTime().before(anotherRental.getRentalTime()))
                return calculateFinishTime(rental).after(anotherRental.getRentalTime());
            else
                return rental.getRentalTime().before(calculateFinishTime(anotherRental));
        }

        else return false;
    }

    private static Timestamp addToTimestamp(Timestamp ts, Integer seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(ts.getTime());
        cal.add(Calendar.SECOND, seconds);
        return new Timestamp(cal.getTime().getTime());
    }

    private static Timestamp calculateFinishTime(Object object) {
        if(object instanceof PrivateReservation) {
            PrivateReservation reservation = (PrivateReservation) object;
            return addToTimestamp(reservation.getReservationTime(), reservation.getDuration() * 60);
        }
        else if(object instanceof PrivateRental) {
            PrivateRental rental = (PrivateRental) object;
            return addToTimestamp(rental.getRentalTime(), rental.getDuration() * 60);
        }
        else return null;
    }

}
