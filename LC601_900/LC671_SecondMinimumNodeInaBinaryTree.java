package LC601_900;

public class LC671_SecondMinimumNodeInaBinaryTree {
    /**
     * Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this
     * tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value
     * among its two sub-nodes. More formally, the property root.val = min(root.left.val, root.right.val) always holds.
     *
     * Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in
     * the whole tree.
     *
     * If no such second minimum value exists, output -1 instead.
     *
     * Input: root = [2,2,5,null,null,5,7]
     * Output: 5
     *
     * Input: root = [2,2,2]
     * Output: -1
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 25].
     * 1 <= Node.val <= 2^31 - 1
     * root.val == min(root.left.val, root.right.val) for each internal node of the tree.
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    long d1, d2;
    public int findSecondMinimumValue(TreeNode root) {
         d1 = d2 = (long) 1e18;
         dfs(root);
         if (d2 == 1e18) return -1;
         return (int) d2;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;
        if (node.val < d1) {
            d2 = d1;
            d1 = node.val;
        } else if (node.val > d1 && node.val < d2) d2 = node.val;
        dfs(node.left);
        dfs(node.right);
    }
}