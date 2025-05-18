package LC601_900;
import java.util.*;
public class LC623_AddOneRowtoTree {
    /**
     * Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v at the
     * given depth d. The root node is at depth 1.
     *
     * The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1, create two
     * tree nodes with value v as N's left subtree root and right subtree root. And N's original left subtree should be
     * the left subtree of the new left subtree root, its original right subtree should be the right subtree of the new
     * right subtree root. If depth d is 1 that means there is no depth d-1 at all, then create a tree node with value
     * v as the new root of the whole original tree, and the original tree is the new root's left subtree.
     *
     * Input:
     * A binary tree as following:
     *        4
     *      /   \
     *     2     6
     *    / \   /
     *   3   1 5
     *
     * v = 1
     *
     * d = 2
     *
     * Output:
     *        4
     *       / \
     *      1   1
     *     /     \
     *    2       6
     *   / \     /
     *  3   1   5
     *
     *  Note:
     * The given d is in range [1, maximum depth of the given tree + 1].
     * The given binary tree has at least one tree node.
     *
     * @param root
     * @param v
     * @param d
     * @return
     */
    // time = O(n), space = O(k) k: the number of maximum number of nodes at any level in the given tree
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        // corner case
        if (root == null) return root;

        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        for (int i = 0; i < d - 2; i++) {
            for (int j = q.size(); j > 0; j--) {
                TreeNode t = q.poll();
                if (t.left != null) q.offer(t.left);
                if (t.right != null) q.offer(t.right);
            }
        }

        while (!q.isEmpty()) {
            TreeNode t = q.poll();
            TreeNode left = new TreeNode(v);
            TreeNode right = new TreeNode(v);
            left.left = t.left;
            right.right = t.right;
            t.left = left;
            t.right = right;
        }
        return root;
    }

    // S2
    // time = O(n), space = O(n)
    int v, d;
    public TreeNode addOneRow2(TreeNode root, int val, int depth) {
        if (root == null) return root;

        v = val;
        d = depth;
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }
        dfs(root, 1);
        return root;
    }

    private void dfs(TreeNode node, int depth) {
        if (node == null) return;

        if (depth + 1 == d) {
            TreeNode l = new TreeNode(v);
            TreeNode r = new TreeNode(v);
            l.left = node.left;
            r.right = node.right;
            node.left = l;
            node.right = r;
        } else {
            dfs(node.left, depth + 1);
            dfs(node.right, depth + 1);
        }
    }
}
