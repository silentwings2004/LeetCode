package LC1201_1500;

public class LC1214_TwoSumBSTs {
    /**
     * Given the roots of two binary search trees, root1 and root2, return true if and only if there is a node in the
     * first tree and a node in the second tree whose values sum up to a given integer target.
     *
     * Input: root1 = [2,1,4], root2 = [1,0,3], target = 5
     * Output: true
     *
     * Input: root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
     * Output: false
     *
     * Constraints:
     *
     * The number of nodes in each tree is in the range [1, 5000].
     * -10^9 <= Node.val, target <= 10^9
     * @param root1
     * @param root2
     * @param target
     * @return
     */
    // S1
    // time = O(m + n), space = O(m + n)
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null || root2 == null) return false;
        if (root1.val + root2.val == target) return true;
        if (root1.val + root2.val < target) {
            return twoSumBSTs(root1.right, root2, target) || twoSumBSTs(root1, root2.right, target);
        } else {
            return twoSumBSTs(root1.left, root2, target) || twoSumBSTs(root1, root2.left, target);
        }
    }

    // S2
    // time = O(mlogn), space = O(logm + logn)
    public boolean twoSumBSTs2(TreeNode root1, TreeNode root2, int target) {
        return dfs(root1, root2, target);
    }

    private boolean dfs(TreeNode n1, TreeNode n2, int t) {
        if (n1 == null) return false;
        if (find(n2, t - n1.val)) return true;
        return dfs(n1.left, n2, t) || dfs(n1.right, n2, t);
    }

    private boolean find(TreeNode node, int t) {
        if (node == null) return false;
        if (node.val == t) return true;
        if (node.val < t) return find(node.right, t);
        return find(node.left, t);
    }
}