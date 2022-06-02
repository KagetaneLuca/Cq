package de.nordakademie.model.eventqueue;

public interface IEventQueue<E> {
    void enqueue (Double time, String event);

    IEventQueue.Entry<E> dequeue ();

    public interface Entry<E> extends de.nordakademie.model.event.IEventQueue.Entry<Object> {

        Double getTime();

        E getEvent();

    }
}