package LC1201_1500;
import java.util.*;
public class LC1457_PseudoPalindromicPathsinaBinaryTree {
    /**
     * Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be
     * pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
     *
     * Return the number of pseudo-palindromic paths going from the root node to leaf nodes.
     *
     * Input: root = [2,3,1,3,1,null,1]
     * Output: 2
     *
     * Input: root = [2,1,1,1,3,null,null,null,null,null,1]
     * Output: 1
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 105].
     * 1 <= Node.val <= 9
     */
    // time = O(n), space = O(h) = O(logn)
    int res = 0;
    public int pseudoPalindromicPaths (TreeNode root) {
        if (root == null) return 0;

        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode node, int path) {
        path ^= 1 << node.val;
        if (node.left == null && node.right == null) {
            if (isPalin(path)) res++;
            return;
        }

        if (node.left != null) dfs(node.left, path);
        if (node.right != null) dfs(node.right, path);
    }

    private boolean isPalin(int x) {
        int v = Integer.bitCount(x);
        return v <= 1;
    }
}
