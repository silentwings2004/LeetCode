package LC901_1200;

public class LC993_CousinsinBinaryTree {
    /**
     * Given the root of a binary tree with unique values and the values of two different nodes of the tree x and y,
     * return true if the nodes corresponding to the values x and y in the tree are cousins, or false otherwise.
     *
     * Two nodes of a binary tree are cousins if they have the same depth with different parents.
     *
     * Note that in a binary tree, the root node is at the depth 0, and children of each depth k node are at the depth
     * k + 1.
     *
     * Input: root = [1,2,3,4], x = 4, y = 3
     * Output: false
     *
     * Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
     * Output: true
     *
     * Input: root = [1,2,3,null,4], x = 2, y = 3
     * Output: false
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [2, 100].
     * 1 <= Node.val <= 100
     * Each node has a unique value.
     * x != y
     * x and y are exist in the tree.
     * @param root
     * @param x
     * @param y
     * @return
     */
    // time = O(n), space = O(n)
    public boolean isCousins(TreeNode root, int x, int y) {
        int[] a = dfs(root, x, -1, 0);
        int[] b = dfs(root, y, -1, 0);
        return a[0] != b[0] && a[1] == b[1];
    }

    private int[] dfs(TreeNode node, int x, int fa, int depth) {
        if (node == null) return new int[]{0, 0};
        if (node.val == x) return new int[]{fa, depth};
        int[] l = dfs(node.left, x, node.val, depth + 1);
        int[] r = dfs(node.right, x, node.val, depth + 1);
        return new int[]{l[0] + r[0], l[1] + r[1]};
    }
}