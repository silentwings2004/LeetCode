package LC301_600;

public class LC530_MinimumAbsoluteDifferenceinBST {
    /**
     * Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any
     * two different nodes in the tree.
     *
     * Input: root = [4,2,6,1,3]
     * Output: 1
     *
     * Input: root = [1,0,48,null,null,12,49]
     * Output: 1
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [2, 104].
     * 0 <= Node.val <= 10^5
     *
     * Note: This question is the same as 783: https://leetcode.com/problems/minimum-distance-between-bst-nodes/
     */
    // time = O(n), space = O(1)
    int res = (int) 1e8, last = -1;
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return res;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;

        dfs(node.left);
        if (last != -1) res = Math.min(res, node.val - last);
        last = node.val;
        dfs(node.right);
    }
}