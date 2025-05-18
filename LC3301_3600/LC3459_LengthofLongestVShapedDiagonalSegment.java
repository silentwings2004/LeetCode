package LC3301_3600;

public class LC3459_LengthofLongestVShapedDiagonalSegment {
    /**
     * You are given a 2D integer matrix grid of size n x m, where each element is either 0, 1, or 2.
     *
     * A V-shaped diagonal segment is defined as:
     *
     * The segment starts with 1.
     * The subsequent elements follow this infinite sequence: 2, 0, 2, 0, ....
     * The segment:
     * Starts along a diagonal direction (top-left to bottom-right, bottom-right to top-left, top-right to bottom-left,
     * or bottom-left to top-right).
     * Continues the sequence in the same diagonal direction.
     * Makes at most one clockwise 90-degree turn to another diagonal direction while maintaining the sequence.
     *
     * Input: grid = [[2,2,1,2,2],[2,0,2,2,0],[2,0,1,1,0],[1,0,2,2,2],[2,0,0,2,2]]
     * Output: 5
     *
     * Input: grid = [[2,2,2,2,2],[2,0,2,2,0],[2,0,1,1,0],[1,0,2,2,2],[2,0,0,2,2]]
     * Output: 4
     *
     * Input: grid = [[1,2,2,2,2],[2,2,2,2,0],[2,0,0,0,0],[0,0,2,2,2],[2,0,0,2,0]]
     * Output: 5
     *
     * Input: grid = [[1]]
     * Output: 1
     *
     * Constraints:
     *
     * n == grid.length
     * m == grid[i].length
     * 1 <= n, m <= 500
     * grid[i][j] is either 0, 1 or 2.
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(m * n)
    int[] dx = new int[]{-1, -1, 1, 1}, dy = new int[]{-1, 1, 1, -1};
    int[][] grid;
    int[][][] f;
    int m, n;
    public int lenOfVDiagonal(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        f = new int[m][n][1 << 3];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        res = Math.max(res, dfs(i, j, k, 1, 2) + 1);
                    }
                }
            }
        }
        return res;
    }

    private int dfs(int i, int j, int k, int flag, int t) {
        i += dx[k];
        j += dy[k];
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != t) return 0;
        int state = k << 1 | flag;
        if (f[i][j][state] > 0) return f[i][j][state];

        int res = dfs(i, j, k, flag, 2 - t);
        if (flag == 1) res = Math.max(res, dfs(i, j, (k + 1) % 4, 0, 2 - t));
        return f[i][j][state] = res + 1;
    }
}