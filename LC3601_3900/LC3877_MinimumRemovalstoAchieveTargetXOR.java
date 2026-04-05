package LC3601_3900;
import java.util.*;
public class LC3877_MinimumRemovalstoAchieveTargetXOR {
    /**
     * You are given an integer array nums and an integer target.
     *
     * You may remove any number of elements from nums (possibly zero).
     *
     * Return the minimum number of removals required so that the bitwise XOR of the remaining elements equals target.
     * If it is impossible to achieve target, return -1.
     *
     * The bitwise XOR of an empty array is 0.
     *
     * Input: nums = [1,2,3], target = 2
     * Output: 1
     *
     * Input: nums = [2,4], target = 1
     * Output: -1
     *
     * Input: nums = [7], target = 7
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 40
     * 0 <= nums[i] <= 10^4
     * 0 <= target <= 10^4
     * @param nums
     * @param target
     * @return
     */
    // time = O(n * 2^14), space = O(2^14)
    public int minRemovals(int[] nums, int target) {
        int m = 1 << 14;
        int[] f = new int[m];
        Arrays.fill(f, -1);
        f[0] = 0;
        for (int x : nums) {
            int[] g = f.clone();
            for (int j = 0; j < m; j++) {
                if (f[j] != -1) {
                    int y = x ^ j;
                    g[y] = Math.max(g[y], f[j] + 1);
                }
            }
            f = g;
        }
        return f[target] == -1 ? -1 : nums.length - f[target];
    }
}
/**
 * Ref: LC2915
 */