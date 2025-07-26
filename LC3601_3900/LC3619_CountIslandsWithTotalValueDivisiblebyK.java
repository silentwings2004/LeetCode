package LC3601_3900;

public class LC3619_CountIslandsWithTotalValueDivisiblebyK {
    /**
     * You are given an m x n matrix grid and a positive integer k. An island is a group of positive integers
     * (representing land) that are 4-directionally connected (horizontally or vertically).
     *
     * The total value of an island is the sum of the values of all cells in the island.
     *
     * Return the number of islands with a total value divisible by k.
     *
     * Input: grid = [[0,2,1,0,0],[0,5,0,0,5],[0,0,1,0,0],[0,1,4,7,0],[0,2,0,0,8]], k = 5
     * Output: 2
     *
     * Input: grid = [[3,0,3,0], [0,3,0,3], [3,0,3,0]], k = 3
     * Output: 6
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 1000
     * 1 <= m * n <= 10^5
     * 0 <= grid[i][j] <= 10^6
     * 1 <= k <= 10^6
     * @param grid
     * @param k
     * @return
     */
    // time = O(m * n), space = O(m * n)
    int[][] g;
    int m, n;
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    public int countIslands(int[][] grid, int k) {
        g = grid;
        m = g.length;
        n = g[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] > 0) {
                    if (dfs(i, j) % k == 0) res++;
                }
            }
        }
        return res;
    }

    private long dfs(int x, int y) {
        long t = g[x][y];
        g[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a < 0 || a >= m || b < 0 || b >= n) continue;
            if (g[a][b] == 0) continue;
            t += dfs(a, b);
        }
        return t;
    }
}