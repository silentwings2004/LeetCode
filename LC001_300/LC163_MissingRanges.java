package LC001_300;
import java.util.*;
public class LC163_MissingRanges {
    /**
     * You are given an inclusive range [lower, upper] and a sorted unique integer array nums, where all elements are
     * in the inclusive range.
     *
     * A number x is considered missing if x is in the range [lower, upper] and x is not in nums.
     *
     * Return the smallest sorted list of ranges that cover every missing number exactly. That is, no element of nums
     * is in any of the ranges, and each missing number is in one of the ranges.
     *
     * Each range [a,b] in the list should be output as:
     *
     * "a->b" if a != b
     * "a" if a == b
     *
     * Input: nums = [0,1,3,50,75], lower = 0, upper = 99
     * Output: ["2","4->49","51->74","76->99"]
     *
     * Constraints:
     *
     * -10^9 <= lower <= upper <= 10^9
     * 0 <= nums.length <= 100
     * lower <= nums[i] <= upper
     * All the values of nums are unique.
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    // time = O(n), space = O(1)
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        if (n == 0) {
            res.add(Arrays.asList(lower, upper));
            return res;
        }

        if (lower < nums[0]) res.add(Arrays.asList(lower, nums[0] - 1));
        for (int i = 0 ; i < n - 1; i++) {
            if (nums[i] != nums[i + 1] - 1) res.add(Arrays.asList(nums[i] + 1, nums[i + 1] - 1));
        }
        if (nums[n - 1] < upper) res.add(Arrays.asList(nums[n - 1] + 1, upper));
        return res;
    }
}