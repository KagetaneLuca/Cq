package de.nordakademie.dostBinary;

import de.nordakademie.IEventQueue;

import java.util.LinkedList;
import java.util.List;

public class EventQueueBinaryLink<Object> implements IEventQueue<Object> {

    private List<Entry<Object>> queue = new LinkedList<>();
    private BinarySearchTree.Tree tree = new BinarySearchTree.Tree();
    private int size;

    public EventQueueBinaryLink(int size) {

        this.size = size;
    }

    /**
     * @param time  Event-timestamp
     * @param event What`ll happen
     */
    @Override
    public void enqueue(Double time, Object event) {
        if (queue.size() > 10) { // move e from linkedlist to binarySearchTree
            for (int i=0; i < queue.size(); i++) {
                tree.insert(new Event(queue.get(i).getTime(), queue.get(i).getEvent().toString()));
            }
        } else if (tree.size(tree) > 0 || size > 10) { // insert into BinarySearchTree
            tree.insert(new Event(time, event.toString()));
//            System.out.println(tree.smallestElement(tree));
        } else { //  insert into linkedList
           linkEnqueue(time, event);
        }
    }
    private void linkEnqueue(Double time, Object event){
        if (queue.isEmpty()) {
            queue.add(new EntryImpl<>(time, event));
            return;
        }
        for (int i = 0; i < queue.size(); i++) {
            if (time < queue.get(i).getTime()) {
                queue.add(i, new EntryImpl<>(time, event));
                return;
            }
        }
        queue.add(new EntryImpl<>(time, event));
    }
    @Override
    public Entry<Object> dequeue() {
        if (!queue.isEmpty()){
            return queue.remove(0);
        } else if (tree.size(tree) > 0) {
//            System.out.println(tree.key.getTimestamp());
//            tree.deleteKey(new Event(tree.smallestElement(tree), ""));
            BinarySearchTree.Tree delTree =  tree.deleteKey(new Event(tree.smallestElement(tree), ""));
            return new EntryImpl<>(delTree.key.getTimestamp(), (Object) delTree.key.getEventDescription());
//            return null;
        } else{
            return null;
        }
    }

    private static class EntryImpl<Object> implements Entry<Object> {

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