package LC901_1200;
import java.util.*;
public class LC1080_InsufficientNodesinRoottoLeafPaths {
    /**
     * Given the root of a binary tree and an integer limit, delete all insufficient nodes in the tree simultaneously,
     * and return the root of the resulting binary tree.
     *
     * A node is insufficient if every root to leaf path intersecting this node has a sum strictly less than limit.
     *
     * A leaf is a node with no children.
     *
     * Input: root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
     * Output: [1,2,3,4,null,null,7,8,9,null,14]
     *
     * Input: root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22
     * Output: [5,4,8,11,null,17,4,7,null,null,null,5]
     *
     * Input: root = [1,2,-3,-5,null,4,null], limit = -1
     * Output: [1,null,-3,4]
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 5000].
     * -10^5 <= Node.val <= 10^5
     * -10^9 <= limit <= 10^9
     * @param root
     * @param limit
     * @return
     */
    // S1: dfs
    // time = O(n), space = O(n)
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if (root == null) return root;
        if (root.left == null && root.right == null) return root.val < limit ? null : root;
        root.left = sufficientSubset(root.left, limit - root.val);
        root.right = sufficientSubset(root.right, limit - root.val);
        return root.left == root.right ? null : root;
    }

    // S2: dfs
    // time = O(n), space = O(n)
    public TreeNode sufficientSubset2(TreeNode root, int limit) {
        if (root == null) return root;
        return dfs(root, 0, limit);
    }

    private TreeNode dfs(TreeNode node, int sum, int limit) {
        sum += node.val;
        if (node.left == null && node.right == null) {
            if (sum < limit) node = null;
        } else {
            if (node.left != null) node.left = dfs(node.left, sum, limit);
            if (node.right != null) node.right = dfs(node.right, sum, limit);
            if (node.left == null && node.right == null) node = null;
        }
        return node;
    }

    // S3
    // time = O(n), space = O(n)
    final int INF = (int) 1e8;
    HashMap<TreeNode, Integer> map;
    public TreeNode sufficientSubset3(TreeNode root, int limit) {
        map = new HashMap<>();
        if (root == null) return root;
        dfs(root);
        if (map.get(root) < limit) return null;
        dfs2(root, 0, limit);
        return root;
    }

    private void dfs2(TreeNode node, int sum, int limit) {
        if (node == null) return;
        sum += node.val;
        if (node.left != null && sum + map.get(node.left) < limit) node.left = null;
        if (node.right != null && sum + map.get(node.right) < limit) node.right = null;
        if (node.left != null) dfs2(node.left, sum, limit);
        if (node.right != null) dfs2(node.right, sum, limit);
    }

    private int dfs(TreeNode node) {
        if (node == null) return -INF;
        int l = dfs(node.left);
        int r = dfs(node.right);
        int t = 0;
        if (l == -INF && r == -INF) t = node.val;
        else if (l == -INF) t = r + node.val;
        else if (r == -INF) t = l + node.val;
        else t = Math.max(l, r) + node.val;
        map.put(node, t);
        return t;
    }
}