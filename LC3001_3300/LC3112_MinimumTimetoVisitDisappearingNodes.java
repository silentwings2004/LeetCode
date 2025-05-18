package LC3001_3300;
import java.util.*;
public class LC3112_MinimumTimetoVisitDisappearingNodes {
    /**
     * There is an undirected graph of n nodes. You are given a 2D array edges, where edges[i] = [ui, vi, lengthi]
     * describes an edge between node ui and node vi with a traversal time of lengthi units.
     *
     * Additionally, you are given an array disappear, where disappear[i] denotes the time when the node i disappears
     * from the graph and you won't be able to visit it.
     *
     * Notice that the graph might be disconnected and might contain multiple edges.
     *
     * Return the array answer, with answer[i] denoting the minimum units of time required to reach node i from node 0.
     * If node i is unreachable from node 0 then answer[i] is -1.
     *
     * Input: n = 3, edges = [[0,1,2],[1,2,1],[0,2,4]], disappear = [1,1,5]
     *
     * Output: [0,-1,4]
     *
     * Input: n = 3, edges = [[0,1,2],[1,2,1],[0,2,4]], disappear = [1,3,5]
     *
     * Output: [0,2,3]
     *
     * Input: n = 2, edges = [[0,1,1]], disappear = [1,1]
     *
     * Output: [0,-1]
     *
     * Constraints:
     *
     * 1 <= n <= 5 * 10^4
     * 0 <= edges.length <= 10^5
     * edges[i] == [ui, vi, lengthi]
     * 0 <= ui, vi <= n - 1
     * 1 <= lengthi <= 10^5
     * disappear.length == n
     * 1 <= disappear[i] <= 10^5
     * @param n
     * @param edges
     * @param disappear
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1], c = e[2];
            adj[a].add(new int[]{b, c});
            adj[b].add(new int[]{a, c});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[]{0, 0});
        int[] res = new int[n];
        Arrays.fill(res, -1);
        boolean[] st = new boolean[n];

        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            int u = t[0], d = t[1];
            if (st[u]) continue;
            st[u] = true;
            res[u] = d;

            for (int[] x : adj[u]) {
                int v = x[0], w = x[1];
                if (disappear[v] <= d + w) continue;
                if (st[v]) continue;
                pq.offer(new int[]{v, d + w});
            }
        }
        return res;
    }
}