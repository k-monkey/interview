package threads.producer_consumer;

import org.junit.Test;

public class ProducerConsumerTest {
    @Test
    public void testRun() {
        //int count = 0;
        //while (count++ < 10) {
            (new ProducerConsumer(10, 1, 1)).run();
        //}
    }
}
