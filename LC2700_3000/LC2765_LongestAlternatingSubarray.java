package LC2700_3000;

public class LC2765_LongestAlternatingSubarray {
    /**
     * You are given a 0-indexed integer array nums. A subarray s of length m is called alternating if:
     *
     * m is greater than 1.
     * s1 = s0 + 1.
     * The 0-indexed subarray s looks like [s0, s1, s0, s1,...,s(m-1) % 2]. In other words, s1 - s0 = 1, s2 - s1 = -1,
     * s3 - s2 = 1, s4 - s3 = -1, and so on up to s[m - 1] - s[m - 2] = (-1)m.
     * Return the maximum length of all alternating subarrays present in nums or -1 if no such subarray exists.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [2,3,4,3,4]
     * Output: 4
     *
     * Input: nums = [4,5,6]
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= nums.length <= 100
     * 1 <= nums[i] <= 10^4
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int alternatingSubarray(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            int r = n;
            for (int j = i + 1, t = 1; j < n; j++, t *= -1) {
                if (nums[j] - nums[j - 1] == t) continue;
                r = j;
                break;
            }
            res = Math.max(res, r - i);
            if (r == n) break;
            if (nums[r] - nums[r - 1] == 1) i = r - 2;
            else i = r - 1;
        }
        return res < 2 ? -1 : res;
    }
}