package LC2700_3000;
import java.util.*;
public class LC2958_LengthofLongestSubarrayWithatMostKFrequency {
    /**
     * You are given an integer array nums and an integer k.
     *
     * The frequency of an element x is the number of times it occurs in an array.
     *
     * An array is called good if the frequency of each element in this array is less than or equal to k.
     *
     * Return the length of the longest good subarray of nums.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,2,3,1,2,3,1,2], k = 2
     * Output: 6
     *
     * Input: nums = [1,2,1,2,1,2,1,2], k = 1
     * Output: 2
     *
     * Input: nums = [5,5,5,5,5,5,5], k = 4
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= nums.length
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length, res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            while (map.get(nums[i]) > k) {
                map.put(nums[j], map.get(nums[j]) - 1);
                if (map.get(nums[j]) == 0) map.remove(nums[j]);
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
/**
 * ref: LC3
 */