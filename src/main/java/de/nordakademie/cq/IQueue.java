package de.nordakademie.cq;

public interface IQueue<T,E> {
    void enqueue(T time, String event);

    IQueue.Entry<T, E> dequeue ();

    public interface Entry<T, E> {

        T getTime();

        E getEvent();

    }
}
