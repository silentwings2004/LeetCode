package LC901_1200;

public class LC1191_KConcatenationMaximumSum {
    /**
     * Given an integer array arr and an integer k, modify the array by repeating it k times.
     *
     * For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].
     *
     * Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can be 0 and its
     * sum in that case is 0.
     *
     * As the answer can be very large, return the answer modulo 10^9 + 7.
     *
     * Input: arr = [1,2], k = 3
     * Output: 9
     *
     * Input: arr = [1,-2,1], k = 5
     * Output: 2
     *
     * Input: arr = [-1,-2], k = 7
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= arr.length <= 10^5
     * 1 <= k <= 10^5
     * -10^4 <= arr[i] <= 10^4
     * @param arr
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int kConcatenationMaxSum(int[] arr, int k) {
        int n = arr.length, mod = (int)1e9 + 7;
        long l = 0, r = 0, sum = 0, s = 0, maxv = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            l = Math.max(l, sum);
            s = Math.max(s, 0) + arr[i];
            maxv = Math.max(maxv, s);
            if (i == n - 1) r = s;
        }
        if (k == 1) return (int)(maxv % mod);
        if (sum < 0) return (int)(Math.max(l + r, maxv) % mod);
        return (int)(Math.max(sum * (k - 2) + l + r, maxv) % mod);
    }
}
/**
 * k = 1 => 最大子段和
 * k = 2 => 最大后缀 + 最大前缀
 * k > 2 =>
 * sum >= 0 => 中间每段都要包含 => sum * (n - 2) + 最大后缀 + 最大前缀
 * sum < 0 => 最大和要么在段内，要么跨过一段
 */