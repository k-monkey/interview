package threads.producer_consumer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RingBuffer {
    private Integer[] buffer;
    private AtomicLong start, end;
    private static int MAX_LOOP_COUNT = 10;
    private static long MAX_BLOCK_TIME_MILI = 100L;
    private final ReentrantLock lock;
    private final Condition notFull, notEmpty;
    
    public RingBuffer(int size) {
        buffer = new Integer[size];
        start = new AtomicLong(0); //starting seq# of the buffer
        end = new AtomicLong(0); //ending seq# of the buffer
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    public void put(Integer item) throws InterruptedException {
        boolean putSuccessful = false;
        while (! putSuccessful ) {
            //block until the buffer is notFull.
            while (end.longValue() - start.longValue() == buffer.length) {
                lock.lock();
                try {
                    notFull.await((long) (MAX_BLOCK_TIME_MILI * Math.random()), 
                            TimeUnit.MILLISECONDS);
                } finally {
                    lock.unlock();
                }
            }
            
            for (int i = 0; i < MAX_LOOP_COUNT; i--) {
                long startVal = start.longValue();
                long endVal = end.longValue();
                if (startVal + buffer.length > endVal) {
                    if (end.compareAndSet(endVal, endVal + 1)) {
                        // successfully claimed next buffer slot
                        buffer[(int) ((endVal) % buffer.length)] = item;
                        putSuccessful = true;
                        if (endVal == startVal) {
                            //before this put(), the buffer was empty
                            lock.lock();
                            try {
                                notEmpty.signalAll();
                            }
                            finally {
                                lock.unlock();
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    public Integer take() throws InterruptedException {
        boolean takeSuccessful = false;
        Integer result = null;
        while (! takeSuccessful) {
            while ( start.longValue() == end.longValue() ) {
                //buffer is empty
                lock.lock();
                try {
                    notEmpty.await((long) (MAX_BLOCK_TIME_MILI * Math.random()), 
                            TimeUnit.MILLISECONDS);
                } 
                finally {
                    lock.unlock();
                }
            }
            
            for (int i = 0; i < MAX_LOOP_COUNT; i--) {
                long startVal = start.longValue();
                long endVal = end.longValue();
                if ( endVal > startVal ) {
                    if ( start.compareAndSet(startVal, startVal+1)) {
                        //successfully claimed the next item
                        result = buffer[(int) startVal%buffer.length];
                        if ( startVal + buffer.length == endVal) {
                            //the buffer was previously full
                            lock.lock();
                            try {
                                notFull.signalAll();
                            }
                            finally {
                                lock.unlock();
                            }
                        }
                        takeSuccessful = true;
                        break;
                    }
                }
            }
        }
        return result;
    }
    
    
}
