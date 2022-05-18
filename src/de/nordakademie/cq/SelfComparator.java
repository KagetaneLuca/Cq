package de.nordakademie.cq;

import de.nordakademie.dostBinary.Event;

public class SelfComparator<E> {

    public int compare(Event e1, Event e2) {
        if (e1.getTimestamp() < e2.getTimestamp())
            return -1;
        else if (e1.getTimestamp() > e2.getTimestamp())
            return 1;
        return 0;
    }
}
