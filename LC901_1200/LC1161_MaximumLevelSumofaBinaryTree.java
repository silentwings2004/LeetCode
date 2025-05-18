package LC901_1200;
import java.util.*;
public class LC1161_MaximumLevelSumofaBinaryTree {
    /**
     * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
     *
     * Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
     *
     * Input: root = [1,7,0,7,-8,null,null]
     * Output: 2
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 104].
     * -10^5 <= Node.val <= 10^5
     * @param root
     * @return
     */
    // S1: bfs
    // time = O(n), space = O(n)
    public int maxLevelSum(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int step = 1, max = Integer.MIN_VALUE, res = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int sum = 0;
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                sum += cur.val;
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            if (max < sum) {
                max = sum;
                res = step;
            }
            step++;
        }
        return res;
    }

    // S2: dfs
    // time = O(n), space = O(n)
    List<Integer> sum;
    public int maxLevelSum2(TreeNode root) {
        if (root == null) return 0;

        sum = new ArrayList<>();
        dfs(root, 1);

        int res = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < sum.size(); i++) {
            if (max < sum.get(i)) {
                max = sum.get(i);
                res = i + 1;
            }
        }
        return res;
    }

    private void dfs(TreeNode node, int depth) {
        if (node == null) return;

        if (sum.size() < depth) sum.add(0);
        sum.set(depth - 1, sum.get(depth - 1) + node.val);

        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }
}
