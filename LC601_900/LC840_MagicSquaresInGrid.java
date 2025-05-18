package LC601_900;

public class LC840_MagicSquaresInGrid {
    /**
     * A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and
     * both diagonals all have the same sum.
     *
     * Given a row x col grid of integers, how many 3 x 3 "magic square" subgrids are there?
     * (Each subgrid is contiguous).
     *
     * Input: grid = [[4,3,8,4],[9,5,1,9],[2,7,6,2]]
     * Output: 1
     *
     * Input: grid = [[8]]
     * Output: 0
     *
     * Constraints:
     *
     * row == grid.length
     * col == grid[i].length
     * 1 <= row, col <= 10
     * 0 <= grid[i][j] <= 15
     * @param grid
     * @return
     */
    // time = O(m * n * 9), space = O(m * n)
    int[][] g;
    public int numMagicSquaresInside(int[][] grid) {
        g = grid;
        int m = g.length, n = g[0].length, res = 0;
        for (int i = 0; i + 3 <= m; i++) {
            for (int j = 0; j + 3 <= n; j++) {
                if (check(i, j)) res++;
            }
        }
        return res;
    }

    private boolean check(int x, int y) {
        boolean[] st = new boolean[10];
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                int t = g[i][j];
                if (t < 1 || t > 9) return false;
                if (st[t]) return false;
                st[t] = true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (g[x + i][y] + g[x + i][y + 1] + g[x + i][y + 2] != 15) return false;
            if (g[x][y + i] + g[x + 1][y + i] + g[x + 2][y + i] != 15) return false;
        }

        if (g[x][y] + g[x + 1][y + 1] + g[x + 2][y + 2] != 15) return false;
        if (g[x + 2][y] + g[x + 1][y + 1] + g[x][y + 2] != 15) return false;
        return true;
    }
}
