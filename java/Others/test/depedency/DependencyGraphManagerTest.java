package depedency;

import static org.junit.Assert.*;

import org.junit.Test;

public class DependencyGraphManagerTest {

    @Test
    public void testReadInputFile() throws Exception {
        DependencyGraphManager depMgr = new DependencyGraphManager();
        //depMgr.readInputFile("./src/dependency/input.txt");
        depMgr.readInputFile("./src/depedency/input.txt");
        fail("haha");
    }

}
