package LC001_300;
import java.util.*;
public class LC16_3SumClosest {
    /**
     * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest
     * to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
     *
     * Input: nums = [-1,2,1,-4], target = 1
     * Output: 2
     *
     * Constraints:
     *
     * 3 <= nums.length <= 10^3
     * -10^3 <= nums[i] <= 10^3
     * -10^4 <= target <= 10^4
     * @param nums
     * @param target
     * @return
     */
    // S1
    // time = O(n^2), space = O(1)
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int n = nums.length, res = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) return sum;
                if (Math.abs(sum - target) < Math.abs(res - target)) {
                    res = sum;
                }
                if (sum < target) j++;
                else k--;
            }
        }
        return res;
    }

    // S2
    // time = O(n^2), space = O(1)
    public int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int[] res = new int[2];
        Arrays.fill(res, Integer.MAX_VALUE);

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1, k = n - 1; j < k; j++) {
                while (j < k - 1 && nums[i] + nums[j] + nums[k - 1] >= target) k--;
                int s = nums[i] + nums[j] + nums[k];
                if (res[0] > Math.abs(s - target)) {
                    res = new int[]{Math.abs(s - target), s};
                }
                if (k - 1 > j) {
                    s = nums[i] + nums[j] + nums[k - 1];
                    if (res[0] > Math.abs(s - target)) {
                        res = new int[]{Math.abs(s - target), s};
                    }
                }
            }
        }
        return res[1];
    }
}
/**
 * nums[i] + nums[j] + nums[k] >= target
 * nums[i] + nums[j] + nums[k - 1] < target
 */