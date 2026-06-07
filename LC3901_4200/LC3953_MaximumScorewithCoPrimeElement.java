package LC3901_4200;
import java.util.*;
public class LC3953_MaximumScorewithCoPrimeElement {
    /**
     * You are given an integer array nums of length n and an integer maxVal.
     *
     * You may change any element in nums to any positive integer less than or equal to maxVal. Each such change costs 1.
     *
     * Two integers are co-prime if their greatest common divisor (GCD) is 1.
     *
     * After all modifications, you must choose an index i such that, nums[i] is co-prime with every other element
     * nums[j].
     *
     * Let:
     *
     * selectedValue be the final value of nums[i] after modifications.
     * modificationCost be the total number of elements changed.
     * The score is defined as score = selectedValue - modificationCost
     *
     * Return the maximum possible score.
     *
     * The term gcd(a, b) denotes the greatest common divisor of a and b.
     *
     * Input: nums = [3,4,6], maxVal = 5
     * Output: 4
     *
     * Input: nums = [1,2,3], maxVal = 4
     * Output: 3
     *
     * Input: nums = [2,2], maxVal = 1
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * 1 <= maxVal <= 10^5
     * @param nums
     * @param maxVal
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int maxScore(int[] nums, int maxVal) {
        int n = nums.length, mx = maxVal;
        for (int x : nums) mx = Math.max(mx, x);

        int[] freq = new int[mx + 1];
        for (int x : nums) freq[x]++;

        int[] cnt = new int[mx + 1];
        for (int i = 1; i <= mx; i++) {
            for (int j = i; j <= mx; j += i) {
                cnt[i] += freq[j];
            }
        }

        // smallest prime factor
        int[] spf = new int[mx + 1];
        for (int i = 2; i <= mx; i++) {
            if (spf[i] == 0) {
                spf[i] = i;
                if (1L * i * i <= mx) {
                    for (int j = i * i; j <= mx; j += i) {
                        if (spf[j] == 0) spf[j] = i;
                    }
                }
            }
        }

        int res = 0;
        // we need bad for all v <= maxVal and for all v with freq > 0
        for (int v = 1; v <= mx; v++) {
            if (v > maxVal && freq[v] == 0) continue;
            int bad = get(v, cnt, spf, mx); // 在原数组 nums 里，有多少个数和v不互质，即gcd(v, nums) > 1 的下标个数。
            int score = 0;
            if (freq[v] > 0) score = v - bad + (v > 1 ? 1 : 0);
            else { // freq = 0 and v <= maxVal
                if (bad > 0) score = v - bad;
                else score = v - 1; // delta = -1
            }
            res = Math.max(res, score);
        }
        return res;
    }

    private int get(int v, int[] cnt, int[] spf, int mx) {
        if (v == 1) return 0; // 一定都和 1 互质 => 不互质的个数 = 0
        int x = v;
        int[] primes = new int[7]; // k 是不同素因子的个数。对 ≤1e5 的数 k ≤ 6，数组开 7 足够。
        int k = 0;
        while (x > 1) {
            int p = spf[x];
            primes[k++] = p;
            while (x % p == 0) x /= p;
        }
        int bad = 0, tot = 1 << k;
        for (int mask = 1; mask < tot; mask++) {
            long prod = 1;
            int bits = 0;
            for (int i = 0; i < k; i++) {
                if ((mask >> i & 1) == 1) {
                    prod *= primes[i];
                    bits++;
                    if (prod > mx) break;
                }
            }
            if (prod > mx) continue;
            int c = cnt[(int)prod];
            if ((bits & 1) == 1) bad += c;
            else bad -= c;
        }
        return bad;
    }

    // S2: Mobius function
    // time = O(n + UlogU), space = O(U)
    class Solution {
        static final int MX = 100010;
        static final int[] mu = new int[MX];
        static final List<Integer>[] divisors = new List[MX];
        static boolean initialized = false;

        public Solution() {
            if (initialized) return;
            initialized = true;

            // 预处理莫比乌斯函数
            // 当 n > 1 时，sum_{d|n} mu[d] = 0
            // 所以 mu[n] = -sum_{d|n ∧ d<n} mu[d]
            mu[1] = 1;
            for (int i = 1; i < MX; i++) {
                for (int j = i * 2; j < MX; j += i) {
                    mu[j] -= mu[i]; // i 是 j 的真因子
                }
            }
            // 预处理不含平方因子的因子列表
            for (int i = 0; i < MX; i++) divisors[i] = new ArrayList<>();
            for (int i = 2; i < MX; i++) { // 本题不需要因子 1
                if (mu[i] == 0) continue;
                for (int j = i; j < MX; j += i) {
                    divisors[j].add(i); // i 是 j 的因子，且 mu[i] != 0
                }
            }
        }

        public int maxScore(int[] nums, int maxVal) {
            int mx = nums[0];
            for (int x : nums) mx = Math.max(mx, x);

            int[] cnt = new int[mx + 1];
            for (int x : nums) cnt[x]++;

            int[] cntMulti = new int[mx + 1];
            for (int i = 2; i <= mx; i++) {
                for (int j = i; j <= mx; j += i) {
                    cntMulti[i] += cnt[j]; // 统计 nums 中有多少个数是 i 的倍数
                }
            }

            int res = cnt[1] > 0 ? 1 : 0; // 单独计算 selectedValue = 1 时的得分

            // 从大到小枚举 selectedValue
            // 优化：如果 selectedValue <= ans，那么 ans 不会变大，跳出循环
            for (int v = Math.max(mx, maxVal); v > res; v--) {
                if (v > maxVal && cnt[v] == 0) continue; // 无法改成 selectedValue
                // 与 selectedValue 不互质的数，其中一个数改成 selectedValue，其余数都改成 1
                int cost = 0;
                for (int d : divisors[v]) {
                    if (d > mx) break;
                    cost -= mu[d] * cntMulti[d];
                }
                if (v <= mx && cnt[v] > 0) cost--; // 如果某个 nums[i] 恰好等于 selectedValue，可以少改一次
                else if (cost == 0) cost = 1; // 至少要有一个数改成 selectedValue
                res = Math.max(res, v - cost);
            }
            return res;
        }
    }
}
/**
 * 莫比乌斯函数
 * 计算 nums 中的和 v 不互质的数的个数
 * v = 6
 * nums = [2,3,3,12,5]
 * 2 的倍数 2 12
 * 3 的倍数 3 3 12
 * 既是 2 的倍数又是 3 的倍数 = 6 的倍数 12
 *  和 6 不互质的数的个数
 * = 2 的倍数的个数 + 3 的倍数的个数 - 6 的倍数的个数
 * |A + B| = |A| + |B| - |A x B|
 * 12 = 2^2 * 3^1
 *  和 12 不互质的数的个数
 * = 2 的倍数的个数 + 3 的倍数的个数 - 6 的倍数的个数
 * 2 = 2
 * 3 = 3
 * 6 = 2 * 3
 * 数论中的容斥，只需要考虑没有平方因子的因子
 * 2，3，6
 *
 * 定义莫比乌斯函数 (容斥系数)
 * mu(d) = 0            d 有平方因子
 *        (-1)^k        k 是d 中不同质因子的个数 k = p1*p2*...*pk
 *        1             d = 1
 */