package LC301_600;

public class LC513_FindBottomLeftTreeValue {
    /**
     * Given the root of a binary tree, return the leftmost value in the last row of the tree.
     *
     * Input: root = [2,1,3]
     * Output: 1
     *
     * Input: root = [1,2,3,4,null,5,6,null,null,7]
     * Output: 7
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 104].
     * -2^31 <= Node.val <= 2^31 - 1
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    int res = 0, deepest = 0;
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) return 0;

        dfs(root, 1);
        return res;
    }

    private void dfs(TreeNode node, int d) {
        if (node == null) return;
        if (d > deepest) {
            deepest = d;
            res = node.val;
        }

        dfs(node.left, d + 1);
        dfs(node.right, d + 1);
    }
}