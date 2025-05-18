package LC2101_2400;

public class LC2302_CountSubarraysWithScoreLessThanK {
    /**
     * The score of an array is defined as the product of its sum and its length.
     *
     * For example, the score of [1, 2, 3, 4, 5] is (1 + 2 + 3 + 4 + 5) * 5 = 75.
     * Given a positive integer array nums and an integer k, return the number of non-empty subarrays of nums whose
     * score is strictly less than k.
     *
     * A subarray is a contiguous sequence of elements within an array.
     *
     * Input: nums = [2,1,4,3,5], k = 10
     * Output: 6
     *
     * Input: nums = [1,1,1], k = 5
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * 1 <= k <= 10^15
     * @param nums
     * @param k
     * @return
     */
    // S1: binary search
    // time = O(nlogn), space = O(n)
    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];
        long res = 0;
        for (int i = 0; i < n; i++) {
            int r = get(s, i, n - 1, k);
            res += r - i + 1;
        }
        return res;
    }

    private int get(long[] s, int l, int r, long k) {
        int i = l;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if ((s[mid + 1] - s[i]) * (mid - i + 1) < k) l = mid;
            else r = mid - 1;
        }
        return (s[r + 1] - s[i]) * (r - i + 1) < k ? r : r - 1;
    }

    // S2: two pointers (optimal)
    // time = O(n), space = O(1)
    public long countSubarrays2(int[] nums, long k) {
        int n = nums.length;
        long t = 0, res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            t += nums[i];
            while (t * (i - j + 1) >= k) t -= nums[j++];
            res += i - j + 1;
        }
        return res;
    }
}
/**
 * 对于每个元素而言，有多少个subarray跟它关联
 * 只要遍历这些元素，将这些元素关联的subarray关联起来即可
 * x x x [j x x i] x x x j
 *              3
 */
