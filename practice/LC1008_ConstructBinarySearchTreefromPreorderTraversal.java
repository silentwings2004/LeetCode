package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ConstructBinarySearchTreefromPreorderTraversal
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 1008. Construct Binary Search Tree from Preorder Traversal
 */
public class LC1008_ConstructBinarySearchTreefromPreorderTraversal {
    /**
     * Return the root node of a binary search tree that matches the given preorder traversal.
     *
     * (Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value
     * < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal
     * displays the value of the node first, then traverses node.left, then traverses node.right.)
     *
     *
     *
     * Example 1:
     *
     * Input: [8,5,1,7,10,12]
     * Output: [8,5,10,1,7,null,12]
     *
     *
     *
     * Note:
     *
     * 1 <= preorder.length <= 100
     * The values of preorder are distinct.
     * @param preorder
     * @return
     */
    // time = O(n), space = O(n)
    public TreeNode bstFromPreorder(int[] preorder) {
        // corner case
        if (preorder == null || preorder.length == 0) return null;
        return dfs(preorder, 0, preorder.length - 1);
    }

    private TreeNode dfs(int[] preorder, int start, int end) {
        // base case
        if (start > end) return null;

        TreeNode root = new TreeNode(preorder[start]);
        int idx = start + 1;
        while (idx <= end) {
            if (preorder[idx] > root.val) break;
            idx++;
        }
        root.left = dfs(preorder, start + 1, idx - 1);
        root.right = dfs(preorder, idx, end);
        return root;
    }
}
