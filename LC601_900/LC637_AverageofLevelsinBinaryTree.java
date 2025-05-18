package LC601_900;
import java.util.*;
public class LC637_AverageofLevelsinBinaryTree {
    /**
     * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
     *
     * Input:
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * Output: [3, 14.5, 11]
     *
     * Note:
     * The range of node's value is in the range of 32-bit signed integer.
     * @param root
     * @return
     */
    // time = O(n), space = O(k) k: the maximum number of nodes at any level in the input tree
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode t = q.poll();
                sum += t.val;
                if (t.left != null) q.offer(t.left);
                if (t.right != null) q.offer(t.right);
            }
            res.add(sum / size);
        }
        return res;
    }
}
/**
 * 大部分tree的题都用dfs来做，只有少数涉及到层级遍历的，我们用bfs。
 */
