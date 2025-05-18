package LC001_300;
import java.util.*;
public class LC110_BalancedBinaryTree {
    /**
     * Given a binary tree, determine if it is height-balanced.
     *
     * For this problem, a height-balanced binary tree is defined as:
     *
     * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [0, 5000].
     * -10^4 <= Node.val <= 10^4
     *
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    boolean res;
    public boolean isBalanced(TreeNode root) {
        res = true;
        dfs(root);
        return res;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int lh = dfs(node.left);
        int rh = dfs(node.right);
        if (Math.abs(lh - rh) > 1) res = false;
        return Math.max(lh, rh) + 1;
    }
}