package LC001_300;
import java.util.*;
public class LC79_WordSearch {
    /**
     * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
     *
     * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally
     * or vertically neighboring. The same letter cell may not be used more than once.
     *
     * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
     * Output: true
     *
     * Constraints:
     *
     * m == board.length
     * n = board[i].length
     * 1 <= m, n <= 6
     * 1 <= word.length <= 15
     * board and word consists of only lowercase and uppercase English letters.
     *
     *
     * Follow up: Could you use search pruning to make your solution faster with a larger board?
     * @param board
     * @param word
     * @return
     */
    // time = O(m * n * 3^k), space = O(m * n) * k
    char[][] g;
    int m, n;
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    public boolean exist(char[][] board, String word) {
        g = board;
        m = g.length;
        n = g[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j, 0, word)) return true;
            }
        }
        return false;
    }

    private boolean dfs(int x, int y, int u, String s) {
        if (g[x][y] != s.charAt(u)) return false;
        if (u == s.length() - 1) return true;

        char c = g[x][y];
        g[x][y] = '#';
        for (int k = 0; k < 4; k++) {
            int a = x + dx[k], b = y + dy[k];
            if (a < 0 || a >= m || b < 0 || b >= n) continue;
            if (g[a][b] == '#') continue;
            if (dfs(a, b, u + 1, s)) return true;
        }
        g[x][y] = c;
        return false;
    }
}