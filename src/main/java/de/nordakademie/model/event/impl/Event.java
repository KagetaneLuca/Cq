package de.nordakademie.model.event.impl;

import java.util.GregorianCalendar;

public class Event {
    private final GregorianCalendar timestamp;
    private final String eventDescription;
    
    public Event(GregorianCalendar timestamp, String eventDescription) {
        this.timestamp = timestamp;
        this.eventDescription = eventDescription;
    }

    public GregorianCalendar getTimestamp() {
        return timestamp;
    }

    public String getEventDescription() {
        return eventDescription;
    }
}
