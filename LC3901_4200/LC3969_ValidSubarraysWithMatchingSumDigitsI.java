package LC3901_4200;

public class LC3969_ValidSubarraysWithMatchingSumDigitsI {
    /**
     * You are given an integer array nums and an integer digit x.
     *
     * A subarray nums[l..r] is considered valid if the sum of its elements satisfies both of the following conditions:
     *
     * The first digit of the sum is equal to x.
     * The last digit of the sum is equal to x.
     * Return the number of valid subarrays.
     *
     * Input: nums = [1,100,1], x = 1
     * Output: 4
     *
     * Input: nums = [1], x = 2
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1500
     * 1 <= nums[i] <= 10^9
     * 1 <= x <= 9
     * @param nums
     * @param x
     * @return
     */
    // S1: brute-force
    // time = O(n^2 * logS), space = O(1)
    public int countValidSubarrays(int[] nums, int x) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            long s = 0;
            for (int j = i; j < n; j++) {
                s += nums[j];
                long v = s;
                int d1 = -1, d2 = -1;
                while (v > 0) {
                    int d = (int)(v % 10);
                    if (d1 == -1) {
                        d1 = d;
                        if (d1 != x) break;
                    }
                    d2 = d;
                    v /= 10;
                }
                if (d1 == x && d2 == x) res++;
            }
        }
        return res;
    }

    // S2
    // time = O(nlogS), space = O(n + D)  D = 10
    public int countValidSubarrays2(int[] nums, int x) {
        int n = nums.length;
        long[] sum = new long[n + 1];
        for (int i = 0; i < n; i++) sum[i + 1] = sum[i] + nums[i];

        int res = 0;

        // 枚举子数组和的十进制长度
        for (long low = x, high = x + 1; low <= sum[n]; low *= 10, high *= 10) { // 左闭右开
            // 计算子数组和在 [low, high-1] 中，且子数组和模 10 为 x 的子数组个数
            int[] cnt = new int[10];
            int l1 = 0;
            int l2 = 0;
            for (long s : sum) {
                // 随着 s 的增大，<= s-high 的前缀和离开窗口，<= s-low 的前缀和进入窗口
                while (sum[l1] <= s - high) {
                    cnt[(int) (sum[l1] % 10)]--;
                    l1++;
                }
                while (sum[l2] <= s - low) {
                    cnt[(int) (sum[l2] % 10)]++;
                    l2++;
                }
                res += cnt[(int) ((s - x + 10) % 10)];
            }
        }

        return res;
    }
}
/**
 * 只看首位数字 => 不是连续的 => 枚举子数组和的十进制长度
 * 在固定的十进制长度范围内，有多少个子数组和的末尾数字为 x 的子数组的个数
 * 末尾数字 = x => (s[i] - s[j]) % 10 = x  ref: LC560
 * 枚举右端点，统计有多少个满足条件的左端点
 * s[j] % 10 = (s[i] - x + 10) % 10
 * 如何保证 s[j] 在范围内
 * s 是一个递增 => 滑动窗口 去位数 S[j] % 10 的个数
 * Si - 599 <= Sj <= Si - 500
 * [5*10^k, 6*10^k)
 */