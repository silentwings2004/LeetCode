package LC3301_3600;
import java.util.*;
public class LC3600_MaximizeSpanningTreeStabilitywithUpgrades {
    /**
     * You are given an integer n, representing n nodes numbered from 0 to n - 1 and a list of edges, where
     * edges[i] = [ui, vi, si, musti]:
     *
     * ui and vi indicates an undirected edge between nodes ui and vi.
     * si is the strength of the edge.
     * musti is an integer (0 or 1). If musti == 1, the edge must be included in the spanning tree. These edges cannot
     * be upgraded.
     * You are also given an integer k, the maximum number of upgrades you can perform. Each upgrade doubles the
     * strength of an edge, and each eligible edge (with musti == 0) can be upgraded at most once.
     *
     * The stability of a spanning tree is defined as the minimum strength score among all edges included in it.
     *
     * Return the maximum possible stability of any valid spanning tree. If it is impossible to connect all nodes,
     * return -1.
     *
     * Note: A spanning tree of a graph with n nodes is a subset of the edges that connects all nodes together (i.e.
     * the graph is connected) without forming any cycles, and uses exactly n - 1 edges.
     *
     * Input: n = 3, edges = [[0,1,2,1],[1,2,3,0]], k = 1
     * Output: 2
     *
     * Input: n = 3, edges = [[0,1,4,0],[1,2,3,0],[0,2,1,0]], k = 2
     * Output: 6
     *
     * Input: n = 3, edges = [[0,1,1,1],[1,2,1,1],[2,0,1,1]], k = 0
     * Output: -1
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * 1 <= edges.length <= 10^5
     * edges[i] = [ui, vi, si, musti]
     * 0 <= ui, vi < n
     * ui != vi
     * 1 <= si <= 10^5
     * musti is either 0 or 1.
     * 0 <= k <= n
     * There are no duplicate edges.
     * @param n
     * @param edges
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int maxStability(int n, int[][] edges, int k) {
        List<int[]> must = new ArrayList<>();
        List<int[]> opt = new ArrayList<>();
        int maxs = 0;
        for (int[] e : edges) {
            maxs = Math.max(maxs, e[2]);
            if (e[3] == 1) must.add(e);
            else opt.add(e);
        }

        UnionFind uf = new UnionFind(n);
        for (int[] e : must) {
            if (!uf.merge(e[0], e[1])) return -1;
        }
        for (int[] e : opt) uf.merge(e[0], e[1]);

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (uf.find(i) == i) cnt++;
        }
        if (cnt > 1) return -1;

        int l = 1, r = 2 * maxs;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(must, opt, n, k, mid)) l = mid;
            else r = mid - 1;
        }
        return r;
    }

    private boolean check(List<int[]> must, List<int[]> opt, int n, int k, int mid) {
        UnionFind uf2 = new UnionFind(n);
        for (int[] e : must) {
            if (e[2] < mid) return false;
            uf2.merge(e[0], e[1]);
        }
        List<int[]> q = new ArrayList<>();
        for (int[] e : opt) {
            if (e[2] >= mid) uf2.merge(e[0], e[1]);
            if (e[2] < mid && e[2] * 2 >= mid) q.add(e);
        }
        int used = 0;
        for (int[] e : q) {
            if (used >= k) break;
            if (uf2.merge(e[0], e[1])) used++;
        }
        int root = uf2.find(0);
        for (int i = 1; i < n; i++) {
            if (uf2.find(i) != root) return false;
        }
        return true;
    }

    class UnionFind {
        private final int[] fa;
        private final int[] size;
        public int cc;

        UnionFind(int n) {
            fa = new int[n];
            for (int i = 0; i < n; i++) {
                fa[i] = i;
            }
            size = new int[n];
            Arrays.fill(size, 1);
            cc = n;
        }

        public int find(int x) {
            if (fa[x] != x) {
                fa[x] = find(fa[x]);
            }
            return fa[x];
        }

        public boolean isSame(int x, int y) {
            return find(x) == find(y);
        }

        public boolean merge(int from, int to) {
            int x = find(from);
            int y = find(to);
            if (x == y) {
                return false;
            }
            fa[x] = y;
            size[y] += size[x];
            cc--;
            return true;
        }

        public int getSize(int x) {
            return size[find(x)];
        }
    }
}