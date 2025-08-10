package LC3601_3900;

public class LC3643_FlipSquareSubmatrixVertically {
    /**
     * You are given an m x n integer matrix grid, and three integers x, y, and k.
     *
     * The integers x and y represent the row and column indices of the top-left corner of a square submatrix and the
     * integer k represents the size (side length) of the square submatrix.
     *
     * Your task is to flip the submatrix by reversing the order of its rows vertically.
     *
     * Return the updated matrix.
     *
     * Input: grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], x = 1, y = 0, k = 3
     * Output: [[1,2,3,4],[13,14,15,8],[9,10,11,12],[5,6,7,16]]
     *
     * Input: grid = [[3,4,2,3],[2,3,4,2]], x = 0, y = 2, k = 2
     * Output: [[3,4,4,2],[2,3,2,3]]
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 50
     * 1 <= grid[i][j] <= 100
     * 0 <= x < m
     * 0 <= y < n
     * 1 <= k <= min(m - x, n - y)
     * @param grid
     * @param x
     * @param y
     * @param k
     * @return
     */
    // time = O(k^2), space = O(1)
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        for (int i = 0; i < k / 2; i++) {
            int r1 = x + i, r2 = x + k - 1 - i;
            for (int j = 0; j < k; j++) {
                int c = y + j;
                int t = grid[r1][c];
                grid[r1][c] = grid[r2][c];
                grid[r2][c] = t;
            }
        }
        return grid;
    }
}