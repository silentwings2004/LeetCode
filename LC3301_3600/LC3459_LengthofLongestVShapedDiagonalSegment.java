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
    // S1
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

    // S2
    // time = O(m * n), space = O(m * n)
    class Solution {
        int[][] grid;
        int[][][][] f;
        int m, n;
        int[] dx = new int[]{-1, -1, 1, 1}, dy = new int[]{-1, 1, 1, -1};
        public int lenOfVDiagonal(int[][] grid) {
            this.grid = grid;
            m = grid.length;
            n = grid[0].length;
            f = new int[m][n][4][2];
            int res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        res = Math.max(res, dfs(i, j, 0, 1));
                        res = Math.max(res, dfs(i, j, 1, 1));
                        res = Math.max(res, dfs(i, j, 2, 1));
                        res = Math.max(res, dfs(i, j, 3, 1));
                    }
                }
            }
            return res;
        }

        private int dfs(int x, int y, int k, int t) {
            if (f[x][y][k][t] != 0) return f[x][y][k][t];
            int v = 1;

            int a = x + dx[k], b = y + dy[k];
            if (a >= 0 && a < m && b >= 0 && b < n && check(grid[x][y], grid[a][b])) {
                v = Math.max(v, dfs(a, b, k, t) + 1);
            }

            if (t == 1) {
                int k2 = (k + 1) % 4;
                a = x + dx[k];
                b = y + dy[k];
                if (a >= 0 && a < m && b >= 0 && b < n && check(grid[x][y], grid[a][b])) {
                    v = Math.max(v, dfs(a, b, k2, t - 1) + 1);
                }
            }
            return f[x][y][k][t] = v;
        }

        private boolean check(int a, int b) {
            if (a == 1) return b == 2;
            if (a == 2) return b == 0;
            if (a == 0) return b == 2;
            return false;
        }
    }
}
/**
 * 很常规的深度优先搜索。每个格子、每个方向只会进入一次。
 * 所以最多有500*500*4=1e6种状态。
 * 再加上有一次转弯的机会，所以2e6种状态是可以遍历和存储下来的。
 * 定义dfs(x,y,k,t)表示以k的方向进入(x,y)的格子、且还有t次转弯机会时，还能走的最长路径。
 * 如果t==0，那么只能按照k的方向进入下一个(i1,j1)；否则还可以考察按照k+1的方向进入下一个(i2,j2).
 * 注意进入的下一个各自(i,j)和(x,y)要满足数值上的约束，否则即可停止往下搜索。
 * 本题的记忆化根据四个参数进行记忆化也是必须的。
 */