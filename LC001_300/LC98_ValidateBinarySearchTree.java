package LC001_300;
import java.util.*;
public class LC98_ValidateBinarySearchTree {
    /**
     * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
     *
     * A valid BST is defined as follows:
     *
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     *
     * @param root
     * @return
     */
    // S1: 定义
    // time = O(n), space = O(n)
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return dfs(root)[0] == 1 ? true : false;
    }

    private int[] dfs(TreeNode node) {
        int[] res = new int[]{1, node.val, node.val};

        if (node.left != null) {
            int[] t = dfs(node.left);
            if (t[0] == 0 || t[2] >= node.val) res[0] = 0;
            res[1] = Math.min(res[1], t[1]);
            res[2] = Math.max(res[2], t[2]);
        }
        if (node.right != null) {
            int[] t = dfs(node.right);
            if (t[0] == 0 || t[1] <= node.val) res[0] = 0;
            res[1] = Math.min(res[1], t[1]);
            res[2] = Math.max(res[2], t[2]);
        }
        return res;
    }

    // S2: Recursion
    // time = O(n), space = O(n)
    private TreeNode prev = null;
    public boolean isValidBST2(TreeNode root) {
        // corner case
        if (root == null) return true;

        if (!isValidBST(root.left)) return false;

        if (prev != null && prev.val >= root.val) return false;
        prev = root;
        return isValidBST(root.right);
    }
}
/**
 * 直接判断它的子树是否中序遍历有序
 * 在遍历的时候求下其左子树的最大值是否小于当前值，右子树的最小值是否大于当前值(定义)
 */