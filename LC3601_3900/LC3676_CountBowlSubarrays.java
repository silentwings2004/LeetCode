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
        int[] stk = new int[n + 1];
        int tt = 0;
        long res = 0;
        for (int i = 0; i < n; i++) {
            while (tt > 0 && nums[stk[tt]] < nums[i]) {
                if (i - stk[tt] > 1) res++;
                tt--;
            }
            if (tt > 0 && i - stk[tt] > 1) res++;
            stk[++tt] = i;
        }
        return res;
    }
}
/**
 * 对于每个右端点 r，我们只需要找 nums[r] 左侧最近的大于等于 nums[r] 的数的下标 l。
 * 如果 l 存在且 i−l+1≥3，那么找到了一个合法子数组，把答案加一。
 */