package LC2401_2700;

public class LC2428_MaximumSumofanHourglass {
    /**
     * You are given an m x n integer matrix grid.
     *
     * We define an hourglass as a part of the matrix with the following form:
     *
     * Return the maximum sum of the elements of an hourglass.
     *
     * Note that an hourglass cannot be rotated and must be entirely contained within the matrix.
     *
     * Input: grid = [[6,2,1,3],[4,2,1,5],[9,2,8,7],[4,1,2,9]]
     * Output: 30
     *
     * Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
     * Output: 35
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 3 <= m, n <= 150
     * 0 <= grid[i][j] <= 106
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(1)
    public int maxSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                int s = grid[i][j];
                for (int k = j - 1; k <= j + 1; k++) {
                    s += grid[i - 1][k] + grid[i + 1][k];
                }
                res = Math.max(res, s);
            }
        }
        return res;
    }
}
