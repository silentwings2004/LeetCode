package LC601_900;
import java.util.*;
public class LC656_CoinPath {
    /**
     * You are given an integer array coins (1-indexed) of length n and an integer maxJump. You can jump to any index i
     * of the array coins if coins[i] != -1 and you have to pay coins[i] when you visit index i. In addition to that,
     * if you are currently at index i, you can only jump to any index i + k where i + k <= n and k is a value in the
     * range [1, maxJump].
     *
     * You are initially positioned at index 1 (coins[1] is not -1). You want to find the path that reaches index n
     * with the minimum cost.
     *
     * Return an integer array of the indices that you will visit in order so that you can reach index n with the
     * minimum cost. If there are multiple paths with the same cost, return the lexicographically smallest such path.
     * If it is not possible to reach index n, return an empty array.
     *
     * A path p1 = [Pa1, Pa2, ..., Pax] of length x is lexicographically smaller than p2 = [Pb1, Pb2, ..., Pbx] of
     * length y, if and only if at the first j where Paj and Pbj differ, Paj < Pbj; when no such j exists, then x < y.
     *
     * Input: coins = [1,2,4,-1,2], maxJump = 2
     * Output: [1,3,5]
     *
     * Input: coins = [1,2,4,-1,2], maxJump = 1
     * Output: []
     *
     * Constraints:
     *
     * 1 <= coins.length <= 1000
     * -1 <= coins[i] <= 100
     * coins[1] != -1
     * 1 <= maxJump <= 100
     * @param coins
     * @param maxJump
     * @return
     */
    // time = O(n * k), space = O(n)
    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        int n = coins.length, INF = (int) 1e9;
        int[] f = new int[n];
        Arrays.fill(f, INF);
        f[n - 1] = coins[n - 1];
        int[] path = new int[n];
        Arrays.fill(path, -1);

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= maxJump; j++) {
                if (i + j >= n) continue;
                if (coins[i] == -1 || coins[i + j] == -1) continue;
                if (f[i] > f[i + j] + coins[i]) {
                    f[i] = f[i + j] + coins[i];
                    path[i] = i + j;
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        int i = 0;
        while (i < n) {
            res.add(i + 1);
            if (i == n - 1) break;
            if (path[i] == -1) return new ArrayList<>();
            i = path[i];
        }
        return res;
    }
}
/**
 * 如果从正向考虑，考察dp[i]时，必须考察从 i-B 到 i-1 之间的元素，如果有若干并列的最小权重路径，该如何取舍呢？
 * 比如dp[x]和dp[y]都符合要求，且x<y，那么选择x吗？
 * 显然不行，因为从0行进到x的路径，可能就比从0行进到y的路径，在字典序上更大。
 * 反之，选择y也没有道理。
 * 怎么解决呢？最巧妙的方法是，从反向考虑，考察从N-1行进到0的最优路径。
 * 比如，考察dp[i]时，必须考察从 i+1 到 i+B 之间的元素，如果有若干并列的最小权重路径，该如何取舍呢？
 * 比如dp[x]和dp[y]都符合要求，且x<y，那么我们就要选择x。
 * 因为从0到i到x到N-1的路径，肯定要比从0到i到y到N-1的路径在字典序上更小。
 */