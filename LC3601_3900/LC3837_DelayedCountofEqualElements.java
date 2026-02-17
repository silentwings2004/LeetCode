package LC3601_3900;
import java.util.*;
public class LC3837_DelayedCountofEqualElements {
    /**
     * You are given an integer array nums of length n and an integer k.
     *
     * For each index i, define the delayed count as the number of indices j such that:
     *
     * i + k < j <= n - 1, and
     * nums[j] == nums[i]
     * Return an array ans where ans[i] is the delayed count of index i.
     *
     * Input: nums = [1,2,1,1], k = 1
     * Output: [2,0,0,0]
     *
     * Input: nums = [3,1,3,1], k = 0
     * Output: [1,1,0,0]
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * 0 <= k <= n - 1
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public int[] delayedCount(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = n - 1, j = n - 1; i >= 0; i--) {
            if (j > i + k) {
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
                j--;
            }
            res[i] = map.getOrDefault(nums[i], 0);
        }
        return res;
    }
}