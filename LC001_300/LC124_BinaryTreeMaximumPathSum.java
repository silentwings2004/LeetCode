package LC001_300;
import java.util.*;
public class LC124_BinaryTreeMaximumPathSum {
    /**
     * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge
     * connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass
     * through the root.
     *
     * The path sum of a path is the sum of the node's values in the path.
     *
     * Given the root of a binary tree, return the maximum path sum of any path.
     *
     * Input: root = [1,2,3]
     * Output: 6
     *
     * Input: root = [-10,9,20,null,null,15,7]
     * Output: 42
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 3 * 10^4].
     * -1000 <= Node.val <= 1000
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return res;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int l = dfs(node.left);
        int r = dfs(node.right);
        l = Math.max(l, 0);
        r = Math.max(r, 0);
        int sum = l + r + node.val;
        res = Math.max(res, sum);
        return Math.max(l, r) + node.val;
    }
}
/**
 * break down 2个问题
 * 这个结点一直往下走，路径最大和，不带拐弯的
 * MaxPath(node, AsTurn) -> node.val + max(0, MaxPath(leftNode, noTurn)) + Math.max(0, MaxPath(rightNode, noTurn))
 * dfs写个不带拐弯的从当前结点往下走，最多能走多少
 */
