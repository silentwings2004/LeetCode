package LC2700_3000;
import java.util.*;
public class LC2702_MinimumOperationstoMakeNumbersNonpositive {
    /**
     * You are given a 0-indexed integer array nums and two integers x and y. In one operation, you must choose an index
     * i such that 0 <= i < nums.length and perform the following:
     *
     * Decrement nums[i] by x.
     * Decrement values by y at all indices except the ith one.
     * Return the minimum number of operations to make all the integers in nums less than or equal to zero.
     *
     * Input: nums = [3,4,1,7,6], x = 4, y = 2
     * Output: 3
     *
     * Input: nums = [1,2,1], x = 2, y = 1
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= y < x <= 10^9
     * @param nums
     * @param x
     * @param y
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int minOperations(int[] nums, int x, int y) {
        int l = 0, r = (int)1e9;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(nums, x, y, mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check(int[] nums, int x, int y, int mid) {
        long t = 0;
        for (int num : nums) {
            long d = (long)num - (long)y * mid;
            if (d <= 0) continue;
            else t += (d + x - y - 1) / (x - y);
        }
        return t <= mid;
    }
}