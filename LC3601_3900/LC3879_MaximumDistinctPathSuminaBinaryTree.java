package LC3601_3900;
import java.util.*;
public class LC3879_MaximumDistinctPathSuminaBinaryTree {
    /**
     * You are given the root of a binary tree, where each node contains an integer value.
     *
     * A valid path in the tree is a sequence of connected nodes such that:
     *
     * The path can start and end at any node in the tree.
     * The path does not need to pass through the root.
     * All node values along the path are distinct.
     * Return an integer denoting the maximum possible sum of node values among all valid paths.
     *
     * Input: root = [2,2,1]
     * Output: 3
     *
     * Input: root = [1,-2,5,null,null,3,5]
     * Output: 9
     *
     * Input: root = [4,6,6,null,null,null,9]
     * Output: 19
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 1000].
     * -1000 <= Node.val <= 1000
     * @param root
     * @return
     */
    // time = O(n^2), space = O(n)
    HashMap<TreeNode, TreeNode> map;
    List<TreeNode> q;
    int res;
    public int maxSum(TreeNode root) {
        map = new HashMap<>();
        q = new ArrayList<>();
        res = Integer.MIN_VALUE;

        build(root, null);
        boolean[] st = new boolean[2010];
        for (TreeNode node : q) dfs(node, null, st, 0);
        return res;
    }

    private void build(TreeNode node, TreeNode p) {
        if (node == null) return;
        map.put(node, p);
        q.add(node);
        build(node.left, node);
        build(node.right, node);
    }

    private void dfs(TreeNode node, TreeNode prev, boolean[] st, int sum) {
        int x = node.val + 1000;
        if (st[x]) return;
        st[x] = true;
        int ns = sum + node.val;
        res = Math.max(res, ns);

        TreeNode p = map.get(node);
        if (node.left != null && node.left != prev && !st[node.left.val + 1000]) dfs(node.left, node, st, ns);
        if (node.right != null && node.right != prev && !st[node.right.val + 1000]) dfs(node.right, node, st, ns);
        if (p != null && p != prev && !st[p.val + 1000]) dfs(p, node, st, ns);
        st[x] = false;
    }
}