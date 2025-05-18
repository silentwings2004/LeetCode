package LC2700_3000;
import java.util.*;
public class LC2792_CountNodesThatAreGreatEnough {
    /**
     * You are given a root to a binary tree and an integer k. A node of this tree is called great enough if the
     * followings hold:
     *
     * Its subtree has at least k nodes.
     * Its value is greater than the value of at least k nodes in its subtree.
     * Return the number of nodes in this tree that are great enough.
     *
     * The node u is in the subtree of the node v, if u == v or v is an ancestor of u.
     *
     * Input: root = [7,6,5,4,3,2,1], k = 2
     * Output: 3
     *
     * Input: root = [1,2,3], k = 1
     * Output: 0
     *
     * Input: root = [3,2,2], k = 2
     * Output: 1
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 10^4].
     * 1 <= Node.val <= 10^4
     * 1 <= k <= 10
     * @param root
     * @param k
     * @return
     */
    // time = O(n * klogk), space = O(n * klogk)
    int k, res;
    public int countGreatEnoughNodes(TreeNode root, int k) {
        if (root == null) return 0;
        this.k = k;
        res = 0;
        dfs(root);
        return res;
    }

    private List<Integer> dfs(TreeNode node) {
        if (node == null) return new ArrayList<>();

        List<Integer> l = dfs(node.left);
        List<Integer> r = dfs(node.right);

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int x : l) {
            pq.offer(x);
            if (pq.size() > k) pq.poll();
        }
        for (int x : r) {
            pq.offer(x);
            if (pq.size() > k) pq.poll();
        }
        if (pq.size() == k && node.val > pq.peek()) res++;
        pq.offer(node.val);
        if (pq.size() > k) pq.poll();
        return new ArrayList<>(pq);
    }
}