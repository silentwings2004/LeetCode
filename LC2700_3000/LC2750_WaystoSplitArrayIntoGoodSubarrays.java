package LC2700_3000;
import java.util.*;
public class LC2750_WaystoSplitArrayIntoGoodSubarrays {
    /**
     * You are given a binary array nums.
     *
     * A subarray of an array is good if it contains exactly one element with the value 1.
     *
     * Return an integer denoting the number of ways to split the array nums into good subarrays. As the number may be
     * too large, return it modulo 10^9 + 7.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [0,1,0,0,1]
     * Output: 3
     *
     * Input: nums = [0,1,0]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 1
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int numberOfGoodSubarraySplits(int[] nums) {
        int n = nums.length;
        List<Integer> q = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) q.add(i);
        }
        if (q.size() <= 1) return q.size();

        long mod = (long)(1e9 + 7), res = 1;
        for (int i = 0; i + 1 < q.size(); i++) {
            int d = q.get(i + 1) - q.get(i);
            res = (res * d) % mod;
        }
        return (int)res;
    }
}