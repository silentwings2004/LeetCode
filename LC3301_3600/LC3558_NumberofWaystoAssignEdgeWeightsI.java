package LC3301_3600;
import java.util.*;
public class LC3558_NumberofWaystoAssignEdgeWeightsI {
    /**
     * There is an undirected tree with n nodes labeled from 1 to n, rooted at node 1. The tree is represented by a 2D
     * integer array edges of length n - 1, where edges[i] = [ui, vi] indicates that there is an edge between nodes ui
     * and vi.
     *
     * Initially, all edges have a weight of 0. You must assign each edge a weight of either 1 or 2.
     *
     * The cost of a path between any two nodes u and v is the total weight of all edges in the path connecting them.
     *
     * Select any one node x at the maximum depth. Return the number of ways to assign edge weights in the path from
     * node 1 to x such that its total cost is odd.
     *
     * Since the answer may be large, return it modulo 10^9 + 7.
     *
     * Note: Ignore all edges not in the path from node 1 to x.
     *
     * Input: edges = [[1,2]]
     * Output: 1
     *
     * Input: edges = [[1,2],[1,3],[3,4],[3,5]]
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * edges.length == n - 1
     * edges[i] == [ui, vi]
     * 1 <= ui, vi <= n
     * edges represents a valid tree.
     * @param edges
     * @return
     */
    // time = O(n + logk), space = O(n)
    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0] - 1, b = e[1] - 1;
            adj[a].add(b);
            adj[b].add(a);
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        boolean[] st = new boolean[n];
        st[0] = true;

        int md = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz-- > 0) {
                int u = q.poll();
                for (int v : adj[u]) {
                    if (st[v]) continue;
                    st[v] = true;
                    q.offer(v);
                }
            }
            md++;
        }
        md--;
        if (md == 0) return 0;
        long mod = (long)(1e9 + 7);
        return (int)qmi(2, md - 1, mod);
    }

    private long qmi(long a, long k, long mod) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            k >>= 1;
        }
        return res;
    }
}