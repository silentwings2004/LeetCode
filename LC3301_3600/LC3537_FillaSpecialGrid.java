package LC3301_3600;

public class LC3537_FillaSpecialGrid {
    /**
     * You are given a non-negative integer N representing a 2^N x 2^N grid. You must fill the grid with integers from 0
     * to 2^2N - 1 to make it special. A grid is special if it satisfies all the following conditions:
     *
     * All numbers in the top-right quadrant are smaller than those in the bottom-right quadrant.
     * All numbers in the bottom-right quadrant are smaller than those in the bottom-left quadrant.
     * All numbers in the bottom-left quadrant are smaller than those in the top-left quadrant.
     * Each of its quadrants is also a special grid.
     * Return the special 2^N x 2^N grid.
     *
     * Note: Any 1x1 grid is special.
     *
     * Input: N = 0
     * Output: [[0]]
     *
     * Input: N = 1
     * Output: [[3,0],[2,1]]
     *
     * Input: N = 2
     * Output: [[15,12,3,0],[14,13,2,1],[11,8,7,4],[10,9,6,5]]
     *
     * Constraints:
     *
     * 0 <= N <= 10
     * @param N
     * @return
     */
    // S1: dfs
    // time = O(4^n), space = O(n)
    int v;
    public int[][] specialGrid(int n) {
        int[][] g = new int[1 << n][1 << n];
        dfs(g, 0, 1 << n, 0, 1 << n);
        return g;
    }

    private void dfs(int[][] g, int u, int d, int l, int r) {
        if (d - u == 1) {
            g[u][l] = v++;
            return;
        }
        int m = (d - u) / 2;
        dfs(g, u, u + m, l + m, r);
        dfs(g, u + m, d, l + m, r);
        dfs(g, u + m, d, l, l + m);
        dfs(g, u, u + m, l, l + m);
    }

    // S2
    // time = O(4^n), space = O(n)
    public int[][] specialGrid2(int n) {
        int[][] g = new int[1][1];
        int t = 1;
        for (int k = 1; k <= n; k++) {
            int m = t * t, x = t << 1;
            int[][] f = new int[x][x];
            for (int i = 0; i < t; i++) {
                for (int j = 0; j < t; j++) {
                    int v = g[i][j];
                    f[i][j + t] = v;
                    f[i + t][j + t] = v + m;
                    f[i + t][j] = v + 2 * m;
                    f[i][j] = v + 3 * m;
                }
            }
            g = f;
            t = x;
        }
        return g;
    }
}
/**
 * 象限：4 等分
 * 递归：处理原问题和子问题的逻辑是一样的
 * 象限的 4 个信心 [0, 2^n)
 * m = (R - L) / 2
 * l + m
 * 右上：(u, l + m, u + m, r)
 * 右下：(u + m, l + m, d, r)
 * 左下: (u + m, l, d, l + m)
 * 左上：（u, l, u + m, l + m)
 */