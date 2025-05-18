package LC2401_2700;
import java.util.*;
public class LC2458_HeightofBinaryTreeAfterSubtreeRemovalQueries {
    /**
     * You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n. You are
     * also given an array queries of size m.
     *
     * You have to perform m independent queries on the tree where in the ith query you do the following:
     *
     * Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i]
     * will not be equal to the value of the root.
     * Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.
     *
     * Note:
     *
     * The queries are independent, so the tree returns to its initial state after each query.
     * The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.
     *
     * Input: root = [1,3,4,2,null,6,5,null,null,null,null,null,7], queries = [4]
     * Output: [2]
     *
     * Input: root = [5,8,9,2,1,3,7,4,6], queries = [3,2,4,8]
     * Output: [3,2,3,2]
     *
     * Constraints:
     *
     * The number of nodes in the tree is n.
     * 2 <= n <= 10^5
     * 1 <= Node.val <= n
     * All the values in the tree are unique.
     * m == queries.length
     * 1 <= m <= min(n, 10^4)
     * 1 <= queries[i] <= n
     * queries[i] != root.val
     * @param root
     * @param queries
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 100010;
    int[][] tr;
    int[] son, d, ans;
    public int[] treeQueries(TreeNode root, int[] queries) {
        tr = new int[N][2];
        son = new int[N];
        d = new int[N];
        ans = new int[N];
        Arrays.fill(son, -1);

        dfs(root);

        Arrays.fill(ans, d[root.val]);
        int cur = root.val, path = 0, maxd = 0;
        while (cur != 0 && son[cur] != -1) {
            path++;
            int k = son[cur];
            maxd = Math.max(maxd, d[tr[cur][k ^ 1]] + path);
            ans[tr[cur][k]] = maxd;
            cur = tr[cur][k];
        }

        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) res[i] = ans[queries[i]] - 1;
        return res;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int l = dfs(node.left);
        int r = dfs(node.right);
        int v = node.val;
        if (l > 0) tr[v][0] = node.left.val;
        if (r > 0) tr[v][1] = node.right.val;
        d[v] = Math.max(l, r) + 1;
        if (l > r) son[v] = 0;
        else if (r > l) son[v] = 1;
        return d[v];
    }

    // S2
    int[] depth, height;
    HashMap<Integer, List<Integer>> map; // all the heights for the node whose depth is d
    public int[] treeQueries2(TreeNode root, int[] queries) {
        depth = new int[100010];
        height = new int[100010];
        map = new HashMap<>();
        dfs_height(root, 0);
        for (int d : map.keySet()) Collections.sort(map.get(d), (o1, o2) -> o2 - o1);

        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int node = queries[i];
            int d = depth[node], h = height[node];
            if (map.get(d).size() == 1) res[i] = d - 1;
            else if (map.get(d).get(0) == h) res[i] = map.get(d).get(1) + d;
            else res[i] = map.get(d).get(0) + d;
        }
        return res;
    }

    private int dfs_height(TreeNode node, int d) {
        if (node == null) return -1; // 叶子节点高度为0， 这里是叶子节点下一层，所以是-1
        int h = Math.max(dfs_height(node.left, d + 1), dfs_height(node.right, d + 1)) + 1;
        depth[node.val] = d;
        height[node.val] = h;
        map.putIfAbsent(d, new ArrayList<>());
        map.get(d).add(h);
        return h;
    }
}
/**
 * 影响树高的只有一条链 -> 类似"重链"
 * 从上往下走，判断左右两边谁更高，往高的那边走
 * 往下走路径是唯一的
 *
 * 我们定义一个节点的height表示从它到叶子节点的最大距离，depth表示从它到root的距离。
 * 我们移除node节点对应的子树后，剩下的树的高度其实就取决于与它同depth的节点的height。
 * 所以我们将所有处在同depth的节点的height都提前收集好，那么就很容易找到其他子树的最大height。
 * 特别注意，如果某个深度的节点只有一个，那么将其移除后，剩下树的最大高度就是该节点的depth-1.
 */