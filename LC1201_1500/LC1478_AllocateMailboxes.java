package LC1201_1500;
import java.util.*;
public class LC1478_AllocateMailboxes {
    /**
     * Given the array houses and an integer k. where houses[i] is the location of the ith house along a street,
     * your task is to allocate k mailboxes in the street.
     *
     * Return the minimum total distance between each house and its nearest mailbox.
     *
     * The answer is guaranteed to fit in a 32-bit signed integer.
     *
     * Input: houses = [1,4,8,10,20], k = 3
     * Output: 5
     *
     * Constraints:
     *
     * n == houses.length
     * 1 <= n <= 100
     * 1 <= houses[i] <= 10^4
     * 1 <= k <= n
     * Array houses contain unique integers.
     * @param houses
     * @param k
     * @return
     */
    // S1
    // time = O(n^3), space = O(n^2)
    public int minDistance(int[] houses, int k) {
        // corner case
        if (houses == null || houses.length == 0 || k < 0) return 0;

        int n = houses.length;
        Arrays.sort(houses);
        int[][] dp = new int[n + 1][k + 1];

        int[][] range = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                range[i][j] = 0;
                for (int t = i; t <= j; t++) {
                    range[i][j] += Math.abs(houses[t - 1] - houses[(i - 1 + j - 1) / 2]); // house_idx = dp_idx - 1
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            dp[i][1] = range[1][i];
        }

        for (int i = 1; i <= n; i++) {
            for (int t = 2; t <= k; t++) {
                dp[i][t] = Integer.MAX_VALUE / 2;
                for (int j = 1; j + 1 <= i; j++) {
                    dp[i][t] = Math.min(dp[i][t], dp[j][t - 1] + range[j + 1][i]);
                }
            }
        }
        return dp[n][k];
    }

    // S1.2
    // time = O(n^3), space = O(n^2)
    final int INF = (int) 1e8;
    public int minDistance2(int[] houses, int k) {
        Arrays.sort(houses);
        int n = houses.length;
        int[][] f = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i], INF);
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                for (int s = i; s <= j; s++) {
                    cost[i][j] += Math.abs(houses[s] - houses[i + (j - i + 1) /2]);
                }
            }
        }

        f[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                for (int s = 1; s <= i; s++) {
                    f[i][j] = Math.min(f[i][j], f[s - 1][j - 1] + cost[s - 1][i - 1]);
                }
            }
        }
        return f[n][k];
    }
}
/**
 * 二分猜值行不行？不行，total distance,没法进一步判断这个答案是猜大还是猜小了
 * 类似题：heater
 * 对于每个房子而言，最大距离的最小值，那就可以猜，最远在哪里可以猜到
 * dp
 * 第一类区间型dp[i][k]: minimum total distance between each house and its nearest mailbox for
 * house[0:i] covered by k mailboxes
 * [xxxxxxxx] [xxx i]
 *  k - 1  ^  ^
 *         j j+1
 * dp[i][k] = dp[j][k-1]+range[j+1][i] for j = ... -> 1维上找一个最小的曼哈顿距离
 * minimize sum |xi-p| => p = the median {xi}
 * 并不是所有的j都是符合条件的，要exclude一些
 * 对于不合适的j，整个dp[j][k-1] + range[j+1][i]都会偏大。
 * 只有遍历到最合适的j，就能得到dp[j][k-1] + range[j+1][i]的最小值，即最小化dp[i][k]。
 * 只有个别的j才是合法的
 *
 * 如果只有1个邮筒的话，那就放到正中间 => 绝对值不等式
 * 先划定势力范围，每个势力范围里再考虑放置1个邮箱 => 放到中位数即可
 * 高效从n个房子里，划分到k个势力范围里 => dp
 * f(i,j) 1~i个房子，最多划分成j部分的最小距离和
 * 最后一部分是k~i => cost(k,i) 排序+中位数来求,前面f(k-1,j-1)
 * cost先预处理下，枚举下i,j2个端点，用O(n^3)来处理
 */
