package LC2401_2700;
import java.util.*;
public class LC2500_DeleteGreatestValueinEachRow {
    /**
     * You are given an m x n matrix grid consisting of positive integers.
     *
     * Perform the following operation until grid becomes empty:
     *
     * Delete the element with the greatest value from each row. If multiple such elements exist, delete any of them.
     * Add the maximum of deleted elements to the answer.
     * Note that the number of columns decreases by one after each operation.
     *
     * Return the answer after performing the operations described above.
     *
     * Input: grid = [[1,2,4],[3,3,1]]
     * Output: 8
     *
     * Input: grid = [[10]]
     * Output: 10
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 50
     * 1 <= grid[i][j] <= 100
     * @param grid
     * @return
     */
    // S1: sort
    // time = O(m * nlogn), space = O(1)
    public int deleteGreatestValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int[] g : grid) Arrays.sort(g); // O(m * nlogn)
        int res = 0;
        for (int j = n - 1; j >= 0; j--) { // O(m * n)
            int mx = 0;
            for (int i = 0; i < m; i++) {
                mx = Math.max(mx, grid[i][j]);
            }
            res += mx;
        }
        return res;
    }

    // S2: brute-force
    // time = O(m * n * n), space = O(1)
    public int deleteGreatestValue2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = 0;
        for (int k = 0; k < n; k++) {
            int tmax = 0;
            for (int i = 0; i < m; i++) {
                int max = 0, x = i, y = 0;
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == -1) continue;
                    if (grid[i][j] > max) {
                        max = grid[i][j];
                        y = j;
                    }
                }
                grid[x][y] = -1;
                tmax = Math.max(tmax, max);
            }
            res += tmax;
        }
        return res;
    }
}
