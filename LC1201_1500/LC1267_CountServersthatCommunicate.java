package LC1201_1500;

public class LC1267_CountServersthatCommunicate {
    /**
     * You are given a map of a server center, represented as a m * n integer matrix grid, where 1 means that on that
     * cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on the
     * same row or on the same column.
     *
     * Return the number of servers that communicate with any other server.
     *
     * Input: grid = [[1,0],[0,1]]
     * Output: 0
     *
     * Input: grid = [[1,0],[1,1]]
     * Output: 3
     *
     * Input: grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
     * Output: 4
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m <= 250
     * 1 <= n <= 250
     * grid[i][j] == 0 or 1
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(m + n)
    public int countServers(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] r = new int[m], c = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    r[i]++;
                    c[j]++;
                }
            }
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    if (r[i] > 1 || c[j] > 1) res++;
                }
            }
        }
        return res;
    }
}