package LC2700_3000;

import java.util.HashSet;

public class LC2996_SmallestMissingIntegerGreaterThanSequentialPrefixSum {
    /**
     * You are given a 0-indexed array of integers nums.
     *
     * A prefix nums[0..i] is sequential if, for all 1 <= j <= i, nums[j] = nums[j - 1] + 1. In particular, the prefix
     * consisting only of nums[0] is sequential.
     *
     * Return the smallest integer x missing from nums such that x is greater than or equal to the sum of the longest
     * sequential prefix.
     *
     * Input: nums = [1,2,3,2,5]
     * Output: 6
     *
     * Input: nums = [3,4,5,1,12,14,13]
     * Output: 15
     *
     * Constraints:
     *
     * 1 <= nums.length <= 50
     * 1 <= nums[i] <= 50
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int missingInteger(int[] nums) {
        int n = nums.length, s = nums[0];
        HashSet<Integer> set = new HashSet<>();
        for (int x : nums) set.add(x);
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1] + 1) break;
            s += nums[i];
        }
        while (set.contains(s)) s++;
        return s;
    }
}