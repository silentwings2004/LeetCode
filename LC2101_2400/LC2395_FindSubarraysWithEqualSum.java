package LC2101_2400;
import java.util.*;
public class LC2395_FindSubarraysWithEqualSum {
    /**
     * Given a 0-indexed integer array nums, determine whether there exist two subarrays of length 2 with equal sum.
     * Note that the two subarrays must begin at different indices.
     *
     * Return true if these subarrays exist, and false otherwise.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [4,2,4]
     * Output: true
     *
     * Constraints:
     *
     * 2 <= nums.length <= 1000
     * -10^9 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // S1: brute-force
    // time = O(n^2), space = O(1)
    public boolean findSubarrays(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int sum = nums[i] + nums[i + 1];
            for (int j = i + 1; j < n - 1; j++) {
                if (nums[j] + nums[j + 1] == sum) return true;
            }
        }
        return false;
    }

    // S2: HashSet
    // time = O(n), space = O(n)
    public boolean findSubarrays2(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int sum = nums[i] + nums[i + 1];
            if (!set.add(sum)) return true;
        }
        return false;
    }
}
