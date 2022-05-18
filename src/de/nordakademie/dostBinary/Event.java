package de.nordakademie.dostBinary;

public class Event {
    private final double timestamp;
    private final String eventDescription;
    
    public Event(double timestamp, String eventDescription) {
        this.timestamp = timestamp;
        this.eventDescription = eventDescription;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public String getEventDescription() {
        return eventDescription;
    }
}
