package LC001_300;
import java.util.*;
public class LC63_UniquePathsII {
    /**
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     *
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right
     * corner of the grid (marked 'Finish' in the diagram below).
     *
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
     *
     * An obstacle and space is marked as 1 and 0 respectively in the grid.
     *
     * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
     * Output: 2
     *
     * Constraints:
     *
     * m == obstacleGrid.length
     * n == obstacleGrid[i].length
     * 1 <= m, n <= 100
     * obstacleGrid[i][j] is 0 or 1.
     * @param obstacleGrid
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] f = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    if (i == 0 && j == 0) f[i][j] = 1;
                    else {
                        if (i > 0) f[i][j] += f[i - 1][j];
                        if (j > 0) f[i][j] += f[i][j - 1];
                    }
                }
            }
        }
        return f[m - 1][n - 1];
    }
}