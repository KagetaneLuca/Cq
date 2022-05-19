package de.nordakademie.cq;

import de.nordakademie.IEventQueue;
import de.nordakademie.safe.Event;
import java.util.Arrays;

public class CalendarQueue implements ICalendarQueue<Object> {
//    private PriorityQueue queue = new PriorityQueue(); <- rev. zu prio
    // enqueue with an Object or an array/ List as parameter
    private Event[] bucketArray;

    private Event[][] bucketListArray = new Event[8][];
    public CalendarQueue(int size) {

        bucketArray = new Event[size * 2];
        Arrays.fill(bucketListArray,0, bucketListArray.length-1, bucketArray);
    }

    /**
     * @param time
     * @param eventDes
     */
    @Override
    public void enqueue(Double time, Object eventDes) {
       if(null != bucketArray[bucketArray.length -1]){
           bucketArray = Arrays.copyOf(bucketArray, bucketArray.length *2);
       }
        int bucketNumber = (int)(time/0.5D) -1;
        if (bucketNumber > bucketListArray.length) {
            bucketListArray = Arrays.copyOf(bucketListArray, bucketListArray.length * 2);
        }
        if (bucketNumber < 0){ // past day/ years
//            System.out.println("Bucket n smaller 0: " + bucketNumber);
        } else{
                bucketArray = bucketListArray[bucketNumber];
                bucketArray[0] = new Event(time, eventDes.toString());
                Arrays.sort(bucketArray, SelfComparator::compare);
                bucketListArray[bucketNumber] = bucketArray;
            }
        }

    /**
     * @return Event that was removed
     */
    @Override
    public IEventQueue.Entry<Object> dequeue() {
        return new EntryImpl<>(bucketListArray[0][0].getTimestamp(), bucketListArray[0][0].getEventDescription());
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
