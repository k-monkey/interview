package tree;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class TreeNodeTest {

    @Test
    public void testEqualsObject() {
        String[] array1 = {"1","2","3","4","5", "#", "#"};
        TreeNode tn = TreeNode.fromArray(array1);
        TreeNode tn2 = TreeNode.fromArray(array1);
        assertEquals(tn, tn2);
    }

    @Test
    public void testToArray() {
        TreeNode tn = new TreeNode(0);
        String[] array = tn.toArray();
        assertEquals(1, array.length);
        assertEquals("0", array[0]);
    }

    @Test
    public void testFromArray01() {
        TreeNode tn = new TreeNode(0);
        String[] array = tn.toArray();
        TreeNode tn2 = TreeNode.fromArray(array);
        assertTrue(tn.equals(tn2));
        assertEquals(tn, tn2);
    }

    @Test
    public void testFromArray02() {
        String[] array1 = {"1","2","3","4","5", "#", "#"};
        TreeNode tn = TreeNode.fromArray(array1);
        String[] array2 = tn.toArray();
        assertEquals(array1.length, array2.length);
        for (int i=0; i<array1.length; i++) {
            assertEquals(array1[i], array2[i]);
        }
    }
}
