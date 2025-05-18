package LC1501_1800;
import java.util.*;
public class LC1591_StrangePrinterII {
    /**
     * There is a strange printer with the following two special requirements:
     *
     * On each turn, the printer will print a solid rectangular pattern of a single color on the grid. This will cover
     * up the existing colors in the rectangle.
     * Once the printer has used a color for the above operation, the same color cannot be used again.
     * You are given a m x n matrix targetGrid, where targetGrid[row][col] is the color in the position (row, col) of
     * the grid.
     *
     * Return true if it is possible to print the matrix targetGrid, otherwise, return false.
     *
     * Input: targetGrid = [[1,1,1,1],[1,2,2,1],[1,2,2,1],[1,1,1,1]]
     * Output: true
     *
     * Constraints:
     *
     * m == targetGrid.length
     * n == targetGrid[i].length
     * 1 <= m, n <= 60
     * 1 <= targetGrid[row][col] <= 60
     * @param targetGrid
     * @return
     */
    // S1: dfs
    // time = O(V + E) = O(k * m * n), space = O(k)    k: number of colors
    List<Integer>[] graph;
    int[] st;
    public boolean isPrintable(int[][] targetGrid) {
        int m = targetGrid.length, n = targetGrid[0].length;
        int[] left = new int[61];
        int[] right = new int[61];
        int[] top = new int[61];
        int[] bottom = new int[61];
        Arrays.fill(left, n);
        Arrays.fill(right, -1);
        Arrays.fill(top, m);
        Arrays.fill(bottom, -1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int color = targetGrid[i][j];
                left[color] = Math.min(left[color], j);
                right[color] = Math.max(right[color], j);
                top[color] = Math.min(top[color], i);
                bottom[color] = Math.max(bottom[color], i);
            }
        }

        graph = new List[61];
        for (int i = 0; i < 61; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int color = 1; color <= 60; color++) {
                    if (i >= top[color] && i <= bottom[color] && j >= left[color] && j <= right[color]) {
                        if (targetGrid[i][j] != color) {
                            graph[targetGrid[i][j]].add(color);
                        }
                    }
                }
            }
        }

        st = new int[61];
        for (int i = 1; i <= 60; i++) {
            if (dfs(i)) return false;
        }
        return true;
    }

    private boolean dfs(int cur) {
        if (st[cur] == 2) return false;
        if (st[cur] == 1) return true;

        st[cur] = 1;

        for (int next : graph[cur]) {
            if (dfs(next)) return true;
        }
        st[cur] = 2;
        return false;
    }

    // S2: bfs
    // time = O(V + E) = O(k * m * n), space = O(k)    k: number of colors
    class Solution {
        List<Integer>[] graph;
        int[] indegree;
        public boolean isPrintable(int[][] targetGrid) {
            int m = targetGrid.length, n = targetGrid[0].length;
            int[] u = new int[61];
            int[] d = new int[61];
            int[] l = new int[61];
            int[] r = new int[61];
            Arrays.fill(u, m);
            Arrays.fill(d, -1);
            Arrays.fill(l, n);
            Arrays.fill(r, -1);

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int c = targetGrid[i][j];
                    u[c] = Math.min(u[c], i);
                    d[c] = Math.max(d[c], i);
                    l[c] = Math.min(l[c], j);
                    r[c] = Math.max(r[c], j);
                }
            }

            graph = new List[61];
            indegree = new int[61];
            for (int i = 0; i <= 60; i++) graph[i] = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (int c = 1; c <= 60; c++) {
                        if (i >= u[c] && i <= d[c] && j >= l[c] && j <= r[c]) {
                            if (targetGrid[i][j] != c) {
                                graph[targetGrid[i][j]].add(c);
                                indegree[c]++;
                            }
                        }
                    }
                }
            }

            if (bfs()) return false;
            return true;
        }

        private boolean bfs() {
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 1; i <= 60; i++) {
                if (indegree[i] == 0) queue.offer(i);
            }

            int cnt = 0;
            while (!queue.isEmpty()) {
                int t = queue.poll();
                cnt++;

                for (int next : graph[t]) {
                    if (--indegree[next] == 0) queue.offer(next);
                }
            }
            return cnt != 60;
        }
    }
}
/**
 *  1 2 1
 *  2 1 3
 *  2 4 4
 *
 *  1 1 1
 *  1 1 1
 *  1 1 1
 *
 * (0, 0): 1, 2  => 2 < 1
 * (2, 0): 1, 2  => 1 < 2
 * (2, 1): 1, 2, 4  => (1, 2) < 4
 * (1, 2): 1, 3 => 1 < 3
 * (2, 2): 1, 4  => 1 < 4
 * ....
 * {1, 2, 3, 4}
 *
 * 2, 1, 3, 4   取决于要求彼此是否有矛盾 => course schedule -> topological sort 判断是否有环！
 * 先后依赖的关系，不能相互矛盾 -> 构成了一个有向图，不能有环
 * 循环依赖 => 拓扑排序
 */
