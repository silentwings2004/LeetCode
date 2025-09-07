package LC3601_3900;
import java.util.*;
public class LC3676_CountBowlSubarrays {
    /**
     * You are given an integer array nums with distinct elements.
     *
     * A subarray nums[l...r] of nums is called a bowl if:
     *
     * The subarray has length at least 3. That is, r - l + 1 >= 3.
     * The minimum of its two ends is strictly greater than the maximum of all elements in between. That is,
     * min(nums[l], nums[r]) > max(nums[l + 1], ..., nums[r - 1]).
     * Return the number of bowl subarrays in nums.
     *
     * A subarray is a contiguous sequence of elements within an array.
     *
     * Input: nums = [2,5,3,1,4]
     * Output: 2
     *
     * Input: nums = [5,1,2,3,4]
     * Output: 3
     *
     * Input: nums = [1000000000,999999999,999999998]
     * Output: 0
     *
     * Constraints:
     *
     * 3 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * nums consists of distinct elements.
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public long bowlSubarrays(int[] nums) {
        int n = nums.length;
        int[] l = new int[n], r = new int[n];
        Arrays.fill(l, -1);
        Arrays.fill(r, n);
        int[] stk = new int[n + 1];
        int tt = 0;
        for (int i = 0; i < n; i++) {
            while (tt > 0 && nums[stk[tt]] < nums[i]) tt--;
            if (tt > 0) l[i] = stk[tt];
            stk[++tt] = i;
        }

        stk = new int[n + 1];
        tt = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (tt > 0 && nums[stk[tt]] < nums[i]) tt--;
            if (tt > 0) r[i] = stk[tt];
            stk[++tt] = i;
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            if (l[i] != -1 && r[i] != n) res++;
        }
        return res;
    }
}