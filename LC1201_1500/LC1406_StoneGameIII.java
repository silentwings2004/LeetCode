package LC1201_1500;
import java.util.*;
public class LC1406_StoneGameIII {
    /**
     * Alice and Bob continue their games with piles of stones. There are several stones arranged in a row, and each
     * stone has an associated value which is an integer given in the array stoneValue.
     *
     * Alice and Bob take turns, with Alice starting first. On each player's turn, that player can take 1, 2 or 3 stones
     * from the first remaining stones in the row.
     *
     * The score of each player is the sum of values of the stones taken. The score of each player is 0 initially.
     *
     * The objective of the game is to end with the highest score, and the winner is the player with the highest score
     * and there could be a tie. The game continues until all the stones have been taken.
     *
     * Assume Alice and Bob play optimally.
     *
     * Return "Alice" if Alice will win, "Bob" if Bob will win or "Tie" if they end the game with the same score.
     *
     * Input: values = [1,2,3,7]
     * Output: "Bob"
     *
     * Input: values = [1,2,3,6]
     * Output: "Tie"
     *
     * Constraints:
     *
     * 1 <= values.length <= 50000
     * -1000 <= values[i] <= 1000
     * @param stoneValue
     * @return
     */
    // time = O(n), space = O(n)
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length, INF = (int) 1e8;
        int[] f = new int[n + 1];
        Arrays.fill(f, -INF);
        f[n] = 0;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + stoneValue[i - 1];
        for (int i = n - 1; i >= 0; i--) {
            int t = 0;
            for (int k = 1; k <= 3; k++) {
                if (i + k - 1 >= n) break;
                t += stoneValue[i + k - 1];
                f[i] = Math.max(f[i], t + s[n] - s[i + k] - f[i + k]);
            }
        }
        if (f[0] > s[n] - f[0]) return "Alice";
        if (f[0] < s[n] - f[0]) return "Bob";
        return "Tie";
    }
}
/**
 * dp[i]: the max score the player can get when there are i piles already taken
 * x {x x x x x}    stone[1]
 * dp[0] = ?
 *      [x] {x x x x x}     stone[1] + sum[2:n] - dp[1]    dp[1]
 *      [x x] {x x x x}     stone[1:2] + sum[3:n] - dp[2]  dp[2]
 *      [x x x] {x x x}     stone[1:3] + sum[4:n] - dp[3]  dp[3]
 * => dp[0] = Math.max{}
 * dp[0] >=< sum[1:n] - dp[0] ???
 *
 * dp[i] = stones[i+1] + sum[i+2:n] - dp[i+1]       dp[i+1]
 *         stones[i+1:i+2] + sum[i+3:n] - dp[i+2]   dp[i+2]
 *         stones[i+1:i+3] + sum[i+4:n] - dp[i+3]   dp[i+3]
 */
