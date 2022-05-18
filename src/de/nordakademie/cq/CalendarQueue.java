package de.nordakademie.cq;

import de.nordakademie.IEventQueue;
import de.nordakademie.safe.Event;

import java.util.*;
import java.util.Comparator;

public class CalendarQueue implements ICalendarQueue<Object> {
//    private PriorityQueue queue = new PriorityQueue(); <- rev. zu prio
    private List<Event> bucket = new ArrayList<>();
    private List<List<Event>> bucketList = new ArrayList<>();
    private int size;
    public CalendarQueue(int size) {
        this.size = size;
    }

    /**
     * @param time
     * @param eventDes
     */
    @Override
    public void enqueue(Double time, Object eventDes) {
//        if (bucket.size() > 10) { // move e from linkedlist to binarySearchTree
////            System.out.println("queue größer 10");
//            for (int i = 0; i < bucket.size(); i++) {
//                tree.insert(new de.nordakademie.safe.Event(bucket.get(i).getTimestamp(), bucket.get(i).getEventDescription()));
//            }
//        } else if (tree.size(tree) > 0) { // insert into BinarySearchTree
////            System.out.println("insert tree");
//            tree.insert(new de.nordakademie.safe.Event(time, eventDes.toString()));
//
//        } else { //  insert into linkedList
////            System.out.println("insert linkedList");
//            comparatorEnqueue(time, eventDes);
//        }




        Event event = new Event(time, eventDes.toString());
        int bucketNumber = (int)(time/0.5D) -1;
        if (bucketNumber < 0){
//            System.out.println("Bucket n smaller 0: " + bucketNumber);
        } else{
            if (bucketList.isEmpty()){
                bucket.add(event);


                bucketList.add(bucketNumber, bucket);
            } else {
                bucket = bucketList.get(bucketNumber);
                bucket.add(event);
                Collections.sort(bucket, new Comparator<Event>() {
                    /**
                     * @param e1 the first object to be compared.
                     * @param e2 the second object to be compared.
                     * @return
                     */
                    @Override
                    public int compare(Event e1, Event e2) {
                        if (e1.getTimestamp() < e2.getTimestamp())
                            return -1;
                        else if (e1.getTimestamp() > e2.getTimestamp())
                            return 1;
                        return 0;
                    }
                });
                bucketList.remove(bucketNumber);
                bucketList.add(bucketNumber, bucket);
            }
        }
    }


    /**
     * @return Event that was removed
     */
    @Override
    public IEventQueue.Entry<Object> dequeue() {
        return new EntryImpl<>(bucketList.get(0).get(0).getTimestamp(), bucketList.get(0).get(0).getEventDescription());
    }
    private static class EntryImpl<Object> implements IEventQueue.Entry<Object> {

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
