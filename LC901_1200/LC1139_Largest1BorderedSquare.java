package LC901_1200;

public class LC1139_Largest1BorderedSquare {
    /**
     * Given a 2D grid of 0s and 1s, return the number of elements in the largest square subgrid that has all 1s on its
     * border, or 0 if such a subgrid doesn't exist in the grid.
     *
     * Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
     * Output: 9
     *
     * Input: grid = [[1,1,0,0]]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= grid.length <= 100
     * 1 <= grid[0].length <= 100
     * grid[i][j] is 0 or 1
     * @param grid
     * @return
     */
    // time = O(n^3), space = O(n^2)
    int[][] s;
    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        s = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }

        for (int len = n; len > 1; len--) {
            for (int i = 1; i + len - 1 <= m; i++) {
                for (int j = 1; j + len - 1 <= n; j++) {
                    int a = i, b = j, c = i + len - 1, d = j + len - 1;
                    if (get(a, b, c, d) - get(a + 1, b + 1, c - 1, d - 1) == 4 * (len - 1)) {
                        return len * len;
                    }
                }
            }
        }
        return s[m][n] > 0 ? 1 : 0;
    }

    private int get(int x1, int y1, int x2, int y2) {
        return s[x2][y2] - s[x1 - 1][y2] - s[x2][y1 - 1] + s[x1 - 1][y1 - 1];
    }
}