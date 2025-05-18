package LC2101_2400;
import java.util.*;
public class LC2357_MakeArrayZerobySubtractingEqualAmounts {
    /**
     * You are given a non-negative integer array nums. In one operation, you must:
     *
     * Choose a positive integer x such that x is less than or equal to the smallest non-zero element in nums.
     * Subtract x from every positive element in nums.
     * Return the minimum number of operations to make every element in nums equal to 0.
     *
     * Input: nums = [1,5,0,3,5]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int minimumOperations(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : nums) {
            if (x > 0) set.add(x);
        }
        return set.size();
    }
}
