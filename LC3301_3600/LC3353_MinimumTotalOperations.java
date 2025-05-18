package LC3301_3600;
import java.util.*;
public class LC3353_MinimumTotalOperations {
    /**
     * Given an array of integers nums, you can perform any number of operations on this array.
     *
     * In each operation, you can:
     *
     * Choose a prefix of the array.
     * Choose an integer k (which can be negative) and add k to each element in the chosen prefix.
     * A prefix of an array is a subarray that starts from the beginning of the array and extends to any point within it.
     *
     * Return the minimum number of operations required to make all elements in arr equal.
     *
     * Input: nums = [1,4,2]
     *
     * Output: 2
     *
     * Input: nums = [10,10,10]
     *
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int minOperations(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && nums[j] == nums[i]) j++;
            res++;
            i = j - 1;
        }
        return res - 1;
    }
}
