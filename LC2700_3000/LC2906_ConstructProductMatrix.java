package LC2700_3000;

public class LC2906_ConstructProductMatrix {
    /**
     * Given a 0-indexed 2D integer matrix grid of size n * m, we define a 0-indexed 2D matrix p of size n * m as the
     * product matrix of grid if the following condition is met:
     *
     * Each element p[i][j] is calculated as the product of all elements in grid except for the element grid[i][j]. This
     * product is then taken modulo 12345.
     * Return the product matrix of grid.
     *
     * Input: grid = [[1,2],[3,4]]
     * Output: [[24,12],[8,6]]
     *
     * Input: grid = [[12345],[2],[1]]
     * Output: [[2],[0],[0]]
     *
     * Constraints:
     *
     * 1 <= n == grid.length <= 10^5
     * 1 <= m == grid[i].length <= 10^5
     * 2 <= n * m <= 10^5
     * 1 <= grid[i][j] <= 10^9
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(1)
    public int[][] constructProductMatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length, mod = 12345;
        int[][] res = new int[m][n];
        res[0][0] = 1;
        for (int i = 1; i < m * n; i++) {
            int x = i / n, y = i % n;
            int a = (i - 1) / n, b = (i - 1) % n;
            res[x][y] = (int)(1L * res[a][b] * grid[a][b] % mod);
        }
        long right = 1;
        for (int i = m * n - 1; i >= 0; i--) {
            int x = i / n, y = i % n;
            res[x][y] = (int)(1L * res[x][y] * right % mod);
            right = right * grid[x][y] % mod;
        }
        return res;
    }
}