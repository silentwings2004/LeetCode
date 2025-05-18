package LC001_300;
import java.util.*;
public class LC111_MinimumDepthofBinaryTree {
    /**
     * Given a binary tree, find its minimum depth.
     *
     * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
     *
     * Note: A leaf is a node with no children.
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [0, 105].
     * -1000 <= Node.val <= 1000
     * @param root
     * @return
     */
    // S1: dfs
    // time = O(n), space = O(n)
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        if (root.left != null && root.right != null) return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        if (root.left != null) return minDepth(root.left) + 1;
        return minDepth(root.right) + 1;
    }

    // S2: bfs
    // time = O(n), space = O(n)
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
                if (cur.left == null && cur.right == null) return step + 1;
            }
            step++;
        }
        return -1;
    }
}
/**
 * f(u):
 * 1. u是叶结点
 * 2. u不是叶结点
 * (1) a,b不空 => min(f(a),f(b)) + 1
 * (2) a不空，b空 => f(a) + 1
 * (3) a空，b不空 => f(b) + 1
 */
