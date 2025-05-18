package LC2700_3000;
import java.util.*;
public class LC2855_MinimumRightShiftstoSorttheArray {
    /**
     * You are given a 0-indexed array nums of length n containing distinct positive integers. Return the minimum number
     * of right shifts required to sort nums and -1 if this is not possible.
     *
     * A right shift is defined as shifting the element at index i to index (i + 1) % n, for all indices.
     *
     * Input: nums = [3,4,5,1,2]
     * Output: 2
     *
     * Input: nums = [1,3,5]
     * Output: 0
     *
     * Input: nums = [2,1,4]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * nums contains distinct integers.
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int minimumRightShifts(List<Integer> nums) {
        int n = nums.size(), res = 0;
        for (int i = 1; i < n; i++) {
            if (nums.get(i) > nums.get(i - 1)) continue;
            else {
                int j = i + 1;
                while (j < n && nums.get(j) > nums.get(j - 1)) j++;
                if (j < n) return -1;
                if (nums.get(n - 1) > nums.get(0)) return -1;
                res += n - i;
                break;
            }
        }
        return res;
    }
}
/**
 * 最多两端递增数组
 */