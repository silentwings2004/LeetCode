package LC3901_4200;
import java.util.*;
public class LC4074_MaximumProductPairWithTargetSum {
    /**
     * You are given an integer array nums and an integer target.
     *
     * A pair of distinct indices (i, j) is valid if:
     *
     * nums[i] + nums[j] == target
     * nums[i] > nums[j]
     * Return a valid pair [i, j] whose product nums[i] * nums[j] is maximum among all valid pairs. If no valid pair
     * exists, return [-1, -1].
     *
     * If multiple valid pairs achieve the maximum product, you may return any of them.
     *
     * Input: nums = [1,2,3,4], target = 5
     * Output: [2,1]
     *
     * Input: nums = [-3,-1,4,2], target = 1
     * Output: [3,1]
     *
     * Input: nums = [3,3,5], target = 6
     * Output: [-1,-1]
     *
     * Constraints:
     *
     * 2 <= nums.length <= 100
     * -100 <= nums[i], target <= 100
     * @param nums
     * @param target
     * @return
     */
    // S1
    // time = O(n^2), space = O(1)
    public int[] maxProductPair(int[] nums, int target) {
        int n = nums.length, t = Integer.MIN_VALUE;
        int[] res = new int[]{-1, -1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] == nums[j]) continue;
                if (nums[i] + nums[j] == target) {
                    int v = nums[i] * nums[j];
                    if (v > t) {
                        t = v;
                        res = nums[i] > nums[j] ? new int[]{i, j} : new int[]{j, i};
                    }
                }
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public int[] maxProductPair2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] res = new int[]{-1, -1};
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (x * 2 == target) continue;
            int y = target - x;
            if (map.containsKey(y) && (res[0] < 0 || x * y > nums[res[0]] * (target - nums[res[0]]))) {
                res = new int[]{i, map.get(y)};
            }
            map.put(x, i);
        }
        if (res[0] >= 0 && nums[res[0]] < nums[res[1]]) {
            int t = res[0];
            res[0] = res[1];
            res[1] = t;
        }
        return res;
    }
}