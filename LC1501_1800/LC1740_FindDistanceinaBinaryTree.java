package LC1501_1800;

public class LC1740_FindDistanceinaBinaryTree {
    /**
     * Given the root of a binary tree and two integers p and q, return the distance between the nodes of value p and
     * value q in the tree.
     *
     * The distance between two nodes is the number of edges on the path from one to the other.
     *
     * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 0
     * Output: 3
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 10^4].
     * 0 <= Node.val <= 10^9
     * All Node.val are unique.
     * p and q are values in the tree.
     * @param root
     * @param p
     * @param q
     * @return
     */
    // S1:
    // time = O(n), space = O(n)
    int ans = 0;
    public int findDistance(TreeNode root, int p, int q) {
        TreeNode ancestor = lca(root, p, q);
        dfs(ancestor, 0, p, q);
        return ans;
    }

    private TreeNode lca(TreeNode root, int p, int q) {
        if (root == null) return root;
        if (root.val == p || root.val == q) return root;

        TreeNode l = lca(root.left, p, q);
        TreeNode r = lca(root.right, p, q);
        if (l != null && r != null) return root;
        return l == null ? r : l;
    }

    private void dfs(TreeNode node, int d, int p, int q) {
        if (node == null) return;

        if (node.val == p || node.val == q) ans += d;
        dfs(node.left, d + 1, p, q);
        dfs(node.right, d + 1, p, q);
    }

    // S2
    // time = O(n), space = O(n)
    int res = -1;
    public int findDistance2(TreeNode root, int p, int q) {
        dfs(root, p, q);
        return res;
    }

    private int[] dfs(TreeNode node, int p, int q) {
        // base case
        if (node == null) return new int[]{-1, -1};
        if (res != -1) return new int[]{-1, -1};

        int[] ans1 = dfs(node.left, p, q);
        int[] ans2 = dfs(node.right, p, q);

        int dp = -1, dq = -1;
        if (ans1[0] != -1) dp = ans1[0] + 1;
        else if (ans2[0] != -1) dp = ans2[0] + 1;
        else if (node.val == p) dp = 0;

        if (ans1[1] != -1) dq = ans1[1] + 1;
        else if (ans2[1] != -1) dq = ans2[1] + 1;
        else if (node.val == q) dq = 0;

        if (dp != -1 && dq != -1 && res == -1) res = dp + dq; // 注意千万别忘了判断res == -1!!!
        return new int[]{dp, dq};
    }

    // S3
    // time = O(n), space = O(n)
    int da = -1, dp = -1, dq = -1;
    public int findDistance3(TreeNode root, int p, int q) {
        if (p == q) return 0;
        lca(root, p, q, 0);
        return (dp - da) + (dq - da);
    }

    private int lca(TreeNode node, int p, int q, int d) {
        if (node == null) return 0;

        int l = lca(node.left, p, q, d + 1);
        int r = lca(node.right, p, q, d + 1);
        int self = node.val == p || node.val == q ? 1 : 0;
        if (node.val == p) dp = d;
        if (node.val == q) dq = d;
        int sum = l + r + self;
        if (sum == 2 && da == -1) da = d;
        return sum;
    }
}
/**
 * ref: LC236
 * 所谓的拐点就是lca
 * lowest common ancestor
 * 本质上就是求两者分别到lca的距离之和
 * 更简洁的做法就是纯递归，不需要使用额外的空间
 * int dfs(node): whether node is the LCA of p & q
 *            how many p & q are contained in the subtree of node
 * 还要知道lca到p和q的距离
 * pair<int,int> dfs(node): {the distance between p & node, the distance between q & node}
 * 当这2个分量都不为-1时，表示找到了lca
 */
