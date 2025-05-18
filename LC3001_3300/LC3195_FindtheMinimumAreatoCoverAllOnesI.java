package LC3001_3300;

public class LC3195_FindtheMinimumAreatoCoverAllOnesI {
    /**
     * You are given a 2D binary array grid. Find a rectangle with horizontal and vertical sides with the smallest area,
     * such that all the 1's in grid lie inside this rectangle.
     *
     * Return the minimum possible area of the rectangle.
     *
     * Input: grid = [[0,1,0],[1,0,1]]
     * Output: 6
     *
     * Input: grid = [[0,0],[1,0]]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= grid.length, grid[i].length <= 1000
     * grid[i][j] is either 0 or 1.
     * The input is generated such that there is at least one 1 in grid.
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(1)
    public int minimumArea(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int xl = 1010, yl = 1010, xr = 0, yr = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    xl = Math.min(xl, i);
                    xr = Math.max(xr, i);
                    yl = Math.min(yl, j);
                    yr = Math.max(yr, j);
                }
            }
        }
        return (xr - xl + 1) * (yr - yl + 1);
    }
}