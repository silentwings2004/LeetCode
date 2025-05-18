package LC3001_3300;
import java.util.*;
public class LC3065_MinimumOperationstoExceedThresholdValueI {
    /**
     * You are given a 0-indexed integer array nums, and an integer k.
     *
     * In one operation, you can remove one occurrence of the smallest element of nums.
     *
     * Return the minimum number of operations needed so that all elements of the array are greater than or equal to k.
     *
     * Input: nums = [2,11,10,1,3], k = 10
     * Output: 3
     *
     * Input: nums = [1,1,2,4,9], k = 1
     * Output: 0
     *
     * Input: nums = [1,1,2,4,9], k = 9
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.length <= 50
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= 10^9
     * The input is generated such that there is at least one index i such that nums[i] >= k.
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int minOperations(int[] nums, int k) {
        int res = 0;
        for (int x : nums) {
            if (x < k) res++;
        }
        return res;
    }
}