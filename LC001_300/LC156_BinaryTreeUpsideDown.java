package LC001_300;
import java.util.*;
public class LC156_BinaryTreeUpsideDown {
    /**
     * Given the root of a binary tree, turn the tree upside down and return the new root.
     *
     * You can turn a binary tree upside down with the following steps:
     *
     * The original left child becomes the new root.
     * The original root becomes the new right child.
     * The original right child becomes the new left child.
     *
     * The mentioned steps are done level by level, it is guaranteed that every node in the given tree has either 0
     * or 2 children.
     *
     * Input: root = [1,2,3,4,5]
     * Output: [4,5,2,null,null,3,1]
     *
     * Constraints:
     *
     * The number of nodes in the tree will be in the range [0, 10].
     * 1 <= Node.val <= 10
     * Every node has either 0 or 2 children.
     * @param root
     * @return
     */
    // S1: dfs
    // time = O(n), space = O(n)
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) return root;

        TreeNode nh = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = root.right = null;
        return nh;
    }

    // S2: iteration
    // time = O(n), space = O(n)
    final int N = 15;
    public TreeNode upsideDownBinaryTree2(TreeNode root) {
        if (root == null || root.left == null) return root;

        TreeNode[] stk = new TreeNode[N];
        int tt = 0;
        TreeNode cur = root, nh = null;
        while (cur != null || tt > 0) {
            if (cur != null) {
                stk[++tt] = cur;
                cur = cur.left;
            } else {
                cur = stk[tt--];
                if (nh == null) nh = cur;
                cur.left = tt > 0 ? stk[tt].right : null;
                cur.right = tt > 0 ? stk[tt] : null;
                if (tt > 0) stk[tt].left = stk[tt].right = null;
                cur = null;
            }
        }
        return nh;
    }
}
