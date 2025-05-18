package LC2401_2700;

public class LC2639_FindtheWidthofColumnsofaGrid {
    /**
     * You are given a 0-indexed m x n integer matrix grid. The width of a column is the maximum length of its integers.
     *
     * For example, if grid = [[-10], [3], [12]], the width of the only column is 3 since -10 is of length 3.
     * Return an integer array ans of size n where ans[i] is the width of the ith column.
     *
     * The length of an integer x with len digits is equal to len if x is non-negative, and len + 1 otherwise.
     *
     * Input: grid = [[1],[22],[333]]
     * Output: [3]
     *
     * Input: grid = [[-15,1,3],[15,7,12],[5,6,-2]]
     * Output: [3,1,2]
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 100
     * -10^9 <= grid[r][c] <= 10^9
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(1)
    public int[] findColumnWidth(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] res = new int[n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                res[j] = Math.max(res[j], String.valueOf(grid[i][j]).length());
            }
        }
        return res;
    }
}