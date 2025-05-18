package LC2101_2400;
import java.util.*;
public class LC2218_MaximumValueofKCoinsFromPiles {
    /**
     * There are n piles of coins on a table. Each pile consists of a positive number of coins of assorted denominations.
     *
     * In one move, you can choose any coin on top of any pile, remove it, and add it to your wallet.
     *
     * Given a list piles, where piles[i] is a list of integers denoting the composition of the ith pile from top to
     * bottom, and a positive integer k, return the maximum total value of coins you can have in your wallet if you
     * choose exactly k coins optimally.
     *
     * Input: piles = [[1,100,3],[7,8,9]], k = 2
     * Output: 101
     *
     * Input: piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7
     * Output: 706
     *
     * Constraints:
     *
     * n == piles.length
     * 1 <= n <= 1000
     * 1 <= piles[i][j] <= 10^5
     * 1 <= k <= sum(piles[i].length) <= 2000
     * @param piles
     * @param k
     * @return
     */
    // time = O(s * k), space = O(n * k)
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int n = piles.size();
        int[][] s = new int[n][2010];
        for (int i = 0; i < n; i++) {
            int m = piles.get(i).size();
            for (int j = 1; j <= m; j++) {
                s[i][j] = s[i][j - 1] + piles.get(i).get(j - 1);
            }
        }

        int[][] f = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            int m = piles.get(i - 1).size();
            for (int j = 0; j <= k; j++) {
                for (int u = j; u >= 0; u--) {
                    if (j - u > m) break;
                    f[i][j] = Math.max(f[i][j], f[i - 1][u] + s[i - 1][j - u]);
                }
            }
        }
        return f[n][k];
    }
}
/**
 * 无后效性
 * dp[i][j]: the maximum total value of coins by the first i piles and choosing j coins
 * dp[i][j]: suppose we choose t coins in the i-th pile
 * dp[i][j] = max{dp[i-1][j-t] + pilesPresum[i][t]} for t = 0,1,2,...len(piles[i])
 */