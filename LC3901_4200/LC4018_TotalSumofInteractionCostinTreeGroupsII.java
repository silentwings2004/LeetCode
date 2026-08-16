package LC3901_4200;
import java.util.*;
public class LC4018_TotalSumofInteractionCostinTreeGroupsII {
    /**
     * You are given an integer n and an undirected tree rooted at node 0 with n nodes numbered from 0 to n - 1. The
     * tree is represented by a 2D integer array edges of length n - 1, where edges[i] = [ui, vi] indicates an
     * undirected edge between nodes ui and vi.
     *
     * You are also given an integer array group of length n, where group[i] denotes the group label assigned to node i.
     *
     * Two nodes u and v belong to the same group if and only if group[u] == group[v].
     * The interaction cost between two nodes is the shortest distance between them in the tree.
     * Return the sum of interaction costs over all pairs of node indices (u, v) such that 0 <= u < v < n and
     * group[u] == group[v].
     *
     * The shortest distance between two nodes is the number of edges on the unique path connecting them in the tree.
     *
     * Input: n = 3, edges = [[0,1],[1,2]], group = [1,1,1]
     * Output: 4
     *
     * Input: n = 3, edges = [[0,1],[1,2]], group = [3,2,3]
     * Output: 2
     *
     * Input: n = 4, edges = [[0,1],[0,2],[0,3]], group = [1,1,4,4]
     * Output: 3
     *
     * Input: n = 2, edges = [[0,1]], group = [1,2]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * edges.length == n - 1
     * edges[i] = [ui, vi]
     * 0 <= ui, vi <= n - 1
     * group.length == n
     * 1 <= group[i] <= n
     * The input is generated such that edges represents a valid tree.
     * @param n
     * @param edges
     * @param group
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long interactionCosts(int n, int[][] edges, int[] group) {
        if (n == 1) return 0;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int g : group) map.put(g, map.getOrDefault(g, 0) + 1);

        int[] fa = new int[n];
        Arrays.fill(fa, -2);
        int[] order = new int[n];
        int idx = 0;

        int[] stk = new int[n + 1];
        int tt = 1;
        fa[0] = -1;
        while (tt > 0) {
            int u = stk[tt--];
            order[idx++] = u;
            for (int v : adj[u]) {
                if (fa[v] == -2) {
                    fa[v] = u;
                    stk[++tt] = v;
                }
            }
        }

        HashMap<Integer, Integer>[] mp = new HashMap[n];
        long[] sumSq = new long[n], sumTot = new long[n];
        long res = 0;
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            HashMap<Integer, Integer> cnt = new HashMap<>();
            int gu = group[u];
            cnt.put(gu, 1);
            long s = 1, t = map.get(gu);

            for (int v : adj[u]) {
                if (fa[v] != u) continue;
                HashMap<Integer, Integer> child = mp[v];
                if (child == null) continue;
                if (cnt.size() < child.size()) {
                    HashMap<Integer, Integer> tmp = cnt;
                    cnt = child;
                    child = tmp;
                    long tmpS = s;
                    s = sumSq[v];
                    long tmpT = t;
                    t = sumTot[v];
                }
                for (int g : child.keySet()) {
                    int cntSmall = child.get(g);
                    int old = cnt.getOrDefault(g, 0);
                    if (old != 0) s -= 1L * old * old;
                    long nw = 1L * old + cntSmall;
                    s += nw * nw;
                    t += 1L * cntSmall * map.get(g);
                    cnt.put(g, (int)nw);
                }
                mp[v] = null;
            }
            mp[u] = cnt;
            sumSq[u] = s;
            sumTot[u] = t;
            if (fa[u] != -1) res += t - s;
        }
        return res;
    }
}