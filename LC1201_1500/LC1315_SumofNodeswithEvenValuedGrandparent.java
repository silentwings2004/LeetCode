package LC1201_1500;

public class LC1315_SumofNodeswithEvenValuedGrandparent {
    /**
     * Given the root of a binary tree, return the sum of values of nodes with an even-valued grandparent. If there are
     * no nodes with an even-valued grandparent, return 0.
     *
     * A grandparent of a node is the parent of its parent if it exists.
     *
     * Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
     * Output: 18
     *
     * Input: root = [1]
     * Output: 0
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 10^4].
     * 1 <= Node.val <= 100
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    int res = 0;
    public int sumEvenGrandparent(TreeNode root) {
        dfs(root, null, null);
        return res;
    }

    private void dfs(TreeNode node, TreeNode p, TreeNode pp) {
        if (node == null) return;
        if (pp != null && pp.val % 2 == 0) res += node.val;
        dfs(node.left, node, p);
        dfs(node.right, node, p);
    }
}