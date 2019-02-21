package pl.put.boardgamemanager.model;

import java.sql.Timestamp;

public interface TimeEvent {

    Timestamp getStartTime();

    Integer getDuration();

}
