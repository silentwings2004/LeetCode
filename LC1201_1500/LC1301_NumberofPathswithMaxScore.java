package LC1201_1500;
import java.util.*;
public class LC1301_NumberofPathswithMaxScore {
    /**
     * You are given a square board of characters. You can move on the board starting at the bottom right square marked
     * with the character 'S'.
     *
     * You need to reach the top left square marked with the character 'E'. The rest of the squares are labeled either
     * with a numeric character 1, 2, ..., 9 or with an obstacle 'X'. In one move you can go up, left or up-left
     * (diagonally) only if there is no obstacle there.
     *
     * Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect, and
     * the second is the number of such paths that you can take to get that maximum sum, taken modulo 10^9 + 7.
     *
     * In case there is no path, return [0, 0].
     *
     * Input: board = ["E23","2X2","12S"]
     * Output: [7,1]
     *
     * Input: board = ["E12","1X1","21S"]
     * Output: [4,2]
     *
     * Input: board = ["E11","XXX","11S"]
     * Output: [0,0]
     *
     * Constraints:
     *
     * 2 <= board.length == board[i].length <= 100
     * @param board
     * @return
     */
    // time = O(n^2), space = O(n^2)
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size(), mod = (int)1e9 + 7;
        int[][] f = new int[n][n], g = new int[n][n];
        g[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0 || board.get(i).charAt(j) == 'X') continue;
                int t = 0, r = 0;
                if (i > 0) t = Math.max(t, f[i - 1][j]);
                if (j > 0) t = Math.max(t, f[i][j - 1]);
                if (i > 0 && j > 0) t = Math.max(t, f[i - 1][j - 1]);
                if (i > 0 && t == f[i - 1][j]) r = (r + g[i - 1][j]) % mod;
                if (j > 0 && t == f[i][j - 1]) r = (r + g[i][j - 1]) % mod;
                if (i > 0 && j > 0 && t == f[i - 1][j - 1]) r = (r + g[i - 1][j - 1]) % mod;
                t += board.get(i).charAt(j) == 'S' ? 0 : board.get(i).charAt(j) - '0';
                f[i][j] = t;
                g[i][j] = r;
            }
        }
        if (g[n - 1][n - 1] == 0) return new int[]{0, 0};
        return new int[]{f[n - 1][n - 1], g[n - 1][n - 1]};
    }
}
/**
 * dp
 * 1.状态表示：f(i,j), g(i,j)
 * 集合：从左上角走到(i,j)的所有走法的集合
 * 属性：f(i,j)存最大值，g(i,j)存最大值的方案数
 * 2. 状态计算：
 */