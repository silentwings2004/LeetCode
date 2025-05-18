package LC601_900;
import java.util.*;
public class LC889_ConstructBinaryTreefromPreorderandPostorderTraversal {
    /**
     * Given two integer arrays, preorder and postorder where preorder is the preorder traversal of a binary tree of
     * distinct values and postorder is the postorder traversal of the same tree, reconstruct and return the binary tree.
     *
     * If there exist multiple answers, you can return any of them.
     *
     * Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
     * Output: [1,2,3,4,5,6,7]
     *
     * Input: preorder = [1], postorder = [1]
     * Output: [1]
     *
     * Constraints:
     *
     * 1 <= preorder.length <= 30
     * 1 <= preorder[i] <= preorder.length
     * All the values of preorder are unique.
     * postorder.length == preorder.length
     * 1 <= postorder[i] <= postorder.length
     * All the values of postorder are unique.
     * It is guaranteed that preorder and postorder are the preorder traversal and postorder traversal of the same
     * binary tree.
     * @param preorder
     * @param postorder
     * @return
     */
    // time = O(n), space = O(n)
    HashMap<Integer, Integer> map;
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        map = new HashMap<>();
        int n = preorder.length;
        for (int i = 0; i < n; i++) map.put(postorder[i], i);
        return build(preorder, postorder, 0, n - 1, 0, n - 1);
    }

    private TreeNode build(int[] pre, int[] post, int a, int b, int x, int y) {
        if (a > b) return null;

        TreeNode root = new TreeNode(pre[a]);
        if (a == b) return root;
        int k = map.get(pre[a + 1]);
        root.left = build(pre, post, a + 1, a + 1 + k - x, x, k);
        root.right = build(pre, post, a + 2 + k - x, b, k + 1, y - 1);
        return root;
    }
}