package LC001_300;
import java.util.*;
public class LC105_ConstructBinaryTreefromPreorderandInorderTraversal {
    /**
     * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and
     * inorder is the inorder traversal of the same tree, construct and return the binary tree.
     *
     * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * Output: [3,9,20,null,null,15,7]
     *
     * Constraints:
     *
     * 1 <= preorder.length <= 3000
     * inorder.length == preorder.length
     * -3000 <= preorder[i], inorder[i] <= 3000
     * preorder and inorder consist of unique values.
     * Each value of inorder also appears in preorder.
     * preorder is guaranteed to be the preorder traversal of the tree.
     * inorder is guaranteed to be the inorder traversal of the tree.
     *
     * @param preorder
     * @param inorder
     * @return
     */
    // time = O(n), space - O(n)
    HashMap<Integer, Integer> map;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap<>();
        int n = inorder.length;
        for (int i = 0; i < n; i++) map.put(inorder[i], i);
        return dfs(preorder, 0, n - 1, inorder, 0, n - 1);
    }

    private TreeNode dfs(int[] a, int i, int j, int[] b, int x, int y) {
        if (i > j) return null;

        int r = map.get(a[i]);
        int left = r - x, right = y - r;
        TreeNode root = new TreeNode(a[i]);
        root.left = dfs(a, i + 1, i + left, b, x, r - 1);
        root.right = dfs(a, i + left + 1, j, b, r + 1, y);
        return root;
    }
}