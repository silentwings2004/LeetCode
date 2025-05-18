package LC1201_1500;
import java.util.*;
public class LC1339_MaximumProductofSplittedBinaryTree {
    /**
     * Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the
     * product of the sums of the subtrees is maximized.
     *
     * Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo
     * 10^9 + 7.
     *
     * Note that you need to maximize the answer before taking the mod and not after taking it.
     *
     * Input: root = [1,2,3,4,5,6]
     * Output: 110
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [2, 5 * 10^4].
     * 1 <= Node.val <= 10^4
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    long tot, res;
    public int maxProduct(TreeNode root) {
        tot = dfs(root);
        dfs(root);
        long mod = (long)(1e9 + 7);
        return (int)(res % mod);
    }

    private long dfs(TreeNode node) {
        if (node == null) return 0;

        long l = dfs(node.left);
        long r = dfs(node.right);
        long sum = l + r + node.val;
        res = Math.max(res, sum * (tot - sum));
        return sum;
    }
}
