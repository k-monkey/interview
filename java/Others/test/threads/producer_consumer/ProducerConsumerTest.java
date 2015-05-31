package threads.producer_consumer;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProducerConsumerTest {
    @Test
    public void testRun() {
        (new ProducerConsumer(10, 10, 2)).run();
        fail("test finished");
    }
}
