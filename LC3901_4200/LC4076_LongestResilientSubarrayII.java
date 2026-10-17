package LC3901_4200;

public class LC4076_LongestResilientSubarrayII {
    /**
     * You are given an integer array nums and an integer k.
     *
     * A subarray is resilient if, for every position in it, deleting the element at that position leaves the remaining
     * elements with a sum divisible by k.
     *
     * For a subarray of length 1, the remaining sum is 0, which is divisible by k.
     *
     * Return the length of the longest resilient subarray.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [2,4,6,3], k = 2
     * Output: 3
     *
     * Input: nums = [1,4,7,1], k = 3
     * Output: 4
     *
     * Input: nums = [5,5,4,8], k = 4
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * 1 <= k <= 10^5
     * @param nums
     * @param k
     * @return
     */
    // time = O(n + logk), space = O(logk)
    public int resilientSubarray(int[] nums, int k) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) nums[i] %= k;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && nums[j] == nums[i]) j++;
            int len = j - i;
            res = Math.max(res, get(nums[i], len, k));
            i = j - 1;
        }
        return res;
    }

    private int get(int x, int len, int k) {
        if (x == 0) return len;
        int g = gcd(x, k);
        long lcm = 1L * x * k / g;
        long uc = lcm / x;
        long cnt = len / uc;
        if (len % uc == 0) return (int)(uc * (cnt - 1) + 1);
        return (int)(uc * cnt + 1);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // S2
    // time = O(n), space = O(1)
    public int resilientSubarray2(int[] nums, int k) {
        int n = nums.length, res = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            int r = nums[i] % k;
            if (i > 0 && r == nums[i - 1] % k) cnt++;
            else cnt = 1;
            if (r * (cnt - 1) % k == 0) res = Math.max(res, cnt);
        }
        return res;
    }
}
/**
 * s % k = x % k
 * (x-1) * r % k = 0
 */