package LC301_600;
import java.util.*;
public class LC542_01Matrix {
    /**
     * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
     *
     * The distance between two adjacent cells is 1.
     *
     * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
     * Output: [[0,0,0],[0,1,0],[0,0,0]]
     *
     * Constraints:
     *
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 10^4
     * 1 <= m * n <= 10^4
     * mat[i][j] is either 0 or 1.
     * There is at least one 0 in mat.
     * @param mat
     * @return
     */
    // time = O(m * n), space = O(m * n)
    final int N = 10010;
    public int[][] updateMatrix(int[][] mat) {
        int[][] q = new int[N][2];
        int m = mat.length, n = mat[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dist[i], -1);
        int hh = 0, tt = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    q[++tt] = new int[]{i, j};
                    dist[i][j] = 0;
                }
            }
        }

        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        while (hh <= tt) {
            int[] t = q[hh++];
            int x = t[0], y = t[1];
            for (int i = 0; i < 4; i++) {
                int a = x + dx[i], b = y + dy[i];
                if (a < 0 || a >= m || b < 0 || b >= n) continue;
                if (dist[a][b] != -1) continue;
                q[++tt] = new int[]{a, b};
                dist[a][b] = dist[x][y] + 1;
            }
        }
        return dist;
    }
}
/**
 * 多源bfs问题
 */