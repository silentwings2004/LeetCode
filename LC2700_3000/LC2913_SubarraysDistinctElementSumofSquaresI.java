package LC2700_3000;
import java.util.*;
public class LC2913_SubarraysDistinctElementSumofSquaresI {
    /**
     * You are given a 0-indexed integer array nums.
     *
     * The distinct count of a subarray of nums is defined as:
     *
     * Let nums[i..j] be a subarray of nums consisting of all the indices from i to j such that
     * 0 <= i <= j < nums.length. Then the number of distinct values in nums[i..j] is called the distinct count of
     * nums[i..j].
     * Return the sum of the squares of distinct counts of all subarrays of nums.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,2,1]
     * Output: 15
     *
     * Input: nums = [1,1]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n^3), space = O(n)
    public int sumCounts(List<Integer> nums) {
        int n = nums.size(), res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                HashSet<Integer> set = new HashSet<>();
                for (int k = i; k <= j; k++) {
                    set.add(nums.get(k));
                }
                res += set.size() * set.size();
            }
        }
        return res;
    }
}