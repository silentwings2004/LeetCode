package LC601_900;

public class LC892_SurfaceAreaof3DShapes {
    /**
     * You are given an n x n grid where you have placed some 1 x 1 x 1 cubes. Each value v = grid[i][j] represents a
     * tower of v cubes placed on top of cell (i, j).
     *
     * After placing these cubes, you have decided to glue any directly adjacent cubes to each other, forming several
     * irregular 3D shapes.
     *
     * Return the total surface area of the resulting shapes.
     *
     * Note: The bottom face of each shape counts toward its surface area.
     *
     * Input: grid = [[1,2],[3,4]]
     * Output: 34
     *
     * Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
     * Output: 32
     *
     * Input: grid = [[2,2,2],[2,1,2],[2,2,2]]
     * Output: 46
     *
     * Constraints:
     *
     * n == grid.length == grid[i].length
     * 1 <= n <= 50
     * 0 <= grid[i][j] <= 50
     * @param grid
     * @return
     */
    // S1
    // time = O(m * n), space = O(1)
    public int surfaceArea(int[][] grid) {
        int n = grid.length, res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) res += grid[i][j] * 4 + 2;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                res -= 2 * Math.min(grid[i][j], grid[i][j - 1]);
            }
        }

        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                res -= Math.min(grid[i][j], grid[i - 1][j]);
            }
        }
        return res;
    }

    // S2
    // time = O(m * n), space = O(1)
    public int surfaceArea2(int[][] grid) {
        int n = grid.length, res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                res += Math.abs(grid[i][j] - grid[i][j - 1]);
                res += Math.abs(grid[j][i] - grid[j - 1][i]);
                if (grid[i][j] > 0) res += 2;
            }
            res += grid[i][0] + grid[i][n - 1];
            res += grid[0][i] + grid[n - 1][i];
            if (grid[i][0] > 0) res += 2;
        }
        return res;
    }
}