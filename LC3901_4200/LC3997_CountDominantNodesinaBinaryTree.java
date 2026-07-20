package LC3901_4200;

public class LC3997_CountDominantNodesinaBinaryTree {
    /**
     * You are given the root of a complete binary tree.
     *
     * A node x is called dominant if its value is equal to the maximum value among all nodes in the subtree rooted at x.
     *
     * Return the number of dominant nodes in the tree.
     *
     * Input: root = [5,3,8,2,4,7,1]
     * Output: 5
     *
     * Input: root = [1,2,3,1,2]
     * Output: 4
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 10^5].
     * 1 <= Node.val <= 10^9
     * The tree is guaranteed to be a complete binary tree.
     * @param root
     * @return
     */
    // time = O(n), space = O(logn)
    int res;
    public int countDominantNodes(TreeNode root) {
        dfs(root);
        return res;
    }

    private int dfs(TreeNode node) {
        int l = node.left == null ? 0 : dfs(node.left);
        int r = node.right == null ? 0 : dfs(node.right);
        int mx = Math.max(node.val, Math.max(l, r));
        if (mx == node.val) res++;
        return mx;
    }
}