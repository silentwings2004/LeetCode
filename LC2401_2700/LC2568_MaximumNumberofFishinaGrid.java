package LC2401_2700;

public class LC2568_MaximumNumberofFishinaGrid {
    /**
     * You are given a 0-indexed 2D matrix grid of size m x n, where (r, c) represents:
     *
     * A land cell if grid[r][c] = 0, or
     * A water cell containing grid[r][c] fish, if grid[r][c] > 0.
     * A fisher can start at any water cell (r, c) and can do the following operations any number of times:
     *
     * Catch all the fish at cell (r, c), or
     * Move to any adjacent water cell.
     * Return the maximum number of fish the fisher can catch if he chooses his starting cell optimally, or 0 if no
     * water cell exists.
     *
     * An adjacent cell of the cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) or (r - 1, c) if it
     * exists.
     *
     * Input: grid = [[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]
     * Output: 7
     *
     * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,1]]
     * Output: 1
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 10
     * 0 <= grid[i][j] <= 10
     * @param grid
     * @return
     */
    // time = O(m * n * log(m * n)), space = O(m * n)
    int[] p, w;
    public int findMaxFish(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        p = new int[m * n];
        w = new int[m * n];
        for (int i = 0; i < m * n; i++) p[i] = i;

        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    for (int k = 0; k < 4; k++) {
                        int a = i + dx[k], b = j + dy[k];
                        if (a < 0 || a >= m || b < 0 || b >= n) continue;
                        if (grid[a][b] == 0) continue;
                        int u = i * n + j, v = a * n + b;
                        p[find(u)] = find(v);
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = find(i * n + j);
                w[x] += grid[i][j];
                res = Math.max(res, w[x]);
            }
        }
        return res;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}