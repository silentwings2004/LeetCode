package LC2101_2400;
import java.util.*;
public class LC2392_BuildaMatrixWithConditions {
    /**
     * You are given a positive integer k. You are also given:
     *
     * a 2D integer array rowConditions of size n where rowConditions[i] = [abovei, belowi], and
     * a 2D integer array colConditions of size m where colConditions[i] = [lefti, righti].
     * The two arrays contain integers from 1 to k.
     *
     * You have to build a k x k matrix that contains each of the numbers from 1 to k exactly once. The remaining cells
     * should have the value 0.
     *
     * The matrix should also satisfy the following conditions:
     *
     * The number abovei should appear in a row that is strictly above the row at which the number belowi appears for
     * all i from 0 to n - 1.
     * The number lefti should appear in a column that is strictly left of the column at which the number righti appears
     * for all i from 0 to m - 1.
     * Return any matrix that satisfies the conditions. If no answer exists, return an empty matrix.
     *
     * Input: k = 3, rowConditions = [[1,2],[3,2]], colConditions = [[2,1],[3,2]]
     * Output: [[3,0,0],[0,0,1],[0,2,0]]
     *
     * Input: k = 3, rowConditions = [[1,2],[2,3],[3,1],[2,3]], colConditions = [[2,1]]
     * Output: []
     *
     * Constraints:
     *
     * 2 <= k <= 400
     * 1 <= rowConditions.length, colConditions.length <= 10^4
     * rowConditions[i].length == colConditions[i].length == 2
     * 1 <= abovei, belowi, lefti, righti <= k
     * abovei != belowi
     * lefti != righti
     * @param k
     * @param rowConditions
     * @param colConditions
     * @return
     */
    // S1
    // time = O(k^2), space = O(k)
    int n;
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        n = k;
        List<Integer> row = topsort(rowConditions);
        List<Integer> col = topsort(colConditions);
        if (row.size() < n || col.size() < n) return new int[][]{};
        int[][] res = new int[n][n];
        for (int i = 1; i <= n; i++) {
            res[get(row, i)][get(col, i)] = i;
        }
        return res;
    }

    private int get(List<Integer> q, int x) {
        for (int i = 0; i < n; i++) {
            if (q.get(i) == x) return i;
        }
        return -1;
    }

    private List<Integer> topsort(int[][] edges) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        int[] indegree = new int[n + 1];
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            graph[a].add(b);
            indegree[b]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res.add(cur);
            for (int next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) queue.offer(next);
            }
        }
        return res;
    }

    // S1.2
    // time = O(k^2), space = O(k)
    public int[][] buildMatrix2(int k, int[][] rowConditions, int[][] colConditions) {
        int[] row = topo(k, rowConditions);
        int[] col = topo(k, colConditions);
        if (row.length == 0 || col.length == 0) return new int[][]{};

        int[][] pos = new int[k + 1][2]; // p[v]: (x, y) of v
        for (int i = 0; i < k; i++) pos[row[i]][0] = i;
        for (int j = 0; j < k; j++) pos[col[j]][1] = j;

        int[][] matrix = new int[k][k];
        for (int v = 1; v <= k; v++) matrix[pos[v][0]][pos[v][1]] = v;
        return matrix;
    }

    private int[] topo(int k, int[][] conditions) {
        List<Integer>[] graph = new List[k + 1];
        for (int i = 0; i <= k; i++) graph[i] = new ArrayList<>();
        int[] indegree = new int[k + 1];
        for (int[] x : conditions) {
            int a = x[0], b = x[1];
            graph[a].add(b);
            indegree[b]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= k; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        int[] res = new int[k];
        int idx = 0, cnt = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res[idx++] = cur;
            cnt++;
            for (int next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) queue.offer(next);
            }
        }
        return cnt == k ? res : new int[0];
    }

    // S3
    // time = O(k^2 + n + m), space = O(k + n + m)
    int k;
    public int[][] buildMatrix3(int k, int[][] rowConditions, int[][] colConditions) {
        this.k = k;
        int[] r = bfs(rowConditions);
        int[] c = bfs(colConditions);
        if (r == null || c == null) return new int[0][];
        int[][] g = new int[k][k];
        for (int i = 1; i <= k; i++) {
            int x = r[i], y = c[i];
            g[x][y] = i;
        }
        return g;
    }

    private int[] bfs(int[][] edges) {
        int[] d = new int[k + 1];
        List<Integer>[] adj = new List[k + 1];
        for (int i = 1; i <= k; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            d[b]++;
            adj[a].add(b);
        }

        Queue<int[]> q = new LinkedList<>();
        int r = 0;
        for (int i = 1; i <= k; i++) {
            if (d[i] == 0) q.offer(new int[]{i, r++});
        }

        int cnt = 0;
        int[] res = new int[k + 1];
        Arrays.fill(res, -1);
        while (!q.isEmpty()) {
            int[] t = q.poll();
            int u = t[0], x = t[1];
            res[u] = x;
            cnt++;
            for (int v : adj[u]) {
                if (--d[v] == 0) q.offer(new int[]{v, r++});
            }
        }
        if (cnt < k) return null;
        return res;
    }
}
/**
 * 行和列是完全独立的
 * 任意2个元素不在同一行和同一列！
 * 先去定义在行方向上的顺序，列方向上的顺序可以任意排
 * 行列分别去考虑
 * 拓扑排序问题
 *
 * 一系列约束 -> 节点关系转化成图，本质上就是个有向图
 * 观察入度为0的节点
 */