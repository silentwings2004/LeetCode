package LC3601_3900;
import java.util.*;
public class LC3897_MaximumValueofConcatenatedBinarySegments {
    /**
     * You are given two integer arrays nums1 and nums0, each of size n.
     *
     * nums1[i] represents the number of '1's in the ith segment.
     * nums0[i] represents the number of '0's in the ith segment.
     * For each index i, construct a binary segment consisting of:
     *
     * nums1[i] occurrences of '1' followed by
     * nums0[i] occurrences of '0'.
     * You may rearrange the order of these segments in any way. After rearranging, concatenate all segments to form a
     * single binary string.
     *
     * Return the maximum possible integer value of the concatenated binary string.
     *
     * Since the result can be very large, return the answer modulo 10^9 + 7.
     *
     * Input: nums1 = [1,2], nums0 = [1,0]
     * Output: 14
     *
     * Input: nums1 = [3,1], nums0 = [0,3]
     * Output: 120
     *
     * Constraints:
     *
     * 1 <= n == nums1.length == nums0.length <= 10^5
     * 0 <= nums1[i], nums0[i] <= 10^4
     * nums1[i] + nums0[i] > 0
     * The total sum of all elements in nums1 and nums0 does not exceed 2 * 10^5.
     * @param nums1
     * @param nums0
     * @return
     */
    // S1
    // time = O(k + nlogn), space = O(k + n)  k: max{nums1}
    long mod = (long)(1e9 + 7);
    public int maxValue(int[] nums1, int[] nums0) {
        int n = nums1.length, mx = nums1[0];
        for (int i = 0; i < n; i++) mx = Math.max(mx, nums1[i]);
        long[] w = new long[mx + 1];
        for (int i = 1; i <= mx; i++) w[i] = (w[i - 1] * 2 % mod + 1) % mod;

        Integer[] p = new Integer[n];
        for (int i = 0; i < n; i++) p[i] = i;
        Arrays.sort(p, (o1, o2) -> {
            int c11 = nums1[o1], c12 = nums1[o2];
            int c01 = nums0[o1], c02 = nums0[o2];
            if (c01 == 0 && c02 == 0) return c12 - c11;
            if (c01 == 0) return -1;
            if (c02 == 0) return 1;
            return c11 != c12 ? c12 - c11 : c01 - c02;
        });

        long res = 0;
        for (int i = 0; i < n; i++) {
            int idx = p[i];
            int c1 = nums1[idx], c0 = nums0[idx];
            long x = w[c1] * qmi(2, c0) % mod;
            int m = c0 + c1;
            res = (res * qmi(2, m) % mod + x) % mod;
        }
        return (int)res;
    }

    private long qmi(long a, long k) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            k >>= 1;
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public int maxValue2(int[] nums1, int[] nums0) {
        final int N = 100010;
        long[] pow2 = new long[N];
        pow2[0] = 1;
        for (int i = 1; i < N; i++) pow2[i] = pow2[i - 1] * 2 % mod;
        int n = nums1.length;
        Integer[] p = new Integer[n];
        for (int i = 0; i < n; i++) p[i] = i;
        Arrays.sort(p, (o1, o2) -> {
            if (nums0[o1] == 0 && nums0[o2] != 0) return -1;
            if (nums0[o1] != 0 && nums0[o2] == 0) return 1;
            if (nums1[o1] != nums1[o2]) return nums1[o2] - nums1[o1];
            return nums0[o1] - nums0[o2];
        });

        long res = 0;
        for (int x : p) res = ((res + 1) * pow2[nums1[x]] -1) % mod * pow2[nums0[x]] % mod;
        return (int)res;
    }
}
/**
 * 在 ans 右边拼接 k 个 0，等价于把 ans 左移 k 位 => res * 2^k
 * 在 ans 右边拼接 k 个 1，先左移 k 位（x 2^k), 再加上 2^k - 1，即 res * 2^k + 2^k - 1 = (res + 1) * 2^k - 1
 */