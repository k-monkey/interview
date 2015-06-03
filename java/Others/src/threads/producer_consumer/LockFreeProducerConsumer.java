package threads.producer_consumer;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LockFreeProducerConsumer {
    private ArrayBlockingQueue<Integer> bq;
    private int numProducers, numConsumers;
    private ExecutorService consumerPool, producerPool;

    public LockFreeProducerConsumer(int capacity, int numProducers,
            int numConsumers) {
        bq = new ArrayBlockingQueue<Integer>(capacity);
        this.consumerPool = Executors.newFixedThreadPool(numConsumers);
        this.producerPool = Executors.newFixedThreadPool(numProducers);
        this.numConsumers = numConsumers;
        this.numProducers = numProducers;
    }

    public void run() {
        ArrayList<Future<?>> futures = new ArrayList<Future<?>>();
        for (int i = 0; i < numProducers; i++) {
            futures.add(producerPool.submit(new Producer(bq)));
        }

        for (int i = 0; i < numConsumers; i++) {
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

