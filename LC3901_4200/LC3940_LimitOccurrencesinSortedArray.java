package LC3901_4200;
import java.util.*;
public class LC3940_LimitOccurrencesinSortedArray {
    /**
     * You are given a sorted integer array nums and an integer k.
     *
     * Return an array such that each distinct element appears at most k times, while preserving the relative order of
     * the elements in nums.
     *
     * Input: nums = [1,1,1,2,2,3], k = 2
     * Output: [1,1,2,2,3]
     *
     * Input: nums = [1,2,3], k = 1
     * Output: [1,2,3]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * nums is sorted in non-decreasing order.
     * 1 <= k <= nums.length
     *
     * Follow-up:
     *
     * Can you solve this in-place using O(1) extra space?
     * Note that the space used for returning or resizing the result does not count toward the space complexity
     * mentioned above, as some languages do not support in-place resizing.
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int[] limitOccurrences(int[] nums, int k) {
        int n = nums.length, j = 0;
        for (int i = 0, cnt = 0; i < n; i++) {
            if (i == 0 || nums[i] == nums[i - 1]) cnt++;
            else cnt = 1;
            if (cnt <= k) nums[j++] = nums[i];
        }
        return Arrays.copyOf(nums, j);
    }
}