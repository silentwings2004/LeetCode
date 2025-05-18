package LC901_1200;

public class LC1026_MaximumDifferenceBetweenNodeandAncestor {
    /**
     * Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b where
     * v = |a.val - b.val| and a is an ancestor of b.
     *
     * A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.
     *
     * Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
     * Output: 7
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [2, 5000].
     * 0 <= Node.val <= 10^5
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    int res = 0;
    public int maxAncestorDiff(TreeNode root) {
        if (root == null) return 0;

        dfs(root, root.val, root.val);
        return res;
    }

    private void dfs(TreeNode node, int min, int max) {
        if (node == null) return;

        max = Math.max(max, node.val);
        min = Math.min(min, node.val);
        res = Math.max(res, max - min);
        dfs(node.left, min, max);
        dfs(node.right, min, max);
    }
}
/**
 * 只跟祖先结点的最大值和最小值相关
 */
