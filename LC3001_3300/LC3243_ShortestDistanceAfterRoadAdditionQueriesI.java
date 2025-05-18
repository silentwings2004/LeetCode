package LC3001_3300;
import java.util.*;
public class LC3243_ShortestDistanceAfterRoadAdditionQueriesI {
    /**
     * You are given an integer n and a 2D integer array queries.
     *
     * There are n cities numbered from 0 to n - 1. Initially, there is a unidirectional road from city i to city i + 1
     * for all 0 <= i < n - 1.
     *
     * queries[i] = [ui, vi] represents the addition of a new unidirectional road from city ui to city vi. After each
     * query, you need to find the length of the shortest path from city 0 to city n - 1.
     *
     * Return an array answer where for each i in the range [0, queries.length - 1], answer[i] is the length of the
     * shortest path from city 0 to city n - 1 after processing the first i + 1 queries.
     *
     * Input: n = 5, queries = [[2,4],[0,2],[0,4]]
     * Output: [3,2,1]
     *
     * Input: n = 4, queries = [[0,3],[0,2]]
     * Output: [1,1]
     *
     * Constraints:
     *
     * 3 <= n <= 500
     * 1 <= queries.length <= 500
     * queries[i].length == 2
     * 0 <= queries[i][0] < queries[i][1] < n
     * 1 < queries[i][1] - queries[i][0]
     * There are no repeated roads among the queries.
     * @param n
     * @param queries
     * @return
     */
    // time = O(m * n), space = O(n)
    List<Integer>[] adj;
    int n;
    int[] res;
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int m = queries.length;
        this.n = n;
        res = new int[m];
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i + 1 < n; i++) adj[i].add(i + 1);

        for (int i = 0; i < m; i++) {
            int a = queries[i][0], b = queries[i][1];
            adj[a].add(b);
            res[i] = bfs();
        }
        return res;
    }

    private int bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        boolean[] st = new boolean[n];
        st[0] = true;

        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz-- > 0) {
                int u = q.poll();
                if (u == n - 1) return step;
                for (int v : adj[u]) {
                    if (st[v]) continue;
                    q.offer(v);
                    st[v] = true;
                }
            }
            step++;
        }
        return -1;
    }
}