package pl.put.boardgamemanager;

import java.time.LocalDateTime;

public interface TimeEvent {

    LocalDateTime getStartTime();
    Integer getDuration();

}
