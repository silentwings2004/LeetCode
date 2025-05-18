package LC2401_2700;
import java.util.*;
public class LC2453_DestroySequentialTargets {
    /**
     * You are given a 0-indexed array nums consisting of positive integers, representing targets on a number line. You
     * are also given an integer space.
     *
     * You have a machine which can destroy targets. Seeding the machine with some nums[i] allows it to destroy all
     * targets with values that can be represented as nums[i] + c * space, where c is any non-negative integer. You want
     * to destroy the maximum number of targets in nums.
     *
     * Return the minimum value of nums[i] you can seed the machine with to destroy the maximum number of targets.
     *
     * Input: nums = [3,7,8,1,1,5], space = 2
     * Output: 1
     *
     * Input: nums = [1,3,5,2,4,6], space = 2
     * Output: 1
     *
     * Input: nums = [6,2,5], space = 100
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= space <= 10^9
     * @param nums
     * @param space
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int destroyTargets(int[] nums, int space) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Arrays.sort(nums);
        int n = nums.length, s = 0, res = -1;
        for (int i = n - 1; i >= 0; i--) {
            int r = nums[i] % space;
            map.put(r, map.getOrDefault(r, 0) + 1);
            if (map.get(r) >= s) {
                s = map.get(r);
                res = nums[i];
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public int destroyTargets2(int[] nums, int space) {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> cnt = new HashMap<>();
        int max = 0;
        for (int x : nums) {
            int r = x % space;
            cnt.put(r, cnt.getOrDefault(r, 0) + 1);
            max = Math.max(max, cnt.get(r));
            if (!map.containsKey(r) || map.get(r) > x) map.put(r, x);
        }

        int res = Integer.MAX_VALUE;
        for (int key : cnt.keySet()) {
            if (cnt.get(key) == max) res = Math.min(res, map.get(key));
        }
        return res;
    }
}
