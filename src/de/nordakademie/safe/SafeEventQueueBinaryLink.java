package de.nordakademie.safe;

import de.nordakademie.IEventQueue;
import java.util.LinkedList;
import java.util.List;

public class SafeEventQueueBinaryLink<Object> implements IEventQueue<Object> {

    private List<Entry<Object>> queue = new LinkedList<>();
    private SafeBinarySearchTree.Tree tree = new SafeBinarySearchTree.Tree();
    private int size;

    public SafeEventQueueBinaryLink(int size) {

        this.size = size;
    }

    /**
     * @param time  Event-timestamp
     * @param event What`ll happen
     */
    @Override
    public void enqueue(Double time, Object event) {

        if (size > 10) {

        } else if (queue.size() > 10) { // move e from linkedlist to binarySearchTree
            System.out.println("queue größer 10");
            for (int i = 0; i < queue.size(); i++) {
                tree.insert(new Event(queue.get(i).getTime(), queue.get(i).getEvent().toString()));
            }
        } else if (tree.size(tree) > 0) { // insert into BinarySearchTree
            System.out.println("insert tree");
            tree.insert(new Event(time, event.toString()));

        } else { //  insert into linkedList
            System.out.println("insert linkedList");
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
            //System.out.println("tree size" + tree.size(tree));
            tree.deleteKey(new Event(tree.smallestElement(tree), ""));
            return null;
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
