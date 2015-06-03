package threads.producer_consumer;

public class LockFreeConsumer implements Runnable {
    private final RingBuffer buffer;
    
    public LockFreeConsumer(RingBuffer buffer) {
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("consumed: " + buffer.take());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
