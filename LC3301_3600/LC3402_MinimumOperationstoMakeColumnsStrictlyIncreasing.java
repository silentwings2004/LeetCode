package LC3301_3600;

public class LC3402_MinimumOperationstoMakeColumnsStrictlyIncreasing {
    /**
     * You are given a m x n matrix grid consisting of non-negative integers.
     *
     * In one operation, you can increment the value of any grid[i][j] by 1.
     *
     * Return the minimum number of operations needed to make all columns of grid strictly increasing.
     *
     * Input: grid = [[3,2],[1,3],[3,4],[0,1]]
     * Output: 15
     *
     * Input: grid = [[3,2,1],[2,1,0],[1,2,3]]
     * Output: 12
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 50
     * 0 <= grid[i][j] < 2500
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(1)
    public int minimumOperations(int[][] grid) {
        int m = grid.length, n = grid[0].length, res = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < m; i++) {
                if (grid[i][j] > grid[i - 1][j]) continue;
                res += grid[i - 1][j] + 1 - grid[i][j];
                grid[i][j] = grid[i - 1][j] + 1;
            }
        }
        return res;
    }
}