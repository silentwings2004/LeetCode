package LC001_300;
import java.util.*;
public class LC130_SurroundedRegions {
    /**
     * Given an m x n matrix board containing 'X' and 'O', capture all regions surrounded by 'X'.
     *
     * A region is captured by flipping all 'O's into 'X's in that surrounded region.
     *
     * Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
     * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
     *
     * Constraints:
     *
     * m == board.length
     * n == board[i].length
     * 1 <= m, n <= 200
     * board[i][j] is 'X' or 'O'.
     * @param board
     */
    // time = O(m * n), space = O(m * n)
    char[][] g;
    int m, n;
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    public void solve(char[][] board) {
        g = board;
        m = g.length;
        n = g[0].length;

        for (int i = 0; i < m; i++) {
            if (g[i][0] == 'O') dfs(i, 0);
            if (g[i][n - 1] == 'O') dfs(i, n - 1);
        }
        for (int i = 0; i < n; i++) {
            if (g[0][i] == 'O') dfs(0, i);
            if (g[m - 1][i] == 'O') dfs(m - 1, i);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 'O') g[i][j] = 'X';
                if (g[i][j] == 'Y') g[i][j] = 'O';
            }
        }
    }

    private void dfs(int x, int y) {
        g[x][y] = 'Y';
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a < 0 || a >= m || b < 0 || b >= n) continue;
            if (g[a][b] != 'O') continue;
            dfs(a, b);
        }
    }
}
/**
 * flood fill 算法
 * 直接在沿海地带找，有O的话肯定不会被X包围，反着来
 */
