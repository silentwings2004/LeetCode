package LC3901_4200;

public class LC3971_MaximumTotalValue {
    /**
     * You are given two integer arrays value and decay, and an integer m.
     *
     * value[i] represents the initial value at index i.
     * decay[i] represents how much the value decreases after each selection of index i.
     * You may select any index multiple times. The total number of selections across all indices must not exceed m.
     *
     * If you select index i for the tth time, where t is 1-indexed, the value gained is value[i] - decay[i] * (t - 1).
     *
     * Return the maximum total value you can obtain. Since the answer may be large, return it modulo 109 + 7.
     *
     * Input: value = [6,5,4], decay = [2,1,1], m = 4
     * Output: 19
     *
     * Input: value = [7,2,2], decay = [3,2,1], m = 2
     * Output: 11
     *
     * Input: value = [4,3], decay = [5,4], m = 5
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= value.length == decay.length <= 10^5
     * 1 <= value[i], decay[i] <= 10^9
     * 1 <= m <= 10^9
     * @param value
     * @param decay
     * @param m
     * @return
     */
    // time = O(nlogU), space = O(1)
    public int maxTotalValue(int[] value, int[] decay, int m) {
        int mx = value[0];
        for (int x : value) mx = Math.max(mx, x);

        int l = 1, r = mx;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(value, decay, m, mid)) l = mid;
            else r = mid - 1;
        }
        int low = check(value, decay, m, r) ? r : r - 1;
        int n = value.length;
        long mod = (long)(1e9 + 7), res = 0;
        for (int i = 0; i < n; i++) {
            int v = value[i], d = decay[i];
            if (v > low) {
                int k = (v - (low + 1)) / d + 1;
                m -= k;
                res += (v + v - 1L * d * (k - 1)) * k;
            }
        }
        res /= 2;
        res += 1L * m * low;
        return (int)(res % mod);
    }

    private boolean check(int[] value, int[] decay, int m, int mid) {
        int n = value.length;
        for (int i = 0; i < n; i++) {
            int v = value[i], d = decay[i];
            if (v >= mid) {
                m -= (v - mid) / d + 1;
                if (m < 0) return true;
            }
        }
        return false;
    }
}
/**
 * 对于第 m 小/大问题，有如下转化套路：
 * 第 m 小等价于：求最小的 x，满足 ≤x 的数至少有 m 个。
 * 第 m 大等价于：求最大的 x，满足 ≥x 的数至少有 m 个。
 * 由于 x 越小，能选的次数越多；x 越大，能选的次数越少。有这样的性质，可以二分。
 * 核心思路：
 * 二分求一个下界，只选那些 >= 下界的价值，数这样的价值有多少个
 * 等差数列 => 选了 k 次之后，v-d*(k-1) >= low => d*(k-1) <= v-low => k <= (v-low)/d+1
 * 剩余的 m−cnt 个价值都恰好等于 low，加到价值之和中。
 */