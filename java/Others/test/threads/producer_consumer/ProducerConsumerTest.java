package threads.producer_consumer;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class ProducerConsumerTest {
    @Test
    public void testLockVersion01() {
        runTest(10, 1, 1);
    }

    @Test
    public void testLockFreeVersion01() {
        runTest2(10, 1, 1);
    }
    
    private void runTest2(int capacity, int numConsumers, int numProducers) {
        RingBuffer buffer = new RingBuffer(capacity);
        ExecutorService consumerPool = Executors.newFixedThreadPool(numConsumers);
        ExecutorService producerPool = Executors.newFixedThreadPool(numProducers);

        ArrayList<Future<?>> futures = new ArrayList<Future<?>>();
        for (int i=0; i< numProducers; i++) {
            futures.add(producerPool.submit(new LockFreeProducer(buffer)));
        }
        
        for (int i=0; i< numConsumers; i++) {
            futures.add(consumerPool.submit(new LockFreeConsumer(buffer)));
        }
        
        System.out.println("futures: " + futures.size());
        for (Future<?> f : futures) {
            try {
                f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("one thread failed");
                continue;
            } catch (ExecutionException e) {
                System.out.println("one thread failed");
                e.printStackTrace();
                continue;
            }
            System.out.println("one thread finished");
        }    
    }
    
    private void runTest(int capacity, int numConsumers, int numProducers) {
        BlockingQueue<Integer> bq = new ArrayBlockingQueue<Integer>(capacity);
        ExecutorService consumerPool = Executors.newFixedThreadPool(numConsumers);
        ExecutorService producerPool = Executors.newFixedThreadPool(numProducers);

        ArrayList<Future<?>> futures = new ArrayList<Future<?>>();
        for (int i=0; i< numProducers; i++) {
            futures.add(producerPool.submit(new Producer(bq)));
        }
        
        for (int i=0; i< numConsumers; i++) {
            futures.add(consumerPool.submit(new Consumer(bq)));
        }
        
        System.out.println("futures: " + futures.size());
        for (Future<?> f : futures) {
            try {
                f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("one thread failed");
                continue;
            } catch (ExecutionException e) {
                System.out.println("one thread failed");
                e.printStackTrace();
                continue;
            }
            System.out.println("one thread finished");
        }    
    }    
}
