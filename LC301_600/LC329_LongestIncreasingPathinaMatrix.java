package LC301_600;
import java.util.*;
public class LC329_LongestIncreasingPathinaMatrix {
    /**
     * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
     *
     * From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or
     * move outside the boundary (i.e., wrap-around is not allowed).
     *
     * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
     * Output: 4
     *
     * Constraints:
     *
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 200
     * 0 <= matrix[i][j] <= 2^31 - 1
     * @param matrix
     * @return
     */
    // S1: dfs + memo
    // time = O(m * n), space = O(m * n)
    private int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int[][] memo;
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        memo = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfs(matrix, i, j));
            }
        }
        return res;
    }

    private int dfs(int[][] matrix, int i, int j) {
        int m = matrix.length, n = matrix[0].length;
        if (memo[i][j] != 0) return memo[i][j];

        int res = 0;
        for (int[] dir : directions) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n) continue;
            if (matrix[x][y] <= matrix[i][j]) continue;
            res = Math.max(res, dfs(matrix, x, y));
        }
        memo[i][j] = res + 1;
        return res + 1;
    }

    // S2: 记忆化搜索
    // time = O(m * n), space = O(m * n)
    int[][] g, f;
    int m, n;
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    public int longestIncreasingPath2(int[][] matrix) {
        g = matrix;
        m = g.length;
        n = g[0].length;
        f = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(f[i], -1);
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dp(i, j));
            }
        }
        return res;
    }

    private int dp(int x, int y) {
        if (f[x][y] != -1) return f[x][y];
        f[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a < 0 || a >= m || b < 0 || b >= n) continue;
            if (g[a][b] <= g[x][y]) continue;
            f[x][y] = Math.max(f[x][y], dp(a, b) + 1);
        }
        return f[x][y];
    }
}
/**
 * dfs(C) => 1 + (dfs(A), dfs(D))
 * dfs(A) = 1 + max(dfs(B1), dfs(B2))
 * 我们从任意点A开始递归寻找各条递增路径，最终返回的时候记录从A为起点时的最长路径长度。
 * 将此结果记忆化，这样当对其他点进行DFS的时候，如果递归调用到dfs(A)就直接返回结果。
 * dp的记忆化搜索写法
 * f(i,j): 以(i,j)为起点的上升路径的最大长度
 * 这题不存在环
 * 这里必须要严格上升，如果这个题说可以非严格上升，就不能用dp来做，可以用图论做法来做。
 * 相当于求图论里的长度，枚举下起点，dijkstra或者spfa
 * 需要保证按照拓扑序来写 -> dfs
 */