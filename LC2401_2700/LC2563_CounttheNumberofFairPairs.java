package LC2401_2700;
import java.util.*;
public class LC2563_CounttheNumberofFairPairs {
    /**
     * Given a 0-indexed integer array nums of size n and two integers lower and upper, return the number of fair pairs.
     *
     * A pair (i, j) is fair if:
     *
     * 0 <= i < j < n, and
     * lower <= nums[i] + nums[j] <= upper
     *
     * Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
     * Output: 6
     *
     * Input: nums = [1,7,9,2,5], lower = 11, upper = 11
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * nums.length == n
     * -10^9 <= nums[i] <= 10^9
     * -10^9 <= lower <= upper <= 10^9
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int n = nums.length;
        long res = 0;
        for (int i = 1; i < n; i++) {
            int a = lower - nums[i], b = upper - nums[i];
            int x = helper(nums, 0, i - 1, a - 1), y = helper(nums, 0, i - 1, b);
            res += y - x;
        }
        return res;
    }

    private int helper(int[] nums, int l, int r, int t) {
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] <= t) l = mid;
            else r = mid - 1;
        }
        return nums[r] <= t ? r : r - 1;
    }
}
/**
 * [x x x x x x] j
 *    i
 * [lower - nums[j], upper - nums[j]]
 * 二分法，前提是要有序
 * 数值范围如果特别小 => 桶排序，但这里特别大
 * 虽然这里i, j有先后顺序，但是这里i,j是完全对称的。
 */