package LC3001_3300;
import java.util.*;
public class LC3157_FindtheLevelofTreewithMinimumSum {
    /**
     * Given the root of a binary tree root where each node has a value, return the level of the tree that has the
     * minimum sum of values among all the levels (in case of a tie, return the lowest level).
     *
     * Note that the root of the tree is at level 1 and the level of any other node is its distance from the root + 1.
     *
     * Input: root = [50,6,2,30,80,7]
     * Output: 2
     *
     * Input: root = [36,17,10,null,null,24]
     * Output: 3
     *
     * Input: root = [5,null,5,null,5]
     * Output: 1
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 10^5].
     * 1 <= Node.val <= 10^9
     * @param root
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int minimumLevel(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        long mx = root.val;
        int res = 1;

        int step = 1;
        while (!q.isEmpty()) {
            int sz = q.size();
            long s = 0;
            while (sz-- > 0) {
                TreeNode t = q.poll();
                s += t.val;
                if (t.left != null) q.offer(t.left);
                if (t.right != null) q.offer(t.right);
            }
            if (s < mx) {
                mx = s;
                res = step;
            }
            step++;
        }
        return res;
    }

    // S2: dfs
    // time = O(n), space = O(n)
    HashMap<Integer, Long> map;
    public int minimumLevel2(TreeNode root) {
        map = new HashMap<>();
        dfs(root, 1);

        long mx = root.val;
        int res = 1;
        for (int key : map.keySet()) {
            if (map.get(key) < mx) {
                mx = map.get(key);
                res = key;
            } else if (map.get(key) == mx) {
                res = Math.min(res, key);
            }
        }
        return res;
    }

    private void dfs(TreeNode node, int d) {
        if (node == null) return;

        map.put(d, map.getOrDefault(d, 0L) + node.val);
        dfs(node.left, d + 1);
        dfs(node.right, d + 1);
    }
}