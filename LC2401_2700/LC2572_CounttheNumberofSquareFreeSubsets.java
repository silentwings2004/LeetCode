package LC2401_2700;
import java.util.*;
public class LC2572_CounttheNumberofSquareFreeSubsets {
    /**
     * You are given a positive integer 0-indexed array nums.
     *
     * A subset of the array nums is square-free if the product of its elements is a square-free integer.
     *
     * A square-free integer is an integer that is divisible by no square number other than 1.
     *
     * Return the number of square-free non-empty subsets of the array nums. Since the answer may be too large, return
     * it modulo 10^9 + 7.
     *
     * A non-empty subset of nums is an array that can be obtained by deleting some (possibly none but not all) elements
     * from nums. Two subsets are different if and only if the chosen indices to delete are different.
     *
     * Input: nums = [3,4,4,5]
     * Output: 3
     *
     * Input: nums = [1]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 30
     * @param nums
     * @return
     */
    // S1
    // time = O(30 * 2^10), space = O(2^10 + 30)
    int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
    long mod = (long)(1e9 + 7);
    public int squareFreeSubsets(int[] nums) {
        int[] cnt = new int[31];
        for (int x : nums) cnt[x]++;
        int n = primes.length;
        long[] f = new long[1 << n];
        f[0] = 1;

        for (int i = 1; i <= 30; i++) {
            int k = i, v = cnt[i];
            if (k == 1) continue;
            int encode = encoding(k);
            if (encode == -1) continue;
            for (int state = (1 << n) - 1; state >= 1; state--) {
                if ((encode & state) == encode) {
                    f[state] = (f[state] + f[state - encode] * v) % mod;
                }
            }
        }
        long res = 0;
        for (int state = 1; state < (1 << n); state++) {
            res = (res + f[state]) % mod;
        }

        long p = 1;
        for (int i = 0; i < cnt[1]; i++) p = p * 2 % mod;
        return (int)((res * p % mod + p - 1) % mod);
    }

    private int encoding(int x) {
        int encode = 0;
        for (int i = 0; i < primes.length; i++) {
            if (x % primes[i] == 0) {
                encode += 1 << i;
                x /= primes[i];
            }
            if (x % primes[i] == 0) return -1;
        }
        return encode;
    }

    // S2
    // time = O(2^19 * 19), space = O(1)
    public int squareFreeSubsets2(int[] nums) {
        int[] s = new int[]{1, 2, 3, 5, 6, 7, 10, 11, 13, 14, 15, 17, 19, 21, 22, 23, 26, 29, 30};
        int[] cnt = new int[31];
        for (int x : nums) cnt[x]++;
        long mod = (long)(1e9 + 7), res = 0, p = 0;
        if (cnt[1] > 0) p = qmi(2, cnt[1], mod) - 1;
        int n = s.length;
        for (int i = 0; i < (1 << n); i++) {
            List<Integer> q = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    if (cnt[s[j]] > 0) q.add(s[j]);
                    else {
                        q.clear();
                        break;
                    }
                }
            }
            if (q.size() > 0 && check(q)) {
                long t = 1;
                for (int x : q) {
                    if (x == 1) t = (t * p) % mod;
                    else t = t * cnt[x] % mod;
                }
                res = (res + t) % mod;
            }
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

    private boolean check(List<Integer> q) {
        long n = 1;
        for (int x : q) n *= x;
        for (int i = 2; i <= 30; i++) {
            if (n % i == 0) {
                int s = 0;
                while (n % i == 0) {
                    n /= i;
                    s++;
                }
                if (s > 1) return false;
            }
        }
        return true;
    }
}