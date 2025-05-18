package LC001_300;
import java.util.*;
public class LC80_RemoveDuplicatesfromSortedArrayII {
    /**
     * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return
     * the new length.
     *
     * Do not allocate extra space for another array; you must do this by modifying the input array in-place with O(1)
     * extra memory.
     *
     * Input: nums = [1,1,1,2,2,3]
     * Output: 5, nums = [1,1,2,2,3]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 3 * 10^4
     * -10^4 <= nums[i] <= 104
     * nums is sorted in ascending order.
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int removeDuplicates(int[] nums) {
        int k = 0;
        for (int x : nums) {
            if (k < 2 || nums[k - 1] != x || nums[k - 2] != x) nums[k++] = x;
        }
        return k;
    }

    // S2: Two Pointers
    // time = O(n), space = O(1)
    public int removeDuplicates2(int[] nums) {
        int n = nums.length, k = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && nums[j] == nums[i]) j++;
            int cnt = j - i;
            for (int t = 0; t < Math.min(cnt, 2); t++) nums[k++] = nums[i];
            i = j - 1;
        }
        return k;
    }
}