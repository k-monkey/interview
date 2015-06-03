package threads.producer_consumer;

import org.junit.Test;

public class LockFreeProducerConsumerTest {

    @Test
    public void testRun() {
        (new LockFreeProducerConsumer(10, 1, 1)).run();
    }

}
