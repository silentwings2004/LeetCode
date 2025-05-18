package LC1201_1500;
import java.util.*;
public class LC1382_BalanceaBinarySearchTree {
    /**
     * Given a binary search tree, return a balanced binary search tree with the same node values.
     *
     * A binary search tree is balanced if and only if the depth of the two subtrees of every node never differ by more
     * than 1.
     *
     * If there is more than one answer, return any of them.
     *
     * Input: root = [1,null,2,null,3,null,4,null,null]
     * Output: [2,1,3,null,null,null,4]
     *
     * Constraints:
     *
     * The number of nodes in the tree is between 1 and 10^4.
     * The tree nodes will have distinct values between 1 and 10^5.
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    List<Integer> q;
    public TreeNode balanceBST(TreeNode root) {
        if (root == null) return root;
        q = new ArrayList<>();
        dfs(root);
        return dfs2(0, q.size() - 1);
    }

    private void dfs(TreeNode node) {
        if (node == null) return;

        dfs(node.left);
        q.add(node.val);
        dfs(node.right);
    }

    private TreeNode dfs2(int l, int r) {
        if (l > r) return null;

        int mid = l + r >> 1;
        TreeNode node = new TreeNode(q.get(mid));
        node.left = dfs2(l, mid - 1);
        node.right = dfs2(mid + 1, r);
        return node;
    }
}