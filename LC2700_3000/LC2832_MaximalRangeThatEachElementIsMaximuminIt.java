package LC2700_3000;
import java.util.*;
public class LC2832_MaximalRangeThatEachElementIsMaximuminIt {
    /**
     * You are given a 0-indexed array nums of distinct integers.
     *
     * Let us define a 0-indexed array ans of the same length as nums in the following way:
     *
     * ans[i] is the maximum length of a subarray nums[l..r], such that the maximum element in that subarray is equal
     * to nums[i].
     * Return the array ans.
     *
     * Note that a subarray is a contiguous part of the array.
     *
     * Input: nums = [1,5,4,3,6]
     * Output: [1,4,2,1,5]
     *
     * Input: nums = [1,2,3,4,5]
     * Output: [1,2,3,4,5]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * All elements in nums are distinct.
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 100010;
    public int[] maximumLengthOfRanges(int[] nums) {
        int[] stk = new int[N];
        int n = nums.length, tt = 0;
        int[] l = new int[n + 1], r = new int[n + 1];
        Arrays.fill(l, -1);
        Arrays.fill(r, n);

        for (int i = 0; i <= n; i++) {
            int x = i == n ? 0 : nums[i];
            while (tt > 0 && nums[stk[tt]] <= x) r[stk[tt--]] = i;
            l[i] = tt == 0 ? -1 : stk[tt];
            stk[++tt] = i;
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) res[i] = r[i] - l[i] - 1;
        return res;
    }
}