package pl.put.boardgamemanager;

import java.time.LocalDateTime;
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
        if (event.getStartTime().isBefore(another.getStartTime()))
            return calculateFinishTime(event).isAfter(another.getStartTime());
        else
            return event.getStartTime().isBefore(calculateFinishTime(another));
    }

    private static LocalDateTime calculateFinishTime(TimeEvent event) {
        return event.getStartTime().plusMinutes(event.getDuration());
    }

}
