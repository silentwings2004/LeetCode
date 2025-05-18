package LC3001_3300;

public class LC3239_MinimumNumberofFlipstoMakeBinaryGridPalindromicI {
    /**
     * You are given an m x n binary matrix grid.
     *
     * A row or column is considered palindromic if its values read the same forward and backward.
     *
     * You can flip any number of cells in grid from 0 to 1, or from 1 to 0.
     *
     * Return the minimum number of cells that need to be flipped to make either all rows palindromic or all columns
     * palindromic.
     *
     * Input: grid = [[1,0,0],[0,0,0],[0,0,1]]
     * Output: 2
     *
     * Input: grid = [[0,1],[0,1],[0,0]]
     * Output: 1
     *
     * Input: grid = [[1],[0]]
     * Output: 0
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m * n <= 2 * 10^5
     * 0 <= grid[i][j] <= 1
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(1)
    public int minFlips(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int v1 = 0, v2 = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0, k = n - 1; j < k; j++, k--) {
                if (grid[i][j] != grid[i][k]) v1++;
            }
        }
        for (int j = 0; j < n; j++) {
            for (int i = 0, k = m - 1; i < k; i++, k--) {
                if (grid[i][j] != grid[k][j]) v2++;
            }
        }
        return Math.min(v1, v2);
    }
}