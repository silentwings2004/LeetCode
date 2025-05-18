package LC901_1200;

public class LC979_DistributeCoinsinBinaryTree {
    /**
     * You are given the root of a binary tree with n nodes where each node in the tree has node.val coins. There are n
     * coins in total throughout the whole tree.
     *
     * In one move, we may choose two adjacent nodes and move one coin from one node to another. A move may be from
     * parent to child, or from child to parent.
     *
     * Return the minimum number of moves required to make every node have exactly one coin.
     *
     * Input: root = [3,0,0]
     * Output: 2
     *
     * Input: root = [0,3,0]
     * Output: 3
     *
     * Constraints:
     *
     * The number of nodes in the tree is n.
     * 1 <= n <= 100
     * 0 <= Node.val <= n
     * The sum of all Node.val is n.
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    public int distributeCoins(TreeNode root) {
        return dfs(root)[2];
    }

    private int[] dfs(TreeNode node) {
        if (node == null) return new int[]{0, 0, 0};

        int[] l = dfs(node.left);
        int[] r = dfs(node.right);
        int x = l[0] + r[0] + 1, y = l[1] + r[1] + node.val;
        return new int[]{x, y, Math.abs(x - y) + l[2] + r[2]};
    }
}
/**
 * 考虑每条边对答案的贡献
 * 节点数：x
 * 金币数：y
 * x < y => y - x
 * x > y => x - y
 * x = y
 * 每条边对答案的贡献 = |x - y|
 */