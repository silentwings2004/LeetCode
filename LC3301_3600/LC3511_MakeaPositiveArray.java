package LC3301_3600;
import java.util.*;
public class LC3511_MakeaPositiveArray {
    /**
     * You are given an array nums. An array is considered positive if the sum of all numbers in each subarray with more
     * than two elements is positive.
     *
     * You can perform the following operation any number of times:
     *
     * Replace one element in nums with any integer between -10^18 and 10^18.
     * Find the minimum number of operations needed to make nums positive.
     *
     * Input: nums = [-10,15,-12]
     * Output: 1
     *
     * Input: nums = [-1,-2,3,-1,2,6]
     * Output: 1
     *
     * Input: nums = [1,2,3]
     * Output: 0
     *
     * Constraints:
     *
     * 3 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int makeArrayPositive(int[] nums) {
        final long inf = (long)1E18;
        int n = nums.length, res = 0;
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = nums[i];
        long minv = inf;
        for (int i = 2; i < n; i++) {
            long t = a[i - 2] + a[i - 1] + a[i];
            minv = Math.min(minv + a[i], t);
            if (minv <= 0) {
                a[i] = minv = inf;
                res++;
            }
        }
        return res;
    }
}