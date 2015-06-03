package threads.producer_consumer;

import java.util.concurrent.BlockingQueue;

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
                e.printStackTrace();
            }
        }
    }
}
