package LC3301_3600;
import java.util.*;
public class LC3311_Construct2DGridMatchingGraphLayout {
    /**
     * You are given a 2D integer array edges representing an undirected graph having n nodes, where edges[i] = [ui, vi]
     * denotes an edge between nodes ui and vi.
     *
     * Construct a 2D grid that satisfies these conditions:
     *
     * The grid contains all nodes from 0 to n - 1 in its cells, with each node appearing exactly once.
     * Two nodes should be in adjacent grid cells (horizontally or vertically) if and only if there is an edge between
     * them in edges.
     * It is guaranteed that edges can form a 2D grid that satisfies the conditions.
     *
     * Return a 2D integer array satisfying the conditions above. If there are multiple solutions, return any of them.
     *
     * Input: n = 4, edges = [[0,1],[0,2],[1,3],[2,3]]
     * Output: [[3,1],[2,0]]
     *
     * Input: n = 5, edges = [[0,1],[1,3],[2,3],[2,4]]
     * Output: [[4,2,3,1,0]]
     *
     * Input: n = 9, edges = [[0,1],[0,4],[0,5],[1,7],[2,3],[2,4],[2,5],[3,6],[4,6],[4,7],[6,8],[7,8]]
     * Output: [[8,6,3],[7,4,2],[1,0,5]]
     *
     * Constraints:
     *
     * 2 <= n <= 5 * 10^4
     * 1 <= edges.length <= 10^5
     * edges[i] = [ui, vi]
     * 0 <= ui < vi < n
     * All the edges are distinct.
     * The input is generated such that edges can form a 2D grid that satisfies the conditions.
     * @param n
     * @param edges
     * @return
     */
    // time = O(m + n), space = O(m + n)
    List<Integer>[] adj;
    int[][] res;
    boolean[] st;
    public int[][] constructGridLayout(int n, int[][] edges) {
        List<Integer>[] ds = new List[5];
        adj = new List[n];
        for (int i = 0; i < 5; i++) ds[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        int[] d = new int[n];
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj[a].add(b);
            adj[b].add(a);
            d[a]++;
            d[b]++;
        }

        for (int i = 0; i < n; i++) ds[d[i]].add(i);
        st = new boolean[n];

        if (ds[1].size() > 0) { // 1 col
            res = new int[n][1];
            res[0][0] = ds[1].get(0);
            int p = res[0][0];
            st[p] = true;
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{p, 0, 0});
            bfs(q);
        } else if (ds[4].size() == 0) { // 2 col
            res = new int[n / 2][2];
            res[0][0] = ds[2].get(0);
            int p = res[0][0];
            st[p] = true;
            for (int x : adj[p]) {
                if (d[x] == 2) {
                    res[0][1] = x;
                    st[x] = true;
                    break;
                }
            }
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{res[0][0], 0, 0});
            q.offer(new int[]{res[0][1], 0, 1});
            bfs(q);
        } else {
            int p = ds[2].get(0);
            List<Integer> tmp = new ArrayList<>();
            tmp.add(p);
            st[p] = true;
            while (true) {
                boolean flag = false;
                for (int x : adj[p]) {
                    if (st[x] || d[x] > 3) continue;
                    st[x] = true;
                    tmp.add(x);
                    if (d[x] == 2) {
                        flag = true;
                        break;
                    }
                    p = x;
                    break;
                }
                if (flag) break;
            }
            int col = tmp.size();
            res = new int[n / col][col];
            Queue<int[]> q = new LinkedList<>();
            for (int i = 0; i < col; i++) {
                res[0][i] = tmp.get(i);
                q.offer(new int[]{res[0][i], 0, i});
                st[res[0][i]] = true;
            }
            bfs(q);
        }
        return res;
    }

    private void bfs(Queue<int[]> q) {
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz-- > 0) {
                int[] t = q.poll();
                int u = t[0], x = t[1], y = t[2];
                for (int v : adj[u]) {
                    if (st[v]) continue;
                    st[v] = true;
                    res[x + 1][y] = v;
                    q.offer(new int[]{v, x + 1, y});
                }
            }
        }
    }
}