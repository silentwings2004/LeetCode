package LC2401_2700;
import java.util.*;
public class LC2664_TheKnightsTour {
    /**
     * Given two positive integers m and n which are the height and width of a 0-indexed 2D-array board, a pair of
     * positive integers (r, c) which is the starting position of the knight on the board.
     *
     * Your task is to find an order of movements for the knight, in a manner that every cell of the board gets visited
     * exactly once (the starting cell is considered visited and you shouldn't visit it again).
     *
     * Return the array board in which the cells' values show the order of visiting the cell starting from 0 (the
     * initial place of the knight).
     *
     * Note that a knight can move from cell (r1, c1) to cell (r2, c2) if 0 <= r2 <= m - 1 and 0 <= c2 <= n - 1 and
     * min(abs(r1 - r2), abs(c1 - c2)) = 1 and max(abs(r1 - r2), abs(c1 - c2)) = 2.
     *
     * Input: m = 1, n = 1, r = 0, c = 0
     * Output: [[0]]
     *
     * Input: m = 3, n = 4, r = 0, c = 0
     * Output: [[0,3,6,9],[11,8,1,4],[2,5,10,7]]
     *
     * Constraints:
     *
     * 1 <= m, n <= 5
     * 0 <= r <= m - 1
     * 0 <= c <= n - 1
     * The inputs will be generated such that there exists at least one possible order of movements with the given
     * condition
     * @param m
     * @param n
     * @param r
     * @param c
     * @return
     */
    // time = O((m * n)^7), space = O(m * n)
    int m, n;
    int[][] g;
    int[][] directions = new int[][]{{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {2, 1}, {2, -1}, {1, 2}, {1, -2}};
    public int[][] tourOfKnight(int m, int n, int r, int c) {
        this.m = m;
        this.n = n;
        g = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(g[i], -1);
        g[r][c] = 0;
        dfs(r, c, 1);
        return g;
    }

    private boolean dfs(int x, int y, int t) {
        if (t == m * n) return true;

        for (int[] dir : directions) {
            int a = x + dir[0], b = y + dir[1];
            if (a < 0 || a >= m || b < 0 || b >= n) continue;
            if (g[a][b] != -1) continue;
            g[a][b] = t;
            if (dfs(a, b, t + 1)) return true;
            g[a][b] = -1;
        }
        return false;
    }
}