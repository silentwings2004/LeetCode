package LC3301_3600;
import java.util.*;
public class LC3395_SubsequenceswithaUniqueMiddleModeI {
    /**
     * Given an integer array nums, find the number of subsequences of size 5 of nums with a unique middle mode.
     *
     * Since the answer may be very large, return it modulo 109 + 7.
     *
     * A mode of a sequence of numbers is defined as the element that appears the maximum number of times in the sequence.
     *
     * A sequence of numbers contains a unique mode if it has only one mode.
     *
     * A sequence of numbers seq of size 5 contains a unique middle mode if the middle element (seq[2]) is a unique mode.
     *
     * A subsequence is a non-empty array that can be derived from another array by deleting some or no elements without
     * changing the order of the remaining elements.
     *
     * Input: nums = [1,1,1,1,1,1]
     * Output: 6
     *
     * Input: nums = [1,2,2,3,3,4]
     * Output: 4
     *
     * Input: nums = [0,1,2,3,4,5,6,7,8]
     * Output: 0
     *
     * Constraints:
     *
     * 5 <= nums.length <= 1000
     * -10^9 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n^2), space = O(n)
    final long mod = (long)(1e9 + 7);
    long[][] c;
    int n;
    public int subsequencesWithMiddleMode(int[] nums) {
        n = nums.length;
        c = new long[n + 1][6];
        init();

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1);

        long res = 0;
        HashMap<Integer, Integer> cnt = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
            int rx = map.get(x) - cnt.get(x);

            for (int l = 0; l <= 2; l++) {
                for (int r = 0; r <= 2; r++) {
                    if (l + r >= 2) {
                        res = (res + C(cnt.get(x) - 1, l) * C(rx, r) % mod * C(i - cnt.get(x), 2 - l) % mod * C(n - i - rx, 2 - r)) % mod;
                    }
                }
            }

            if (cnt.get(x) >= 2) {
                long t = (i - cnt.get(x)) * C(n - i - rx, 2) % mod;
                for (int y : map.keySet()) {
                    if (y == x) continue;
                    int ry = map.get(y) - cnt.getOrDefault(y, 0);
                    t = (t - cnt.getOrDefault(y, 0) * ry % mod * (n - i - rx - 1) % mod + mod) % mod;
                    t = (t - (i - cnt.get(x)) * C(ry, 2) % mod + mod) % mod;
                    t = (t + cnt.getOrDefault(y, 0) * C(ry, 2) * 2) % mod;
                }
                res = (res + t * (cnt.get(x) - 1)) % mod;
            }

            if (rx > 0) {
                long t = C(i - cnt.get(x), 2) * (n - i - rx) % mod;
                for (int y : map.keySet()) {
                    if (y == x) continue;
                    int ry = map.get(y) - cnt.getOrDefault(y, 0);
                    t = (t - cnt.getOrDefault(y, 0) * ry % mod * (i - cnt.get(x) - 1) % mod + mod) % mod;
                    t = (t - C(cnt.getOrDefault(y, 0), 2) * (n - i - rx) % mod + mod) % mod;
                    t = (t + C(cnt.getOrDefault(y, 0), 2) * ry * 2) % mod;
                }
                res = (res + t * rx) % mod;
            }
        }
        return (int)res;
    }

    private long C(int a, int b) {
        if (a < b) return 0;
        return c[a][b];
    }

    private void init() {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i && j < 6; j++) {
                if (j == 0) c[i][j] = 1;
                else c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % mod;
            }
        }
    }

    // S2
    public int subsequencesWithMiddleMode2(int[] nums) {
        long mod = (long)(1e9 + 7);
        int n = nums.length;
        long res = 1L * n * (n - 1) * (n - 2) * (n - 3) * (n - 4) / 120;
        HashMap<Integer, Integer> pre = new HashMap<>();
        HashMap<Integer, Integer> suf = new HashMap<>();
        for (int x : nums) suf.put(x, suf.getOrDefault(x, 0) + 1);

        for (int i = 0; i + 2 < n; i++) {
            int x = nums[i];
            suf.put(x, suf.get(x) - 1);
            if (i > 1) {
                int px = pre.getOrDefault(x, 0);
                int sx = suf.get(x);
                int j = n - 1 - i;
                // cnt[x] = 1
                res -= 1L * comb(i - px) * comb(j - sx);

                // cnt[x] = 2 and cnt[y] >= 2 (y != x)
                for (int y : suf.keySet()) {
                    if (y == x) continue;
                    int py = pre.getOrDefault(y, 0);
                    int sy = suf.get(y);
                    // case 1: yy x xz
                    res -= 1L * comb(py) * sx * (j - sx);
                    // case 2: zx x yy
                    res -= 1L * comb(sy) * px * (i - px);
                    // case 3: yx x yz
                    res -= 1L * py * px * sy * (j - sx - sy);
                    // case 4: zy x xy
                    res -= 1L * (i - px - py) * py * sx * sy;
                }
            }
            pre.put(x, pre.getOrDefault(x, 0) + 1);
        }
        return (int)(res % mod);
    }

    private int comb(int x) {
        return x * (x - 1) / 2;
    }
}
/**
 * 设子序列正中间的数是 x
 * 1. x 在子序列中只出现 1 次，肯定不合法
 * 2. x 在子序列中只出现 2 次
 *    子序列中的不等于 x 的另一个数记作 y
 *    y 出现次数 = 1, 合法
 *    y 出现次数 = 2, 3, 不合法
 * 3. x 在子序列中只出现 3, 4, 5 次，肯定合法
 *
 * 正难则反，算不合法
 * 1. x 在子序列中只出现 1 次，肯定不合法  ? ? x ? ?
 * 2. x 在子序列中只出现 2 次，且另一个数 y 的出现次数 = 2 or 3
 * x ? x ? ?
 * x ? x y y
 * x y x y ?
 *
 * follow-up:
 * 1. 给一堆询问，修改元素，然后计算答案
 * 2. 改数据范围
 * 3. 把 5 改成别的数
 */