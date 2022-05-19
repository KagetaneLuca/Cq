package de.nordakademie.cq;

import de.nordakademie.safe.Event;

import java.util.Comparator;

public class SelfComparator {

    /**
     * @param e1 the first object to be compared.
     * @param e2 the second object to be compared.
     * @return
     */
    public static int compare(Event e1, Event e2) {
        if(e1 == null || e2 == null )
            return 0;
        if (e1.getTimestamp() < e2.getTimestamp())
            return -1;
        else if (e1.getTimestamp() > e2.getTimestamp())
            return 1;
        return 0;
    }
}
