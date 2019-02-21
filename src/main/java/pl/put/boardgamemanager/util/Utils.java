package pl.put.boardgamemanager.util;

import pl.put.boardgamemanager.model.TimeEvent;

import java.sql.Timestamp;
import java.util.Calendar;

public class Utils {

    public static boolean isEventDuringAnother(TimeEvent event, TimeEvent another) {
        if (event.getStartTime().before(another.getStartTime()))
            return calculateFinishTime(event).after(another.getStartTime());
        else
            return event.getStartTime().before(calculateFinishTime(another));
    }

    private static Timestamp calculateFinishTime(TimeEvent object) {
        return addToTimestamp(object.getStartTime(), object.getDuration() * 60);
    }

    private static Timestamp addToTimestamp(Timestamp ts, Integer seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(ts.getTime());
        cal.add(Calendar.SECOND, seconds);
        return new Timestamp(cal.getTime().getTime());
    }

}
