package LC2700_3000;
import java.util.*;
public class LC2773_HeightofSpecialBinaryTree {
    /**
     * You are given a root which is the root of a binary tree with n nodes. The nodes of the binary tree are numbered
     * from 1 to n. Suppose the tree has k leaves in the following order: b1 < b2 < ... < bk.
     *
     * The leaves of this tree have a special property! That is, for every leaf bi, the following conditions hold:
     *
     * The right child of bi is bi + 1 if i < k, and b1 otherwise.
     * The left child of bi is bi - 1 if i > 1, and bk otherwise.
     * Return the height of the given tree.
     *
     * Note: The height of a binary tree is the length of the longest path from the root to any other vertex.
     *
     * Input: root = [1,2,3,null,null,4,5]
     * Output: 2
     *
     * Input: root = [1,2]
     * Output: 1
     *
     * Input: root = [1,2,3,null,null,4,null,5,6]
     * Output: 3
     *
     * Constraints:
     *
     * n == number of nodes in the tree
     * 2 <= n <= 10^4
     * 1 <= node.val <= n
     * The input is generated such that each node.val is unique.
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    int res = 0;
    public int heightOfTree(TreeNode root) {
        if (root == null) return 0;
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode node, int depth) {
        if (node == null) return;
        if (node.left != null && node.left.right != null) {
            if (node.left.right == node) {
                res = Math.max(res, depth);
                return;
            }
        }
        if (node.right != null && node.right.left != null) {
            if (node.right.left == node) {
                res = Math.max(res, depth);
                return;
            }
        }
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }
}