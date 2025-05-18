package LC2401_2700;

public class LC2685_CounttheNumberofCompleteComponents {
    /**
     * You are given an integer n. There is an undirected graph with n vertices, numbered from 0 to n - 1. You are given
     * a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting
     * vertices ai and bi.
     *
     * Return the number of complete connected components of the graph.
     *
     * A connected component is a subgraph of a graph in which there exists a path between any two vertices, and no
     * vertex of the subgraph shares an edge with a vertex outside of the subgraph.
     *
     * A connected component is said to be complete if there exists an edge between every pair of its vertices.
     *
     * Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4]]
     * Output: 3
     *
     * Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4],[3,5]]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= n <= 50
     * 0 <= edges.length <= n * (n - 1) / 2
     * edges[i].length == 2
     * 0 <= ai, bi <= n - 1
     * ai != bi
     * There are no repeated edges.
     * @param n
     * @param edges
     * @return
     */
    // time = O(nlogn), space = O(n)
    int[] p, sz, d;
    public int countCompleteComponents(int n, int[][] edges) {
        p = new int[n];
        sz = new int[n];
        d = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
            sz[i] = 1;
        }

        for (int[] e : edges) {
            int a = e[0], b = e[1];
            d[a]++;
            d[b]++;
            if (find(a) != find(b)) {
                sz[find(b)] += sz[find(a)];
                p[find(a)] = find(b);
            }
        }

        for (int i = 0; i < n; i++) {
            if (i == find(i)) continue;
            d[find(i)] += d[i];
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (i == find(i)) {
                int x = sz[i];
                if (d[i] == x * (x - 1)) res++;
            }
        }
        return res;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}