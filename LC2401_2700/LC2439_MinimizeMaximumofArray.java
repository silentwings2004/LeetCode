package LC2401_2700;
import java.util.*;
public class LC2439_MinimizeMaximumofArray {
    /**
     * You are given a 0-indexed array nums comprising of n non-negative integers.
     *
     * In one operation, you must:
     *
     * Choose an integer i such that 1 <= i < n and nums[i] > 0.
     * Decrease nums[i] by 1.
     * Increase nums[i - 1] by 1.
     * Return the minimum possible value of the maximum integer of nums after performing any number of operations.
     *
     * Input: nums = [3,7,1,6]
     * Output: 5
     *
     * Input: nums = [10,1]
     * Output: 10
     *
     * Constraints:
     *
     * n == nums.length
     * 2 <= n <= 10^5
     * 0 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // S1: Binary Search
    // time = O(nlogn), space = O(1)
    public int minimizeArrayValue(int[] nums) {
        int n = nums.length, max = nums[0];
        for (int x : nums) max = Math.max(max, x);
        int l = 0, r = max;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(nums, mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check(int[] nums, int t) {
        int n = nums.length;
        long diff = 0;
        for (int i = n - 1; i >= 0; i--) {
            diff += nums[i];
            if (diff > t) {
                if (i > 0) diff -= t;
                else return false;
            } else diff = 0;
        }
        return true;
    }

    // S2: Greedy
    // time = O(n), space = O(1)
    public int minimizeArrayValue2(int[] nums) {
        long sum = 0, avg = nums[0];
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            avg = Math.max(avg, (sum + i) / (i + 1));
        }
        return (int) avg;
    }
}