package LC1501_1800;
import java.util.*;
public class LC1508_RangeSumofSortedSubarraySums {
    /**
     * You are given the array nums consisting of n positive integers. You computed the sum of all non-empty continuous
     * subarrays from the array and then sorted them in non-decreasing order, creating a new array of n * (n + 1) / 2
     * numbers.
     *
     * Return the sum of the numbers from index left to index right (indexed from 1), inclusive, in the new array.
     * Since the answer can be a huge number return it modulo 10^9 + 7.
     *
     * Input: nums = [1,2,3,4], n = 4, left = 1, right = 5
     * Output: 13
     *
     * Constraints:
     *
     * n == nums.length
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 100
     * 1 <= left <= right <= n * (n + 1) / 2
     * @param nums
     * @param n
     * @param left
     * @param right
     * @return
     */
    // time = O(n^2), space = O(n)
    public int rangeSum(int[] nums, int n, int left, int right) {
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];
        int m = n * (n + 1) / 2;
        long[] w = new long[m];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int len = 1; i + len - 1 < n; len++) {
                int j = i + len - 1;
                w[idx++] = s[j + 1] - s[i];
            }
        }
        Arrays.sort(w);
        long res = 0, mod = (long)(1e9 + 7);
        for (int i = left - 1; i < right; i++) res = (res + w[i]) % mod;
        return (int)res;
    }
}
/**
 * 一共有 n*(n+1)/2个subarray
 * 10^6 => sort下也能AC
 * 求第k大的subarray sum => bs
 * O(n^2*log(n^2)) ->
 * 怎么判断猜大猜小呢？
 * kth subarray sum = S
 * countSmallerOrEqual(S) < k => 第k个subArray sum肯定不是S => left = S + 1
 * countSmallerOrEqual(S) >= k => right = S
 * TotalSubSum(right) - TotalSubSum(left-1)
 *     x x x ... x x x
 *     l l l     r r r
 */