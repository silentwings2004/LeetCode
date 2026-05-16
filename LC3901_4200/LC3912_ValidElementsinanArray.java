package LC3901_4200;
import java.util.*;
public class LC3912_ValidElementsinanArray {
    /**
     * You are given an integer array nums.
     *
     * An element nums[i] is considered valid if it satisfies at least one of the following conditions:
     *
     * It is strictly greater than every element to its left.
     * It is strictly greater than every element to its right.
     * The first and last elements are always valid.
     *
     * Return an array of all valid elements in the same order as they appear in nums.
     *
     * Input: nums = [1,2,4,2,3,2]
     * Output: [1,2,4,3,2]
     *
     * Input: nums = [5,5,5,5]
     * Output: [5,5]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public List<Integer> findValidElements(int[] nums) {
        int n = nums.length;
        int[] r = new int[n];
        r[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) r[i] = Math.max(r[i + 1], nums[i]);
        List<Integer> res = new ArrayList<>();
        for (int i = 0, mx = nums[0]; i < n; i++) {
            if (i == 0 || i == n - 1) res.add(nums[i]);
            else if (nums[i] > mx || nums[i] > r[i + 1]) res.add(nums[i]);
            mx = Math.max(mx, nums[i]);
        }
        return res;
    }
}