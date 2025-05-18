package LC3301_3600;
import java.util.*;
public class LC3313_FindtheLastMarkedNodesinTree {
    /**
     * There exists an undirected tree with n nodes numbered 0 to n - 1. You are given a 2D integer array edges of
     * length n - 1, where edges[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the tree.
     *
     * Initially, all nodes are unmarked. After every second, you mark all unmarked nodes which have at least one marked
     * node adjacent to them.
     *
     * Return an array nodes where nodes[i] is the last node to get marked in the tree, if you mark node i at time
     * t = 0. If nodes[i] has multiple answers for any node i, you can choose any one answer.
     *
     * Input: edges = [[0,1],[0,2]]
     *
     * Output: [2,2,1]
     *
     * Input: edges = [[0,1]]
     *
     * Output: [1,0]
     *
     * Input: edges = [[0,1],[0,2],[2,3],[2,4]]
     *
     * Output: [3,3,1,1,1]
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
    int[][] d1, d2;
    int[] res;
    public int[] lastMarkedNodes(int[][] edges) {
        int n = edges.length + 1;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj[a].add(b);
            adj[b].add(a);
        }
        d1 = new int[n][2];
        d2 = new int[n][2];
        dfs(0, -1);

        res = new int[n];
        reroot(0, -1, new int[2]);
        return res;
    }

    private int[] dfs(int u, int fa) {
        d1[u] = new int[]{0, u};
        for (int v : adj[u]) {
            if (v == fa) continue;
            int[] t = dfs(v, u);
            if (t[0] + 1 > d1[u][0]) {
                d2[u] = new int[]{d1[u][0], d1[u][1]};
                d1[u] = new int[]{t[0] + 1, t[1]};
            } else if (t[0] + 1 > d2[u][0]) d2[u] = new int[]{t[0] + 1, t[1]};
        }
        return d1[u];
    }

    private void reroot(int u, int fa, int[] up) {
        if (up[0] > d1[u][0]) res[u] = up[1];
        else res[u] = d1[u][1];
        for (int v : adj[u]) {
            if (v == fa) continue;
            int up1 = up[0] + 1, uidx = up[1];
            if (d1[u][1] == d1[v][1]) { // 在下面的最长路径上
                int up2 = d2[u][0] + 1;
                if (up2 > up1) {
                    up1 = up2;
                    uidx = d2[u][1];
                }
            } else {
                int up2 = d1[u][0] + 1;
                if (up2 > up1) {
                    up1 = up2;
                    uidx = d1[u][1];
                }
            }
            reroot(v, u, new int[]{up1, uidx});
        }
    }
}
