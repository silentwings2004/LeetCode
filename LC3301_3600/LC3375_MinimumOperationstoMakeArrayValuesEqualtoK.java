package LC3301_3600;
import java.util.*;
public class LC3375_MinimumOperationstoMakeArrayValuesEqualtoK {
    /**
     * You are given an integer array nums and an integer k.
     *
     * An integer h is called valid if all values in the array that are strictly greater than h are identical.
     *
     * For example, if nums = [10, 8, 10, 8], a valid integer is h = 9 because all nums[i] > 9 are equal to 10, but 5
     * is not a valid integer.
     *
     * You are allowed to perform the following operation on nums:
     *
     * Select an integer h that is valid for the current values in nums.
     * For each index i where nums[i] > h, set nums[i] to h.
     * Return the minimum number of operations required to make every element in nums equal to k. If it is impossible
     * to make all elements equal to k, return -1.
     *
     * Input: nums = [5,2,5,4,5], k = 2
     * Output: 2
     *
     * Input: nums = [2,1,2], k = 2
     * Output: -1
     *
     * Input: nums = [9,7,5,3], k = 1
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * 1 <= k <= 100
     * @param nums
     * @param k
     * @return
     */
    // time = O(nlog), space = O(n)
    public int minOperations(int[] nums, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int x : nums) set.add(x);
        if (set.first() < k) return -1;
        return set.first() == k ? set.size() - 1 : set.size();
    }
}