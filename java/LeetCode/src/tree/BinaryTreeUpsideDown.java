package tree;

public class BinaryTreeUpsideDown {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null)
            return null;
        if (root.left == null)
            return root;
        
        TreeNode left = root.left;
        TreeNode result = upsideDownBinaryTree(left);
        left.left = root.right;
        left.right = root;
        return result;
    }
}
