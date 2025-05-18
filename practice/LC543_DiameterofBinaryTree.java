package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: DiameterofBinaryTree
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 543. Diameter of Binary Tree
 */
public class LC543_DiameterofBinaryTree {
    /**
     * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is
     * the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
     *
     * Example:
     * Given a binary tree
     *           1
     *          / \
     *         2   3
     *        / \
     *       4   5
     * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
     *
     * Note: The length of path between two nodes is represented by the number of edges between them.
     *
     * @param root
     * @return
     */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    // DFS
    // time = O(n) n为二叉树的节点数。遍历一棵二叉树的时间复杂度，每个结点只被访问一次。
    // space = O(k) k为二叉树的高度。由于递归函数在递归过程中需要为每一层递归函数分配栈空间，所以这里需要额外的空间且该空间取决于递归的深度，
    // 而递归的深度显然为二叉树的高度，并且每次递归调用的函数里又只用了常数个变量，所以所需空间复杂度为O(height) = O(k)
    int res;
    public int diameterOfBinaryTree(TreeNode root) {
        // corner case
        if (root == null) return 0;
        dfs(root);
        return res - 1;
    }

    private int dfs(TreeNode root) {
        // base case
        if (root == null) return 0;
        int dl = dfs(root.left);
        int dr = dfs(root.right);
        res = Math.max(res, dl + dr + 1);
        return Math.max(dl, dr) + 1;
    }
}
