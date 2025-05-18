package LC2401_2700;
import java.util.*;
public class LC2537_CounttheNumberofGoodSubarrays {
    /**
     * Given an integer array nums and an integer k, return the number of good subarrays of nums.
     *
     * A subarray arr is good if it there are at least k pairs of indices (i, j) such that i < j and arr[i] == arr[j].
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,1,1,1,1], k = 10
     * Output: 1
     *
     * Input: nums = [3,1,4,3,2,2,4], k = 2
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i], k <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public long countGood(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        long tot = 0, res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (map.get(nums[i]) >= 2) tot += map.get(nums[i]) - 1;
            while (tot >= k) {
                res += n - i;
                map.put(nums[j], map.get(nums[j]) - 1);
                tot -= map.get(nums[j]);
                j++;
            }
        }
        return res;
    }
}