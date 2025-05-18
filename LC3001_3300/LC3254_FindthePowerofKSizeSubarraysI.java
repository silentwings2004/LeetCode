package LC3001_3300;
import java.util.*;
public class LC3254_FindthePowerofKSizeSubarraysI {
    /**
     * You are given an array of integers nums of length n and a positive integer k.
     *
     * The power of an array is defined as:
     *
     * Its maximum element if all of its elements are consecutive and sorted in ascending order.
     * -1 otherwise.
     * You need to find the power of all subarrays of nums of size k.
     *
     * Return an integer array results of size n - k + 1, where results[i] is the power of nums[i..(i + k - 1)].
     *
     * Input: nums = [1,2,3,4,3,2,5], k = 3
     * Output: [3,4,-1,-1,-1]
     *
     * Input: nums = [1,2,3,4,3,2,5], k = 3
     * Output: [3,4,-1,-1,-1]
     *
     * Input: nums = [3,2,3,2,3,2], k = 2
     * Output: [-1,3,-1,3,-1]
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 500
     * 1 <= nums[i] <= 10^5
     * 1 <= k <= n
     * @param nums
     * @param k
     * @return
     */
    // time = O(n * k), space = O(1)
    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        for (int i = 0; i + k - 1 < n; i++) {
            res[i] = nums[i];
            for (int j = i + 1; j - i + 1 <= k; j++) {
                if (nums[j] - nums[j - 1] == 1) res[i] = nums[j];
                else {
                    res[i] = -1;
                    break;
                }
            }
        }
        return res;
    }
}