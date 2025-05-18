package LC2101_2400;
import java.util.*;
public class LC2385_AmountofTimeforBinaryTreetoBeInfected {
    /**
     * You are given the root of a binary tree with unique values, and an integer start. At minute 0, an infection
     * starts from the node with value start.
     *
     * Each minute, a node becomes infected if:
     *
     * The node is currently uninfected.
     * The node is adjacent to an infected node.
     * Return the number of minutes needed for the entire tree to be infected.
     *
     * Input: root = [1,5,3,null,4,10,6,9,2], start = 3
     * Output: 4
     *
     * Input: root = [1], start = 1
     * Output: 0
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 10^5].
     * 1 <= Node.val <= 10^5
     * Each node has a unique value.
     * A node with a value of start exists in the tree.
     * @param root
     * @param start
     * @return
     */
    // S1: bfs
    // time = O(n), space = O(n)
    HashMap<TreeNode, TreeNode> map;
    TreeNode u = null;
    int start;
    public int amountOfTime(TreeNode root, int start) {
        this.start = start;
        map = new HashMap<>();
        buildMap(root, root);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(u);
        HashSet<TreeNode> set = new HashSet<>();
        set.add(u);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (cur.left != null && set.add(cur.left)) queue.offer(cur.left);
                if (cur.right != null && set.add(cur.right)) queue.offer(cur.right);
                if (map.get(cur) != cur && set.add(map.get(cur))) queue.offer(map.get(cur));
            }
            step++;
        }
        return step - 1;
    }

    private void buildMap(TreeNode cur, TreeNode parent) {
        if (cur == null) return;
        if (cur.val == start) u = cur;
        map.put(cur, parent);
        buildMap(cur.left, cur);
        buildMap(cur.right, cur);
    }

    // S2: bfs
    // time = O(n), space = O(n)
    final int N = 100010, INF = 0x3f3f3f3f;
    int[] h, e, ne, dist;
    int idx;
    public int amountOfTime2(TreeNode root, int start) {
        h = new int[N];
        dist = new int[N];
        e = new int[N * 2];
        ne = new int[N * 2];
        idx = 0;

        Arrays.fill(h, -1);
        dfs(root);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        Arrays.fill(dist, INF);
        dist[start] = 0;

        int res = 0;
        while (!queue.isEmpty()) {
            int t = queue.poll();
            res = Math.max(res, dist[t]);
            for (int i = h[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[t] + 1) {
                    dist[j] = dist[t] + 1;
                    queue.offer(j);
                }
            }
        }
        return res;
    }

    private void dfs(TreeNode node) {
        if (node.left != null) {
            add(node.val, node.left.val);
            add(node.left.val, node.val);
            dfs(node.left);
        }
        if (node.right != null) {
            add(node.val, node.right.val);
            add(node.right.val, node.val);
            dfs(node.right);
        }
    }

    private void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}