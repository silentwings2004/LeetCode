package LC3601_3900;
import java.util.*;
public class LC3779_MinimumNumberofOperationstoHaveDistinctElements {
    /**
     * You are given an integer array nums.
     *
     * In one operation, you remove the first three elements of the current array. If there are fewer than three
     * elements remaining, all remaining elements are removed.
     *
     * Repeat this operation until the array is empty or contains no duplicate values.
     *
     * Return an integer denoting the number of operations required.
     *
     * Input: nums = [3,8,3,6,5,8]
     * Output: 1
     *
     * Input: nums = [2,2]
     * Output: 1
     *
     * Input: nums = [4,3,5,1,2]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int minOperations(int[] nums) {
        int n = nums.length, res = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = n - 1; i >= 0; i--) {
            if (!set.add(nums[i])) {
                res = (i + 3) / 3;
                break;
            }
        }
        return res;
    }
}