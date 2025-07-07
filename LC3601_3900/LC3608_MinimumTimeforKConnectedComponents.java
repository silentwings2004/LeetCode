package LC3601_3900;
import java.util.*;
public class LC3608_MinimumTimeforKConnectedComponents {
    /**
     * You are given an integer n and an undirected graph with n nodes labeled from 0 to n - 1. This is represented by a
     * 2D array edges, where edges[i] = [ui, vi, timei] indicates an undirected edge between nodes ui and vi that can be
     * removed at timei.
     *
     * You are also given an integer k.
     *
     * Initially, the graph may be connected or disconnected. Your task is to find the minimum time t such that after
     * removing all edges with time <= t, the graph contains at least k connected components.
     *
     * Return the minimum time t.
     *
     * A connected component is a subgraph of a graph in which there exists a path between any two vertices, and no
     * vertex of the subgraph shares an edge with a vertex outside of the subgraph.
     *
     * Input: n = 2, edges = [[0,1,3]], k = 2
     * Output: 3
     *
     * Input: n = 3, edges = [[0,1,2],[1,2,4]], k = 3
     * Output: 4
     *
     * Input: n = 3, edges = [[0,2,5]], k = 2
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * 0 <= edges.length <= 10^5
     * edges[i] = [ui, vi, timei]
     * 0 <= ui, vi < n
     * ui != vi
     * 1 <= timei <= 10^9
     * 1 <= k <= n
     * There are no duplicate edges.
     * @param n
     * @param edges
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int minTime(int n, int[][] edges, int k) {
        Arrays.sort(edges, (o1, o2) -> o2[2] - o1[2]);
        UnionFind uf = new UnionFind(n);
        for (int[] e : edges) {
            uf.merge(e[0], e[1]);
            if (uf.cc < k) return e[2];
        }
        return 0;
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
/**
 * t 越大，删除的边越多，连通块个数也越多；t 越小，删除的边越少，连通块个数也越少。
 * 枚举 t？不如枚举 time
 * 由于并查集加边容易，删边难，按照 time[i] 从大到小的顺序加边，用并查集合并边的两个端点
 * 如果遍历到某条边时（设这条边的时间为 t），发现合并后，连通块个数从 ≥k 变成 < k，
 * 这意味着这条边不能留，我们必须移除 time[i] ≤t 的边，剩余的边组成的连通块个数才能 ≥k。此时的 t 就是最小答案。
 * 如果所有边合并后，连通块的个数仍然 ≥k（见示例 3），返回 0。
 */