package de.nordakademie.cq.cqImpl;

import de.nordakademie.cq.IQueue;
import de.nordakademie.model.event.impl.Event;

import java.util.*;

public class CalendarQueue implements IQueue<Double, Object> {
    //    private PriorityQueue queue = new PriorityQueue(); <- rev. zu prio
    private List<Event> farFutrure = new LinkedList<>();
    private List<Event> nearFuture = new ArrayList<>();
    private Splaytree.Tree tree = new Splaytree.Tree();
    private long size;

    public CalendarQueue(int size) {
        this.size = size;
    }


    /**
     * @param time
     * @param eventDes
     */
    @Override
    public void enqueue(Double time, String eventDes) {
        if (tree.size(tree) > size) {
            farFutrure.add(new Event(time, eventDes));
        } else if (nearFuture.size() < tree.size(tree) / 100 || nearFuture.size() < 10) { // move e from splaytree to arraylist
            tree.insert(new Event(time, eventDes));
            nearFutureEnqueue();
            Collections.sort(nearFuture, Comparator.comparingDouble(Event::getTimestamp));
        } else {
            tree.insert(new Event(time, eventDes));
        }
    }

    private void nearFutureEnqueue() {
        for (int i = 0; i < tree.size(tree) / 10 - nearFuture.size(); i++) {
            nearFuture.add(tree.smallestElement(tree));
        }
    }

    public int size() {
        return tree.size(tree);
    }

    /**
     * @return Event that was removed
     */
    @Override
    public IQueue.Entry<Double, Object> dequeue() { // test
        nearFuture = new ArrayList<>();
        Splaytree.Tree temp = tree.deleteKey(tree.smallestElement(tree));
        return new EntryImpl<>(temp.key.getTimestamp(), temp.key.getEventDescription());
    }

    private static class EntryImpl<Object> implements IQueue.Entry<Double, Object> {

        private final Double time;
        private final Object event;

        public EntryImpl(Double time, Object event) {
            this.time = time;
            this.event = event;
        }

        /**
         * @return Time
         */
        @Override
        public Double getTime() {

            return time;
        }

        /**
         * @return Event
         */
        @Override
        public Object getEvent() {
            return event;
        }
    }

}
