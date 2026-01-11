package LC3601_3900;
import java.util.*;
public class LC3795_MinimumSubarrayLengthWithDistinctSumAtLeastK {
    /**
     * You are given an integer array nums and an integer k.
     *
     * Return the minimum length of a subarray whose sum of the distinct values present in that subarray (each value
     * counted once) is at least k. If no such subarray exists, return -1.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [2,2,3,1], k = 4
     * Output: 2
     *
     * Input: nums = [3,2,3,4], k = 5
     * Output: 2
     *
     * Input: nums = [5,5,4], k = 5
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * 1 <= k <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public int minLength(int[] nums, int k) {
        int n = nums.length, res = n + 1;
        int mx = nums[0];
        for (int x : nums) mx = Math.max(mx, x);
        int[] mp = new int[mx + 1];
        long s = 0;
        for (int i = 0, j = 0; i < n; i++) {
            int x = nums[i];
            if (mp[x] == 0) s += x;
            mp[x]++;
            while (s >= k) {
                res = Math.min(res, i - j + 1);
                int y = nums[j++];
                mp[y]--;
                if (mp[y] == 0) s -= y;
            }
        }
        return res == n + 1 ? -1 : res;
    }
}