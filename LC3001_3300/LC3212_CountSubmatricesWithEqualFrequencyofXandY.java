package LC3001_3300;

public class LC3212_CountSubmatricesWithEqualFrequencyofXandY {
    /**
     * Given a 2D character matrix grid, where grid[i][j] is either 'X', 'Y', or '.', return the number of
     * submatrices that contains:
     *
     * grid[0][0]
     * an equal frequency of 'X' and 'Y'.
     * at least one 'X'.
     *
     * Input: grid = [["X","Y","."],["Y",".","."]]
     * Output: 3
     *
     * Input: grid = [["X","X"],["X","Y"]]
     * Output: 0
     *
     * Input: grid = [[".","."],[".","."]]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= grid.length, grid[i].length <= 1000
     * grid[i][j] is either 'X', 'Y', or '.'.
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length, n = grid[0].length, res = 0;
        int[][] s = new int[m + 1][n + 1];
        int[][] t = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + get(grid[i - 1][j - 1]);
                t[i][j] = t[i - 1][j] + t[i][j - 1] - t[i - 1][j - 1] + (grid[i - 1][j - 1] == 'X' ? 1 : 0);
                if (s[i][j] == 0 && t[i][j] > 0) res++;
            }
        }
        return res;
    }

    private int get(char c) {
        if (c == 'X') return 1;
        if (c == 'Y') return -1;
        return 0;
    }

    // S2
    // time = O(m * n), space = O(n)
    public int numberOfSubmatrices2(char[][] grid) {
        int m = grid.length, n = grid[0].length, res = 0;
        int[][] cnt = new int[n][2];
        for (int i = 0; i < m; i++) {
            int s0 = 0, s1 = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'X') cnt[j][0]++;
                else if (grid[i][j] == 'Y') cnt[j][1]++;
                s0 += cnt[j][0];
                s1 += cnt[j][1];
                if (s0 > 0 && s0 == s1) res++;
            }
        }
        return res;
    }
}
/**
 * O(n) 空间的做法
 */