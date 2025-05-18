package LC2700_3000;
import java.util.*;
public class LC2915_LengthoftheLongestSubsequenceThatSumstoTarget {
    /**
     * You are given a 0-indexed array of integers nums, and an integer target.
     *
     * Return the length of the longest subsequence of nums that sums up to target. If no such subsequence exists,
     * return -1.
     *
     * A subsequence is an array that can be derived from another array by deleting some or no elements without changing
     * the order of the remaining elements.
     *
     * Input: nums = [1,2,3,4,5], target = 9
     * Output: 3
     *
     * Input: nums = [4,1,3,2,1,5], target = 7
     * Output: 4
     *
     * Input: nums = [1,1,5,4,5], target = 3
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 1000
     * 1 <= target <= 1000
     * @param nums
     * @param target
     * @return
     */
    // time = O(n * m), space = O(m)
    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int n= nums.size(), m = target, INF = (int)1e9;
        int[] f = new int[m + 1];
        Arrays.fill(f, -INF);
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= nums.get(i - 1); j--) {
                f[j] = Math.max(f[j], f[j - nums.get(i - 1)] + 1);
            }
        }
        return f[m] < 0 ? -1 : f[m];
    }
}