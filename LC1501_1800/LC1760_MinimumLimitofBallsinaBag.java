package LC1501_1800;
import java.util.*;
public class LC1760_MinimumLimitofBallsinaBag {
    /**
     * You are given an integer array nums where the ith bag contains nums[i] balls. You are also given an integer
     * maxOperations.
     *
     * You can perform the following operation at most maxOperations times:
     *
     * Take any bag of balls and divide it into two new bags with a positive number of balls.
     * For example, a bag of 5 balls can become two new bags of 1 and 4 balls, or two new bags of 2 and 3 balls.
     * Your penalty is the maximum number of balls in a bag. You want to minimize your penalty after the operations.
     *
     * Return the minimum possible penalty after performing the operations.
     *
     * Input: nums = [9], maxOperations = 2
     * Output: 3
     *
     * Input: nums = [2,4,8,2], maxOperations = 4
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= maxOperations, nums[i] <= 10^9
     * @param nums
     * @param maxOperations
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int minimumSize(int[] nums, int maxOperations) {
        int l = 1, r = (int) 1e9;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(nums, maxOperations, mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check(int[] nums, int op, int mid) {
        int cnt = 0;
        for (int x : nums) {
            cnt += (x + mid - 1) / mid - 1;
            if (cnt > op) return false;
        }
        return true;
    }
}