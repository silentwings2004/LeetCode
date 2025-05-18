package LC2101_2400;

public class LC2373_LargestLocalValuesinaMatrix {
    /**
     * You are given an n x n integer matrix grid.
     *
     * Generate an integer matrix maxLocal of size (n - 2) x (n - 2) such that:
     *
     * maxLocal[i][j] is equal to the largest value of the 3 x 3 matrix in grid centered around row i + 1 and column j + 1.
     * In other words, we want to find the largest value in every contiguous 3 x 3 matrix in grid.
     *
     * Return the generated matrix.
     *
     * Input: grid = [[9,9,8,1],[5,6,2,6],[8,2,6,4],[6,2,2,2]]
     * Output: [[9,9],[8,6]]
     *
     * Constraints:
     *
     * n == grid.length == grid[i].length
     * 3 <= n <= 100
     * 1 <= grid[i][j] <= 100
     * @param grid
     * @return
     */
    // time = O(n^2), space = O(n^2)
    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] res = new int[n - 2][n - 2];

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                for (int x = i - 1; x <= i + 1; x++) {
                    for (int y = j - 1; y <= j + 1; y++) {
                        res[i - 1][j - 1] = Math.max(res[i - 1][j - 1], grid[x][y]);
                    }
                }
            }
        }
        return res;
    }
}
