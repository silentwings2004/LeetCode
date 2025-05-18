package LC001_300;
import java.util.*;
public class LC106_ConstructBinaryTreefromInorderandPostorderTraversal {
    /**
     * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and
     * postorder is the postorder traversal of the same tree, construct and return the binary tree.
     *
     * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
     * Output: [3,9,20,null,null,15,7]
     *
     * Constraints:
     *
     * 1 <= inorder.length <= 3000
     * postorder.length == inorder.length
     * -3000 <= inorder[i], postorder[i] <= 3000
     * inorder and postorder consist of unique values.
     * Each value of postorder also appears in inorder.
     * inorder is guaranteed to be the inorder traversal of the tree.
     * postorder is guaranteed to be the postorder traversal of the tree.
     *
     * @param inorder
     * @param postorder
     * @return
     */
    // time = O(n), space = O(n)
    HashMap<Integer, Integer> map;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        map = new HashMap<>();
        int n = inorder.length;
        for (int i = 0; i < n; i++) map.put(inorder[i], i);
        return dfs(inorder, 0, n - 1, postorder, 0, n - 1);
    }

    private TreeNode dfs(int[] a, int i, int j, int[] b, int x, int y) {
        if (i > j) return null;

        int r = map.get(b[y]);
        int left = r - i, right = j - r;
        TreeNode root = new TreeNode(b[y]);
        root.left = dfs(a, i, r - 1, b, x, x + r - i - 1);
        root.right = dfs(a, r + 1, j, b, x + r - i, y - 1);
        return root;
    }
}