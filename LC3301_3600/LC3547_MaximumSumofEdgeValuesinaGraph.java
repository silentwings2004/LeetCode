package LC3301_3600;
import java.util.*;
public class LC3547_MaximumSumofEdgeValuesinaGraph {
    /**
     * You are given an undirected graph of n nodes, numbered from 0 to n - 1. Each node is connected to at most 2 other
     * nodes.
     *
     * The graph consists of m edges, represented by a 2D array edges, where edges[i] = [ai, bi] indicates that there is
     * an edge between nodes ai and bi.
     *
     * You have to assign a unique value from 1 to n to each node. The value of an edge will be the product of the
     * values assigned to the two nodes it connects.
     *
     * Your score is the sum of the values of all edges in the graph.
     *
     * Return the maximum score you can achieve.
     *
     * Input: n = 7, edges = [[0,1],[1,2],[2,0],[3,4],[4,5],[5,6]]
     * Output: 130
     *
     * Input: n = 6, edges = [[0,3],[4,5],[2,0],[1,3],[2,4],[1,5]]
     * Output: 82
     *
     * Constraints:
     *
     * 1 <= n <= 5 * 10^4
     * m == edges.length
     * 1 <= m <= n
     * edges[i].length == 2
     * 0 <= ai, bi < n
     * ai != bi
     * There are no repeated edges.
     * Each node is connected to at most 2 other nodes.
     * @param n
     * @param edges
     * @return
     */
    // time = O(nlogn), sppace = O(n)
    int[] p, sz, cnt;
    public long maxScore(int n, int[][] edges) {
        p = new int[n];
        sz = new int[n];
        cnt = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
            sz[i] = 1;
        }

        for (int[] e : edges) {
            int a = e[0], b = e[1];
            int fa = find(a), fb = find(b);
            if (fa == fb) cnt[fa]++;
            else {
                sz[fb] += sz[fa];
                p[fa] = fb;
                cnt[fb] += cnt[fa] + 1;
            }
        }

        List<Integer> cycle = new ArrayList<>(), chain = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (find(i) == i) {
                if (sz[i] == 1) continue;
                if (sz[i] == cnt[i]) cycle.add(sz[i]);
                else chain.add(sz[i]);
            }
        }
        Collections.sort(cycle);
        Collections.sort(chain, (o1, o2) -> o2 - o1);

        long[] a = new long[n << 1];
        int tot = n;
        long res = 0;
        for (int x : cycle) {
            int l = n, r = n - 1;
            while (x > 0) {
                a[--l] = tot--;
                x--;
                if (x > 0) {
                    a[++r] = tot--;
                    x--;
                }
            }
            res += a[l] * a[r];
            for (int i = l + 1; i <= r; i++) res += a[i - 1] * a[i];
        }

        for (int x : chain) {
            int l = n, r = n - 1;
            while (x > 0) {
                a[--l] = tot--;
                x--;
                if (x > 0) {
                    a[++r] = tot--;
                    x--;
                }
            }
            for (int i = l + 1; i <= r; i++) res += a[i - 1] * a[i];
        }
        return res;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}