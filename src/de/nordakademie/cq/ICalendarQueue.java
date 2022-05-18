package de.nordakademie.cq;

import de.nordakademie.IEventQueue;

public interface ICalendarQueue<E> {

    void enqueue (Double time, E event);

    IEventQueue.Entry<E> dequeue ();

    public interface Entry<E> {

        Double getTime();

        E getEvent();

    }
}
