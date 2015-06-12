package depedency;

import static org.junit.Assert.*;

import org.junit.Test;

public class DependencyGraphManagerTest {

    @Test
    public void testReadInputFile() throws Exception {
        DependencyGraphManager depMgr = new DependencyGraphManager();
        depMgr.readInputFile("./input.txt");
        fail("haha");
    }

}
