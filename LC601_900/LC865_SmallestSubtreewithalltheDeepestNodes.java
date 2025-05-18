package LC601_900;

public class LC865_SmallestSubtreewithalltheDeepestNodes {
    /**
     * Given the root of a binary tree, the depth of each node is the shortest distance to the root.
     *
     * Return the smallest subtree such that it contains all the deepest nodes in the original tree.
     *
     * A node is called the deepest if it has the largest depth possible among any node in the entire tree.
     *
     * The subtree of a node is a tree consisting of that node, plus the set of all descendants of that node.
     *
     * Input: root = [3,5,1,6,2,0,8,null,null,7,4]
     * Output: [2,7,4]
     *
     * Input: root = [1]
     * Output: [1]
     *
     * Input: root = [0,1,3,null,2]
     * Output: [2]
     *
     * Constraints:
     *
     * The number of nodes in the tree will be in the range [1, 500].
     * 0 <= Node.val <= 500
     * The values of the nodes in the tree are unique.
     *
     *
     * Note: This question is the same as 1123: https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
     * @param root
     * @return
     */
    // S1: dfs
    // time = O(n), space = O(n)
    int deepest = 0;
    TreeNode res = null;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) return root;
        dfs(root, 0);
        return res;
    }

    private int dfs(TreeNode node, int depth) {
        deepest = Math.max(deepest, depth);
        if (node == null) return depth;

        int l = dfs(node.left, depth + 1);
        int r = dfs(node.right, depth + 1);
        if (l == deepest && r == deepest) res = node;
        return Math.max(l, r);
    }

    // S2:
    // time = O(n), space = O(n)
    public TreeNode subtreeWithAllDeepest2(TreeNode root) {
        if (root == null) return root;
        return dfs(root).node;
    }

    private Node dfs(TreeNode node) {
        if (node == null) return new Node(null, 0);

        Node l = dfs(node.left);
        Node r = dfs(node.right);
        if (l.d == r.d) return new Node(node, r.d + 1);
        if (l.d > r.d) return new Node(l.node, l.d + 1);
        return new Node(r.node, r.d + 1);
    }

    private class Node {
        private TreeNode node;
        private int d;
        public Node(TreeNode node, int d) {
            this.node = node;
            this.d = d;
        }
    }
}