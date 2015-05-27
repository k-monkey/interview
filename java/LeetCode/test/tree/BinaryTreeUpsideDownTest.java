package tree;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class BinaryTreeUpsideDownTest {
    @Test
    public void testUpsideDownBinaryTree() {
        BinaryTreeUpsideDown solution = new BinaryTreeUpsideDown();
        String[] array1 = { "1", "2", "3", "4", "5", "#", "#" };
        TreeNode tn = TreeNode.fromArray(array1);
        TreeNode result = solution.upsideDownBinaryTree(tn);
        String[] array2 = { "4", "5", "2", "#", "#", "3", "1" };
        assertEquals(TreeNode.fromArray(array2), result);
    }
}
