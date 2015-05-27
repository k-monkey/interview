package tree;

import java.util.LinkedList;

public class TreeNode {
    public static String NULL_SYMBOL = "#";
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        
        TreeNode other = (TreeNode) obj;
        if (this.val == other.val &&
            (this.left == other.left || this.left.equals(other.left)) &&
            (this.right == other.right || this.right.equals(other.right))) {
            return true;
        }
        else return false;
    }
    
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     * a post-ordered string representation of the tree 
     */
    @Override
    public String toString() {
        String leftStr = left==null?"":left.toString();
        String rightStr = right==null?"":right.toString();       
        return val + ", " + leftStr + ", " + rightStr;
    }
    
    public String[] toArray() {
        LinkedList<String> resultLst = new LinkedList<String>();
        LinkedList<TreeNode> nodeList = new LinkedList<TreeNode>();
        nodeList.add(this);
        TreeNode lvlMarker = new TreeNode(0);
        nodeList.add(lvlMarker);

        //a (tree) level is trivial when all the nodes
        //on this level are null
        boolean isLvlTrivial = true;
        while (nodeList.size() > 0) {
            TreeNode node = nodeList.pop();
            if (node == lvlMarker) {
                if (isLvlTrivial)
                    break;
                else {
                    nodeList.add(node);
                    isLvlTrivial = true;
                    continue;
                }
            }
            else if (node == null) {
                resultLst.add(NULL_SYMBOL);
                nodeList.add(null);
                nodeList.add(null);                
            }
            else {
                resultLst.add(node.val+"");         
                if (isLvlTrivial && (node.left != null || 
                        node.right != null)) 
                    isLvlTrivial = false;
                nodeList.add(node.left);
                nodeList.add(node.right);
            }
        }
        return resultLst.toArray(new String[]{});
    }
    
    public static TreeNode fromArray(String[] array) {
        if ( array == null || array.length == 0 || array[0] == NULL_SYMBOL)
            return null;
        
        LinkedList<TreeNode> nodeQ = new LinkedList<TreeNode>();
        TreeNode root = new TreeNode(Integer.parseInt(array[0]));
        nodeQ.add(root);
        int idx = 1;
        while (idx < array.length && nodeQ.size() > 0) {
            TreeNode node = nodeQ.pop();
            String element = array[idx++];
            if (element != NULL_SYMBOL) {
                node.left = new TreeNode(Integer.parseInt(element));
                nodeQ.add(node.left);
            }
            
            if (idx < array.length) {
                element = array[idx++];
                if (element != "#") {
                    node.right = new TreeNode(Integer.parseInt(element));
                    nodeQ.add(node.right);
                }
            }
        }
        return root;
    }
}