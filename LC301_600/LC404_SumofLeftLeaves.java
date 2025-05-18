package LC301_600;

public class LC404_SumofLeftLeaves {
    /**
     * Given the root of a binary tree, return the sum of all left leaves.
     *
     * Input: root = [3,9,20,null,null,15,7]
     * Output: 24
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 1000].
     * -1000 <= Node.val <= 1000
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    private int res = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        dfs(root);
        return res;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;
        if (node.left != null) {
            if (node.left.left == null && node.left.right == null) res += node.left.val;
        }
        dfs(node.left);
        dfs(node.right);
    }
}