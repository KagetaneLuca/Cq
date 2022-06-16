package de.nordakademie.cq.cqImpl;

import de.nordakademie.cq.IQueue;
import de.nordakademie.model.event.impl.Event;

import java.util.*;

public class CalendarQueue implements IQueue<Double, Object> {
    //    private PriorityQueue queue = new PriorityQueue(); <- rev. zu prio
    private List<Event> farFutrure = new LinkedList<>();
    private List<Event> nearFuture = new ArrayList<>();
    private Node.Splaytree splaytree = new Node.Splaytree();
    private final int nearFutureMaxSize = 10;
    private final long size;

    public CalendarQueue(int size) {
        this.size = size;
    }


    /**
     * @param time
     * @param eventDes
     */
    @Override
    public void enqueue(Double time, String eventDes) {
        if ( treeSize() >= size) {
            farFutrure.add(new Event(time, eventDes));
            return;
        }
        splaytree.insert(new Event(time, eventDes));
        if (nearFuture.size() < nearFutureMaxSize) {
            Node note = splaytree.minimum();
            Event smallestTreeEvent = new Event(note.key.getTimestamp(), note.key.getEventDescription());
            nearFuture.add(smallestTreeEvent);
            Collections.sort(nearFuture, Comparator.comparingDouble(Event::getTimestamp));
            splaytree.deleteEvent(smallestTreeEvent); // node?? was ist das genau , kann man das ohne note para machen, in der impl von root? nehmen
        }
    }

    public int treeSize() {
        return splaytree.size(splaytree.root);
    }

    /**
     * @return Event that was removed
     */
    @Override
    public IQueue.Entry<Double, Object> dequeue() {
        Event event;
        if (!nearFuture.isEmpty()) {
            event = nearFuture.remove(0);
            return new EntryImpl<>(event.getTimestamp(), event.getEventDescription());
        } else if (splaytree.size(splaytree.root) > 0) {
            event = splaytree.minimum().key;
            splaytree.deleteEvent(event);
            return new EntryImpl<>(event.getTimestamp(), event.getEventDescription());
        } else if (!farFutrure.isEmpty()) {
            event = farFutrure.remove(0);
            return new EntryImpl<>(event.getTimestamp(), event.getEventDescription());
        }
        return null;
    }

    public boolean dequeueAll() {
//        if (farFutrure.isEmpty() && nearFuture.isEmpty() && tree.size(tree) == 0) {
//            return false;
//        } else {
//            nearFuture.clear();
//            int size = tree.size(tree);
//            for (int i = 0; i < size; i++) {
//                tree.deleteKey(tree.smallestElement(tree));
//            }
//            farFutrure.clear();
//            return true;
//        }
        return false; // remove
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

    public List<Event> getFarFutrure() {
        return farFutrure;
    }

    public List<Event> getNearFuture() {
        return nearFuture;
    }

    public Node.Splaytree getTree() {
        return splaytree;
    }
    public long getSize() {
        return size;
    }
}
