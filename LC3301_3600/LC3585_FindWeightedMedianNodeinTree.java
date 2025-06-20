package LC3301_3600;
import java.util.*;
public class LC3585_FindWeightedMedianNodeinTree {
    /**
     * You are given an integer n and an undirected, weighted tree rooted at node 0 with n nodes numbered from 0 to
     * n - 1. This is represented by a 2D array edges of length n - 1, where edges[i] = [ui, vi, wi] indicates an edge
     * from node ui to vi with weight wi.
     *
     * The weighted median node is defined as the first node x on the path from ui to vi such that the sum of edge
     * weights from ui to x is greater than or equal to half of the total path weight.
     *
     * You are given a 2D integer array queries. For each queries[j] = [uj, vj], determine the weighted median node
     * along the path from uj to vj.
     *
     * Return an array ans, where ans[j] is the node index of the weighted median for queries[j].
     *
     * Input: n = 2, edges = [[0,1,7]], queries = [[1,0],[0,1]]
     * Output: [0,1]
     *
     * Input: n = 3, edges = [[0,1,2],[2,0,4]], queries = [[0,1],[2,0],[1,2]]
     * Output: [1,0,2]
     *
     * Input: n = 5, edges = [[0,1,2],[0,2,5],[1,3,1],[2,4,3]], queries = [[3,4],[1,2]]
     * Output: [2,2]
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * edges.length == n - 1
     * edges[i] == [ui, vi, wi]
     * 0 <= ui, vi < n
     * 1 <= wi <= 10^9
     * 1 <= queries.length <= 10^5
     * queries[j] == [uj, vj]
     * @param n
     * @param edges
     * @param queries
     * @return
     */
    // time = O((n + q) * logn), space = O(nlogn)
    public int[] findMedian(int n, int[][] edges, int[][] queries) {
        LcaBinaryLifting lca = new LcaBinaryLifting(edges);
        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int u = queries[i][0], v = queries[i][1];
            if (u == v) {
                res[i] = u;
                continue;
            }
            int p = lca.getLCA(u, v);
            long du = lca.getDis(u, p);
            long dv = lca.getDis(v, p);
            long half = (du + dv + 1) / 2;
            if (lca.dis[u] - lca.dis[p] >= half) {
                int x = lca.upd(u, half - 1);
                res[i] = lca.pa[x][0];
            } else {
                res[i] = lca.upd(v, du + dv - half);
            }
        }
        return res;
    }

    class LcaBinaryLifting {
        private final int[] depth;
        public final long[] dis; // 如果是无权树（边权为 1），dis 可以去掉，用 depth 代替
        public final int[][] pa;

        public LcaBinaryLifting(int[][] edges) {
            int n = edges.length + 1;
            int m = 32 - Integer.numberOfLeadingZeros(n); // n 的二进制长度
            List<int[]>[] g = new ArrayList[n];
            Arrays.setAll(g, e -> new ArrayList<>());
            for (int[] e : edges) {
                int x = e[0], y = e[1], w = e[2];
                g[x].add(new int[]{y, w});
                g[y].add(new int[]{x, w});
            }

            depth = new int[n];
            dis = new long[n];
            pa = new int[n][m];

            dfs(g, 0, -1);

            for (int i = 0; i < m - 1; i++) {
                for (int x = 0; x < n; x++) {
                    int p = pa[x][i];
                    pa[x][i + 1] = p < 0 ? -1 : pa[p][i];
                }
            }
        }

        private void dfs(List<int[]>[] g, int x, int fa) {
            pa[x][0] = fa;
            for (int[] e : g[x]) {
                int y = e[0];
                if (y != fa) {
                    depth[y] = depth[x] + 1;
                    dis[y] = dis[x] + e[1];
                    dfs(g, y, x);
                }
            }
        }

        public int getKthAncestor(int node, int k) {
            for (; k > 0; k &= k - 1) {
                node = pa[node][Integer.numberOfTrailingZeros(k)];
            }
            return node;
        }

        // 返回 x 和 y 的最近公共祖先（节点编号从 0 开始）
        public int getLCA(int x, int y) {
            if (depth[x] > depth[y]) {
                int tmp = y;
                y = x;
                x = tmp;
            }
            // 使 y 和 x 在同一深度
            y = getKthAncestor(y, depth[y] - depth[x]);
            if (y == x) {
                return x;
            }
            for (int i = pa[x].length - 1; i >= 0; i--) {
                int px = pa[x][i], py = pa[y][i];
                if (px != py) {
                    x = px;
                    y = py; // 同时往上跳 2^i 步
                }
            }
            return pa[x][0];
        }

        // 返回 x 到 y 的距离（最短路长度）
        public long getDis(int x, int y) {
            return dis[x] + dis[y] - dis[getLCA(x, y)] * 2;
        }

        // 从 x 往上跳【至多】d 距离，返回最远能到达的节点
        // 1. 先看往上跳到 x 的 2^(m - 1) 级祖先
        public int upd(int x, long d) {
            long dx = dis[x];
            for (int i = pa[x].length - 1; i >= 0; i--) {
                int p = pa[x][i];
                if (p != -1 && dx - dis[p] <= d) { // 可以跳至多 d
                    x = p;
                }
            }
            return x;
        }
    }
}