package LC1201_1500;
import java.util.*;
public class LC1377_FrogPositionAfterTSeconds {
    /**
     * Given an undirected tree consisting of n vertices numbered from 1 to n. A frog starts jumping from vertex 1. In
     * one second, the frog jumps from its current vertex to another unvisited vertex if they are directly connected.
     * The frog can not jump back to a visited vertex. In case the frog can jump to several vertices, it jumps randomly
     * to one of them with the same probability. Otherwise, when the frog can not jump to any unvisited vertex, it jumps
     * forever on the same vertex.
     *
     * The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an
     * edge connecting the vertices ai and bi.
     *
     * Return the probability that after t seconds the frog is on the vertex target. Answers within 10^-5 of the actual
     * answer will be accepted.
     *
     * Input: n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
     * Output: 0.16666666666666666
     *
     * Input: n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
     * Output: 0.3333333333333333
     *
     * Constraints:
     *
     * 1 <= n <= 100
     * edges.length == n - 1
     * edges[i].length == 2
     * 1 <= ai, bi <= n
     * 1 <= t <= 50
     * 1 <= target <= n
     * @param n
     * @param edges
     * @param t
     * @param target
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 110, M = N * 2;
    int[] h, e, ne;
    int idx;
    boolean[] st;
    double res;
    public double frogPosition(int n, int[][] edges, int t, int target) {
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        st = new boolean[N];
        Arrays.fill(h, -1);
        idx = 0;
        res = 0;

        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            add(a, b);
            add(b, a);
        }

        dfs(1, 1, t, target);
        return res;
    }

    private void dfs(int u, double p, int t, int target) {
        if (t == 0) {
            if (u == target) res += p;
            return;
        }

        st[u] = true;
        List<Integer> q = new ArrayList<>();
        for (int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i];
            if (st[j]) continue;
            q.add(j);
        }
        if (q.size() > 0) {
            p *= 1.0 / q.size();
            for (int x : q) dfs(x, p, t - 1, target);
        } else dfs(u, p, t - 1, target);
        st[u] = false;
    }

    private void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}