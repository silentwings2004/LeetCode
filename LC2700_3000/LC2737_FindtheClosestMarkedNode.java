package LC2700_3000;
import java.util.*;
public class LC2737_FindtheClosestMarkedNode {
    /**
     * You are given a positive integer n which is the number of nodes of a 0-indexed directed weighted graph and a
     * 0-indexed 2D array edges where edges[i] = [ui, vi, wi] indicates that there is an edge from node ui to node vi
     * with weight wi.
     *
     * You are also given a node s and a node array marked; your task is to find the minimum distance from s to any of
     * the nodes in marked.
     *
     * Return an integer denoting the minimum distance from s to any node in marked or -1 if there are no paths from s
     * to any of the marked nodes.
     *
     * Input: n = 4, edges = [[0,1,1],[1,2,3],[2,3,2],[0,3,4]], s = 0, marked = [2,3]
     * Output: 4
     *
     * Input: n = 5, edges = [[0,1,2],[0,2,4],[1,3,1],[2,3,3],[3,4,2]], s = 1, marked = [0,4]
     * Output: 3
     *
     * Input: n = 4, edges = [[0,1,1],[1,2,3],[2,3,2]], s = 3, marked = [0,1]
     * Output: -1
     *
     * Constraints:
     *
     * 2 <= n <= 500
     * 1 <= edges.length <= 10^4
     * edges[i].length = 3
     * 0 <= edges[i][0], edges[i][1] <= n - 1
     * 1 <= edges[i][2] <= 10^6
     * 1 <= marked.length <= n - 1
     * 0 <= s, marked[i] <= n - 1
     * s != marked[i]
     * marked[i] != marked[j] for every i != j
     * The graph might have repeated edges.
     * The graph is generated such that it has no self-loops.
     * @param n
     * @param edges
     * @param s
     * @param marked
     * @return
     */
    // time = O(n * m), space = O(n + m)
    final int N = 510, M = 10010, INF = 0x3f3f3f3f;
    int[] h, e, ne, w;
    int idx;
    int[] q, dist;
    boolean[] st;
    public int minimumDistance(int n, List<List<Integer>> edges, int s, int[] marked) {
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        w = new int[M];
        q = new int[N];
        dist = new int[N];
        st = new boolean[N];
        Arrays.fill(h, -1);
        idx = 0;

        for (List<Integer> e : edges) {
            int a = e.get(0), b = e.get(1), c = e.get(2);
            add(a, b, c);
        }

        spfa(s);
        int res = INF;
        for (int x : marked) res = Math.min(res, dist[x]);
        return res == INF ? -1 : res;
    }

    private void spfa(int start) {
        Arrays.fill(dist, INF);
        dist[start] = 0;
        int hh = 0, tt = 1;
        q[0] = start;
        st[start] = true;

        while (hh != tt) {
            int t = q[hh++];
            if (hh == N) hh = 0;
            st[t] = false;

            for (int i = h[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[t] + w[i]) {
                    dist[j] = dist[t] + w[i];
                    if (!st[j]) {
                        q[tt++] = j;
                        if (tt == N) tt = 0;
                        st[j] = true;
                    }
                }
            }
        }
    }

    private void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}