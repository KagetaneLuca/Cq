package de.nordakademie.cq;

import de.nordakademie.model.event.IEventQueue;

public interface ICalendarQueue<E> {

    void enqueue (Double time, String event);

    IEventQueue.Entry<E> dequeue ();

    public interface Entry<E> {

        Double getTime();

        E getEvent();

    }
}
