package LC2700_3000;
import java.util.*;
public class LC2818_ApplyOperationstoMaximizeScore {
    /**
     * You are given an array nums of n positive integers and an integer k.
     *
     * Initially, you start with a score of 1. You have to maximize your score by applying the following operation at
     * most k times:
     *
     * Choose any non-empty subarray nums[l, ..., r] that you haven't chosen previously.
     * Choose an element x of nums[l, ..., r] with the highest prime score. If multiple such elements exist, choose the
     * one with the smallest index.
     * Multiply your score by x.
     * Here, nums[l, ..., r] denotes the subarray of nums starting at index l and ending at the index r, both ends being
     * inclusive.
     *
     * The prime score of an integer x is equal to the number of distinct prime factors of x. For example, the prime
     * score of 300 is 3 since 300 = 2 * 2 * 3 * 5 * 5.
     *
     * Return the maximum possible score after applying at most k operations.
     *
     * Since the answer may be large, return it modulo 10^9 + 7.
     *
     * Input: nums = [8,3,9,3,8], k = 2
     * Output: 81
     *
     * Input: nums = [19,12,14,6,10,18], k = 3
     * Output: 4788
     *
     * Constraints:
     *
     * 1 <= nums.length == n <= 10^5
     * 1 <= nums[i] <= 10^5
     * 1 <= k <= min(n * (n + 1) / 2, 10^9)
     * @param nums
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int maximumScore(List<Integer> nums, int k) {
        int mod = (int)1e9 + 7, n = nums.size();
        int[] p = new int[n];
        for (int i = 0; i < n; i++) p[i] = divide(nums.get(i));
        int[][] s = new int[n][3];
        for (int i = 0; i < n; i++) s[i] = new int[]{p[i], nums.get(i), i};
        Arrays.sort(s, (o1, o2) -> o1[1] != o2[1] ? o2[1] - o1[1] : o2[0] - o1[0]);

        long res = 1;
        for (int i = 0; i < n; i++) {
            int pv = s[i][0], val = s[i][1], idx = s[i][2];
            int l = idx - 1, r = idx + 1;
            while (l >= 0 && p[l] < pv) l--;
            while (r < n && p[r] <= pv) r++;
            int left = idx - l, right = r - idx;
            long t = Math.min(k, (long)left * right);
            res = res * qmi(val, t, mod) % mod;
            k -= t;
            if (k == 0) break;
        }
        return (int) res;
    }

    private long qmi(long a, long k, long p) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % p;
            a = a * a % p;
            k >>= 1;
        }
        return res;
    }

    private int divide(int n) {
        int cnt = 0;
        for (int i = 2; i <= n / i; i++) {
            if (n % i == 0) {
                cnt++;
                while (n % i == 0) n /= i;
            }
        }
        if (n > 1) cnt++;
        return cnt;
    }

    // S2
    final int N = 100010, mod = (int)1e9 + 7;
    int[] omega;
    public int maximumScore2(List<Integer> nums, int k) {
        omega = new int[N];
        init();

        int[] a = nums.stream().mapToInt(i -> i).toArray();
        int n = a.length;
        int[] l = new int[n], r = new int[n];
        Arrays.fill(l, -1);
        Arrays.fill(r, n);
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stk.isEmpty() && omega[a[stk.peek()]] < omega[a[i]]) r[stk.pop()] = i;
            if (!stk.isEmpty()) l[i] = stk.peek();
            stk.push(i);
        }

        Integer[] ids = new Integer[n];
        for (int i = 0; i < n; i++) ids[i] = i;
        Arrays.sort(ids, (o1, o2) -> a[o2] - a[o1]);

        long ans = 1;
        for (int i : ids) {
            long tot = (long)(i - l[i]) * (r[i] - i);
            if (tot >= k) {
                ans = ans * qmi(a[i], k, mod) % mod;
                break;
            }
            ans = ans * qmi(a[i], tot, mod) % mod;
            k -= tot;
        }
        return (int)ans;
    }

    private void init() {
        for (int i = 2; i < N; i++) {
            if (omega[i] == 0) {
                for (int j = i; j < N; j += i) {
                    omega[j]++;
                }
            }
        }
    }
}
/**
 * 1. 预处理 不同质因子的数目 omega
 * 2. 如果 k = 1, 应该选哪个子数组？
 * 3. 右边的质数分数更大的最近数的下标 right[i]
 *    左边的质数分数大于或等于b[i]的最近数的下标 left[i]
 *    子数组个数 tot = (i-left[i])*(right[i]-i)
 *    从大到小遍历 nums[i]
 *    把nums[i]^min(k,tot)乘起来
 *
 * 单调队列(线段树二分)：LC862
 *
 */