package LC3901_4200;
import java.util.*;
public class LC4031_FindAllNumbersDisappearedinanArrayII {
    /**
     * You are given an integer array nums and two integers lower and upper.
     *
     * A missing integer is an integer in the inclusive range [lower, upper] that does not appear in nums.
     *
     * Return a 2D integer array where each element is of the form [start, end], representing a contiguous range of
     * missing integers. Return the ranges in increasing order. If there are no missing integers, return an empty array.
     *
     * Note: Consecutive missing integers should be grouped into a single range.
     *
     * Input: nums = [3,9,7], lower = 1, upper = 12
     * Output: [[1,2],[4,6],[8,8],[10,12]]
     *
     * Input: nums = [1,1], lower = 5, upper = 7
     * Output: [[5,7]]
     *
     * Input: nums = [2,3,5], lower = 2, upper = 3
     * Output: []
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * 1 <= lower <= upper <= 10^5
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public List<List<Integer>> findDisappearedNumbers(int[] nums, int lower, int upper) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int cur = lower;
        for (int x : nums) {
            if (x < cur) continue;
            if (x > upper) break;
            if (x > cur) res.add(Arrays.asList(cur, x - 1));
            cur = x + 1;
        }
        if (cur <= upper) res.add(Arrays.asList(cur, upper));
        return res;
    }
}