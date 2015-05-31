package threads.producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {
    private ArrayBlockingQueue<Integer> bq;
    private int numProducers, numConsumers;
    
    public ProducerConsumer(int capacity, int numProducers, int numConsumers) {
        bq = new ArrayBlockingQueue<Integer>(capacity);
        this.numConsumers = numConsumers;
        this.numProducers = numProducers;
    }
    
    public void run() {
        for (int i=0; i< numProducers; i++) {
            (new Thread(new Producer(bq))).start();
        }
        
        for (int i=0; i< numConsumers; i++) {
            (new Thread(new Consumer(bq))).start();
        }
    }
    
    public class Producer implements Runnable {
        private BlockingQueue<Integer> bqueue;
    
        public Producer(BlockingQueue<Integer> bqueue) {
            this.bqueue = bqueue;
        }
        
        @Override
        public void run() {
            while (true) {
                try {
                    bqueue.put(new Integer((int) Math.round(Math.random()*1000000)));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    
    public class Consumer implements Runnable {
        private BlockingQueue<Integer> bqueue;
        
        public Consumer(BlockingQueue<Integer> bqueue) {
            this.bqueue = bqueue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("consumed: " + bq.take());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
    }
}
