package LC1201_1500;

public class LC1373_MaximumSumBSTinBinaryTree {
    /**
     * Given a binary tree root, return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree
     * (BST).
     *
     * Assume a BST is defined as follows:
     *
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     *
     * Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
     * Output: 20
     *
     * Input: root = [4,3,null,1,2]
     * Output: 2
     *
     * Input: root = [-4,-2,-5]
     * Output: 0
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 4 * 10^4].
     * -4 * 10^4 <= Node.val <= 4 * 10^4
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    int res = 0;
    public int maxSumBST(TreeNode root) {
        dfs(root);
        return res;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1};

        int[] l = dfs(node.left);
        int[] r = dfs(node.right);

        int flag = 1;
        if (node.val > l[0] && node.val < r[1] && (l[3] & r[3]) == 1) {
            res = Math.max(res, node.val + l[2] + r[2]);
        } else flag = 0;

        int maxv = Math.max(node.val, Math.max(l[0], r[0]));
        int minv = Math.min(node.val, Math.min(l[1], r[1]));
        return new int[]{maxv, minv, node.val + l[2] + r[2], flag};
    }
}
/**
 * 子树必须要到叶子结点
 * Lmax < root.val< Rmin
 * 可以用递归来判断
 * 还需要返回每一个子树的总和
 * 每个点只会被遍历1次 => O(n)
 */