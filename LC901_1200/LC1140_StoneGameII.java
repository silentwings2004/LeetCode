package LC901_1200;
import java.util.*;
public class LC1140_StoneGameII {
    /**
     * Alice and Bob continue their games with piles of stones.  There are a number of piles arranged in a row, and
     * each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most
     * stones.
     *
     * Alice and Bob take turns, with Alice starting first.  Initially, M = 1.
     *
     * On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.
     * Then, we set M = max(M, X).
     *
     * The game continues until all the stones have been taken.
     *
     * Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.
     *
     * Input: piles = [2,7,9,4,4]
     * Output: 10
     *
     * Constraints:
     *
     * 1 <= piles.length <= 100
     * 1 <= piles[i] <= 10^4
     * @param piles
     * @return
     */
    // S1
    // time = O(n^3), space = O(n^2)
    int[] s;
    int[][] f;
    int n;
    public int stoneGameII(int[] piles) {
        s = piles;
        n = s.length;
        f = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i], -1);
        for (int i = n - 2; i >= 0; i--) s[i] += s[i + 1];
        return dfs(0, 1);
    }

    private int dfs(int u, int m) {
        if (u + m * 2 >= n) return s[u];
        if (f[u][m] != -1) return f[u][m];

        int t = 0;
        for (int x = 1; x <= m * 2; x++) {
            t = Math.max(t, s[u] - dfs(u + x, Math.max(x, m)));
        }
        f[u][m] = t;
        return t;
    }

    // S2
    // time = O(n^3), space = O(n^2)
    public int stoneGameII2(int[] piles) {
        int n = piles.length;
        int[][] f = new int[n + 2][n + 1];
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + piles[i - 1];

        for (int i = n; i > 0; i--) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; i + k - 1 <= n && k <= j * 2; k++) {
                    f[i][j] = Math.max(f[i][j], s[n] - s[i - 1] - f[i + k][Math.max(k, j)]);
                }
            }
        }
        return f[1][1];
    }
}
/**
 * M = 1, [1, 2]   X = 1
 * M = 2, [1, 4]   X = 3  solve(2,2)
 * M = 3, [1, 6]   X = 1
 * M = 3, [1, 6]   X = ...
 *
 * maximize：           dp[state]
 *                         ||
 * minimize (maximize: dp[state'])
 * 让对手最大化的效益最小化
 */
