package pl.put.boardgamemanager;

import java.sql.Timestamp;

public interface TimeEvent {

    Timestamp getStartTime();
    Integer getDuration();

}