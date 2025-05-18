package LC001_300;
import java.util.*;
public class LC59_SpiralMatrixII {
    /**
     * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
     *
     * Input: n = 3
     * Output: [[1,2,3],[8,9,4],[7,6,5]]
     *
     * Constraints:
     *
     * 1 <= n <= 20
     * @param n
     * @return
     */
    // time = O(n^2), space = O(1)
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        for (int i = 0, x = 0, y = 0, d = 1; i < n * n; i++) {
            res[x][y] = i + 1;
            int a = x + dx[d], b = y + dy[d];
            if (a < 0 || a >= n || b < 0 || b >= n || res[a][b] != 0) {
                d = (d + 1) % 4;
                a = x + dx[d];
                b = y + dy[d];
            }
            x = a;
            y = b;
        }
        return res;
    }
}