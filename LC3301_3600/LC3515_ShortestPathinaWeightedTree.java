package LC3301_3600;
import java.util.*;
public class LC3515_ShortestPathinaWeightedTree {
    /**
     * You are given an integer n and an undirected, weighted tree rooted at node 1 with n nodes numbered from 1 to n.
     * This is represented by a 2D array edges of length n - 1, where edges[i] = [ui, vi, wi] indicates an undirected
     * edge from node ui to vi with weight wi.
     *
     * You are also given a 2D integer array queries of length q, where each queries[i] is either:
     *
     * [1, u, v, w'] – Update the weight of the edge between nodes u and v to w', where (u, v) is guaranteed to be an
     * edge present in edges.
     * [2, x] – Compute the shortest path distance from the root node 1 to node x.
     * Return an integer array answer, where answer[i] is the shortest path distance from node 1 to x for the ith query
     * of [2, x].
     *
     * Input: n = 2, edges = [[1,2,7]], queries = [[2,2],[1,1,2,4],[2,2]]
     * Output: [7,4]
     *
     * Input: n = 3, edges = [[1,2,2],[1,3,4]], queries = [[2,1],[2,3],[1,1,3,7],[2,2],[2,3]]
     * Output: [0,4,2,7]
     *
     * Input: n = 4, edges = [[1,2,2],[2,3,1],[3,4,5]], queries = [[2,4],[2,3],[1,2,3,3],[2,2],[2,3]]
     * Output: [8,3,2,5]
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * edges.length == n - 1
     * edges[i] == [ui, vi, wi]
     * 1 <= ui, vi <= n
     * 1 <= wi <= 10^4
     * The input is generated such that edges represents a valid tree.
     * 1 <= queries.length == q <= 10^5
     * queries[i].length == 2 or 4
     * queries[i] == [1, u, v, w'] or,
     * queries[i] == [2, x]
     * 1 <= u, v, x <= n
     * (u, v) is always an edge from edges.
     * 1 <= w' <= 10^4
     * @param n
     * @param edges
     * @param queries
     * @return
     */
    // time = O(n + mlogn), space = O(n)
    List<int[]>[] adj;
    int idx;
    int[] l, r, pos, dep;
    public int[] treeQueries(int n, int[][] edges, int[][] queries) {
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0] - 1, b = e[1] - 1, c = e[2];
            adj[a].add(new int[]{b, c});
            adj[b].add(new int[]{a, c});
        }
        l = new int[n];
        r = new int[n];
        pos = new int[n];
        dep = new int[n];
        dfs(0, -1, 0);

        Fenwick fen = new Fenwick(n);
        for (int i = 1; i < n; i++) fen.add(i, dep[pos[i]] - dep[pos[i - 1]]);
        List<Integer> res = new ArrayList<>();
        for (int[] q : queries) {
            int o = q[0];
            if (o == 1) {
                int x = q[1] - 1, y = q[2] - 1, v = q[3];
                int dx = fen.sum(l[x] + 1), dy = fen.sum(l[y] + 1);
                if (dx > dy) {
                    int tmp = x;
                    x = y;
                    y = tmp;
                    tmp = dx;
                    dx = dy;
                    dy = tmp;
                }
                int old = dy - dx;
                fen.add(l[y], v - old);
                if (r[y] < n - 1) fen.add(r[y] + 1, old - v);
            } else {
                res.add(fen.sum(l[q[1] - 1] + 1));
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }

    private void dfs(int u, int fa, int d) {
        pos[idx] = u;
        l[u] = idx++;
        dep[u] = d;
        for (int[] x : adj[u]) {
            int v = x[0], w = x[1];
            if (v == fa) continue;
            dfs(v, u, d + w);
        }
        r[u] = idx - 1;
    }

    class Fenwick {
        private int n;
        private int[] a;
        public Fenwick(int n) {
            this.n = n;
            this.a = new int[n + 1];
        }

        private void add(int x, int v) {
            for (int i = x + 1; i <= n; i += i & -i) {
                a[i - 1] = a[i - 1] + v;
            }
        }

        private int sum(int x) {
            int ans = 0;
            for (int i = x; i > 0; i -= i & -i) {
                ans = ans + a[i - 1];
            }
            return ans;
        }

        private int rangeSum(int l, int r) {
            return sum(r) - sum(l);
        }
    }
}