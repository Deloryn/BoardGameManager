package pl.put.boardgamemanager;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class Utils {

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static boolean isEventDuringAnother(TimeEvent event, TimeEvent another) {
        if (event.getStartTime().before(another.getStartTime()))
            return calculateFinishTime(event).after(another.getStartTime());
        else
            return event.getStartTime().before(calculateFinishTime(another));
    }

    private static Timestamp calculateFinishTime(TimeEvent event) {
        return addToTimestamp(event.getStartTime(), event.getDuration() * 60);
    }

    private static Timestamp addToTimestamp(Timestamp ts, Integer seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(ts.getTime());
        cal.add(Calendar.SECOND, seconds);
        return new Timestamp(cal.getTime().getTime());
    }

}
