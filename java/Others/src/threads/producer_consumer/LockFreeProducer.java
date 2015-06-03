package threads.producer_consumer;

public class LockFreeProducer implements Runnable {
    private final RingBuffer buffer;
    
    public LockFreeProducer(RingBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                buffer.put(new Integer((int) Math.round(Math.random()*1000000)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
