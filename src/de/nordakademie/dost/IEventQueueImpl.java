package de.nordakademie.dost;

import de.nordakademie.IEventQueue;

import java.util.LinkedList;
import java.util.List;

public class IEventQueueImpl<Object> implements IEventQueue<Object> {

    private List<Entry<Object>> queue = new LinkedList<>();

    /**
     * @param time Event-timestamp
     * @param event What`ll happen
     */
    @Override
    public void enqueue(Double time, Object event) {
        if(queue.isEmpty()){
            queue.add(new EntryImpl<>(time, event));
            return;
        }
        for (int i = 0; i < queue.size(); i++) {
            if (time < queue.get(i).getTime()){
                queue.add(i, new EntryImpl<>(time, event));
                return;
            }
        }
        queue.add(new EntryImpl<>(time, event));
    }

    @Override
    public Entry<Object> dequeue() {

        return queue.remove(0);
    }

    private static class EntryImpl<Object> implements Entry<Object>{

        private final Double time;
        private final Object event;

        public EntryImpl(Double time, Object event) {
            this.time = time;
            this.event = event;
        }

        /**
         * @return Double
         */
        @Override
        public Double getTime() {

            return time;
        }

        /**
         * @return Object
         */
        @Override
        public Object getEvent() {

            return event;
        }
    }
}