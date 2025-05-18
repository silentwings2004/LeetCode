package LC3001_3300;
import java.util.*;
public class LC3241_TimeTakentoMarkAllNodes {
    /**
     * There exists an undirected tree with n nodes numbered 0 to n - 1. You are given a 2D integer array edges of
     * length n - 1, where edges[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the tree.
     *
     * Initially, all nodes are unmarked. For each node i:
     *
     * If i is odd, the node will get marked at time x if there is at least one node adjacent to it which was marked at
     * time x - 1.
     * If i is even, the node will get marked at time x if there is at least one node adjacent to it which was marked
     * at time x - 2.
     * Return an array times where times[i] is the time when all nodes get marked in the tree, if you mark node i at
     * time t = 0.
     *
     * Note that the answer for each times[i] is independent, i.e. when you mark node i all other nodes are unmarked.
     *
     * Input: edges = [[0,1],[0,2]]
     * Output: [2,4,3]
     *
     * Input: edges = [[0,1]]
     * Output: [1,2]
     *
     * Input: edges = [[2,4],[0,1],[2,3],[0,2]]
     * Output: [4,6,3,5,5]
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * edges.length == n - 1
     * edges[i].length == 2
     * 0 <= edges[i][0], edges[i][1] <= n - 1
     * The input is generated such that edges represents a valid tree.
     * @param edges
     * @return
     */
    // time = O(n), space = O(n)
    List<Integer>[] adj;
    int[] time, res;
    public int[] timeTaken(int[][] edges) {
        int n = edges.length + 1;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj[a].add(b);
            adj[b].add(a);
        }

        time = new int[n];
        dfs(0, -1);

        res = new int[n];
        dfs2(0, -1);
        return res;
    }

    private int dfs(int u, int fa) {
        for (int v : adj[u]) {
            if (v == fa) continue;
            int t = dfs(v, u);
            if (v % 2 == 1) time[u] = Math.max(time[u], t + 1);
            else time[u] = Math.max(time[u], t + 2);
        }
        return time[u];
    }

    private void dfs2(int u, int fa) {
        int a = 0, b = 0, t = 0;
        for (int v : adj[u]) {
            if (v % 2 == 1) t = time[v] + 1;
            else t = time[v] + 2;
            if (t >= b) {
                a = b;
                b = t;
            } else if (t > a) a = t;
        }

        res[u] = b;
        time[u] = b;

        // change root
        for (int v : adj[u]) {
            if (v == fa) continue;
            if (v % 2 == 1) t = time[v] + 1;
            else t = time[v] + 2;
            if (t == b) {
                int x = time[u];
                time[u] = a;
                dfs2(v, u);
                time[u] = x;
            } else dfs2(v, u);
        }
    }
}
/**
 * 给你一棵有向树
 * 有 2n - 2 条有向边，每条边都是有边权的
 * 求 ans[i] 表示以 i 为根节点的时候，这棵树的最大深度是多少？
 * 或者说 i 到最远点的距离
 */
