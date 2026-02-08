package LC3601_3900;
import java.util.*;
public class LC3831_MedianofaBinarySearchTreeLevel {
    /**
     * You are given the root of a Binary Search Tree (BST) and an integer level.
     *
     * The root node is at level 0. Each level represents the distance from the root.
     *
     * Return the median value of all node values present at the given level. If the level does not exist or contains no
     * nodes, return -1.
     *
     * The median is defined as the middle element after sorting the values at that level in non-decreasing order. If
     * the number of values at that level is even, return the upper median (the larger of the two middle elements after
     * sorting).
     *
     * Input: root = [4,null,5,null,7], level = 2
     * Output: 7
     *
     * Input: root = [6,3,8], level = 1
     * Output: 8
     *
     * Input: root = [2,1], level = 2
     * Output: -1
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 2 * 10^5].
     * 1 <= Node.val <= 10^6
     * 0 <= level <= 2 * 10^5
     * @param root
     * @param level
     * @return
     */
    // time = O(n), space = O(n)
    public int levelMedian(TreeNode root, int level) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int step = 0;
        List<Integer> p = new ArrayList<>();
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz-- > 0) {
                TreeNode t = q.poll();
                if (step == level) p.add(t.val);
                if (t.left != null) q.offer(t.left);
                if (t.right != null) q.offer(t.right);
            }
            step++;
            if (p.size() > 0) return p.get(p.size() / 2);
        }
        return -1;
    }
}