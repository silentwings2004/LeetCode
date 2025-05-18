package LC1501_1800;
import java.util.*;
public class LC1530_NumberofGoodLeafNodesPairs {
    /**
     * You are given the root of a binary tree and an integer distance. A pair of two different leaf nodes of a binary
     * tree is said to be good if the length of the shortest path between them is less than or equal to distance.
     *
     * Return the number of good leaf node pairs in the tree.
     *
     * Input: root = [1,2,3,null,4], distance = 3
     * Output: 1
     *
     * Input: root = [1,2,3,4,5,6,7], distance = 3
     * Output: 2
     *
     * Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
     * Output: 1
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 2^10].
     * 1 <= Node.val <= 100
     * 1 <= distance <= 10
     * @param root
     * @param distance
     * @return
     */
    // S1
    // time = O(n * d^2), space = O(n * d)
    int res, d;
    public int countPairs(TreeNode root, int distance) {
        d = distance;
        dfs(root);
        return res;
    }

    private List<Integer> dfs(TreeNode node) {
        List<Integer> q = new ArrayList<>();
        if (node == null) return q;
        if (node.left == null && node.right == null) {
            q.add(0);
            return q;
        }

        List<Integer> l = dfs(node.left);
        List<Integer> r = dfs(node.right);

        for (int x : l) {
            for (int y : r) {
                if (x + y + 2 <= d) res++;
            }
        }

        for (int x : l) q.add(x + 1);
        for (int x : r) q.add(x + 1);
        return q;
    }

    // S2
    // time = O(n^2 * d), space = O(n)
    HashMap<TreeNode, TreeNode> map;
    List<TreeNode> p;
    int dist;
    public int countPairs2(TreeNode root, int distance) {
        this.dist = distance;
        map = new HashMap<>();
        p = new ArrayList<>();
        dfs(root, null);

        int res = 0;
        for (TreeNode x : p) res += bfs(x);
        return res / 2;
    }

    private int bfs(TreeNode x) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(x);
        HashSet<TreeNode> set = new HashSet<>();
        set.add(x);

        int step = 0, res = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz-- > 0) {
                TreeNode t = q.poll();
                if (t.left == null && t.right == null) res++;
                if (t.left != null && set.add(t.left)) q.offer(t.left);
                if (t.right != null && set.add(t.right)) q.offer(t.right);
                if (map.get(t) != null && set.add(map.get(t))) q.offer(map.get(t));
            }
            step++;
            if (step > dist) break;
        }
        return res - 1;
    }

    private void dfs(TreeNode node, TreeNode fa) {
        if (node == null) return;
        map.put(node, fa);
        if (node.left == null && node.right == null) p.add(node);
        dfs(node.left, node);
        dfs(node.right, node);
    }
}