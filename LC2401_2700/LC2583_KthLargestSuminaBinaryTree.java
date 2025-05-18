package LC2401_2700;
import java.util.*;
public class LC2583_KthLargestSuminaBinaryTree {
    /**
     * You are given the root of a binary tree and a positive integer k.
     *
     * The level sum in the tree is the sum of the values of the nodes that are on the same level.
     *
     * Return the kth largest level sum in the tree (not necessarily distinct). If there are fewer than k levels in the
     * tree, return -1.
     *
     * Note that two nodes are on the same level if they have the same distance from the root.
     *
     * Input: root = [5,8,9,2,1,3,7,4,6], k = 2
     * Output: 13
     *
     * Input: root = [1,2,null,3], k = 1
     * Output: 3
     *
     * Constraints:
     *
     * The number of nodes in the tree is n.
     * 2 <= n <= 10^5
     * 1 <= Node.val <= 10^6
     * 1 <= k <= n
     * @param root
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long kthLargestLevelSum(TreeNode root, int k) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        List<Long> nums = new ArrayList<>();
        while (!q.isEmpty()) {
            int size = q.size();
            long sum = 0;
            while (size-- > 0) {
                TreeNode t = q.poll();
                sum += t.val;
                if (t.left != null) q.offer(t.left);
                if (t.right != null) q.offer(t.right);
            }
            nums.add(sum);
        }
        if (nums.size() < k) return -1;
        Collections.sort(nums, (o1, o2) -> Long.compare(o2, o1));
        return nums.get(k - 1);
    }
}