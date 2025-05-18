package LC1201_1500;

public class LC1372_LongestZigZagPathinaBinaryTree {
    /**
     * You are given the root of a binary tree.
     *
     * A ZigZag path for a binary tree is defined as follow:
     *
     * Choose any node in the binary tree and a direction (right or left).
     * If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
     * Change the direction from right to left or from left to right.
     * Repeat the second and third steps until you can't move in the tree.
     * Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).
     *
     * Return the longest ZigZag path contained in that tree.
     *
     * Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
     * Output: 3
     *
     * Input: root = [1,1,1,null,1,null,null,1,1,null,1]
     * Output: 4
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 5 * 10^4].
     * 1 <= Node.val <= 100
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    int res = 0;
    public int longestZigZag(TreeNode root) {
        if (root == null) return 0;

        dfs(root, -1);
        return res;
    }

    private int dfs(TreeNode node, int dir) {
        if (node == null) return 0;

        int l = dfs(node.left, 0);
        int r = dfs(node.right, 1);

        res = Math.max(res, Math.max(l, r));
        if (dir == 0) return r + 1;
        return l + 1;
    }
}
