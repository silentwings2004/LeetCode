package LC1201_1500;
import java.util.*;
public class LC1245_TreeDiameter {
    /**
     * Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.
     *
     * The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.
     * Each node has labels in the set {0, 1, ..., edges.length}.
     *
     * Input: edges = [[0,1],[0,2]]
     * Output: 2
     *
     * Constraints:
     *
     * 0 <= edges.length < 10^4
     * edges[i][0] != edges[i][1]
     * 0 <= edges[i][j] <= edges.length
     * The given edges form an undirected tree.
     * @param edges
     * @return
     */
    // time = O(n), space = O(n)   n: # of nodes in the graph
    public int treeDiameter(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj[a].add(b);
            adj[b].add(a);
        }

        int[] x = bfs(adj, n, 0);
        int[] y = bfs(adj, n, x[0]);
        return y[1];
    }

    private int[] bfs(List<Integer>[] adj, int n, int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        int[] dist = new int[n];
        Arrays.fill(dist, 0x3f3f3f3f);
        dist[start] = 0;

        int[] res = new int[2];
        while (!q.isEmpty()) {
            int t = q.poll();
            for (int j : adj[t]) {
                if (dist[j] > dist[t] + 1) {
                    dist[j] = dist[t] + 1;
                    if (dist[j] > res[1]) {
                        res[0] = j;
                        res[1] = dist[j];
                    }
                    q.offer(j);
                }
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    final int N = 10010, M = N * 2;
    int idx;
    int[] h, e, ne;
    int[] q, dist;
    boolean[] st;
    public int treeDiameter2(int[][] edges) {
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        q = new int[N];
        dist = new int[N];
        st = new boolean[N];
        Arrays.fill(h, -1);
        idx = 0;

        for (int[] e : edges) {
            int a = e[0], b = e[1];
            add(a, b);
            add(b, a);
        }

        int a = bfs(0);
        int b = bfs(a);
        return dist[b];
    }

    private int bfs(int start) {
        Arrays.fill(dist, -1);
        dist[start] = 0;
        int hh = 0, tt = 0;
        q[0] = start;
        int max = 0, id = start;

        while (hh <= tt) {
            int t = q[hh++];
            for (int i = h[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] != -1) continue;
                q[++tt] = j;
                dist[j] = dist[t] + 1;
                if (dist[j] > max) {
                    max = dist[j];
                    id = j;
                }
            }
        }
        return id;
    }

    private void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}
/**
 * 对于一张无向图的树，求最长的点到点距离，有一个非常成熟的做法。
 * 我们可以任意取一点A，用BFS找到距离A最远的节点B。那么B一定是就是“最长的点到点距离”的其中一个点。
 * 然后我们再以B为起点，用BFS找到距离B最远的节点C。那么BC就是答案。
 */
