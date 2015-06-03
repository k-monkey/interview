package threads.producer_consumer;

import java.util.concurrent.BlockingQueue;



public class Consumer implements Runnable {
    private BlockingQueue<Integer> bqueue;
    
    public Consumer(BlockingQueue<Integer> bqueue) {
        this.bqueue = bqueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("consumed: " + bqueue.take());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
}