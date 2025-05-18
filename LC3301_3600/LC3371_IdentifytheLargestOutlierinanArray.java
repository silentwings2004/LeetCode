package LC3301_3600;
import java.util.*;
public class LC3371_IdentifytheLargestOutlierinanArray {
    /**
     * You are given an integer array nums. This array contains n elements, where exactly n - 2 elements are special
     * numbers. One of the remaining two elements is the sum of these special numbers, and the other is an outlier.
     *
     * An outlier is defined as a number that is neither one of the original special numbers nor the element
     * representing the sum of those numbers.
     *
     * Note that special numbers, the sum element, and the outlier must have distinct indices, but may share the same
     * value.
     *
     * Return the largest potential outlier in nums.
     *
     * Input: nums = [2,3,5,10]
     *
     * Output: 10
     *
     * Input: nums = [-2,-1,-3,-6,4]
     *
     * Output: 4
     *
     * Input: nums = [1,1,1,1,1,5,5]
     *
     * Output: 5
     *
     * Constraints:
     *
     * 3 <= nums.length <= 10^5
     * -1000 <= nums[i] <= 1000
     * The input is generated such that at least one potential outlier exists in nums.
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int getLargestOutlier(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int s = 0;
        for (int x : nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
            s += x;
        }

        int res = -1100;
        for (int x : nums) {
            int r = s - x;
            if (r % 2 != 0) continue;
            int y = r / 2;
            if (y == x && map.get(x) > 1 || y != x && map.containsKey(y)) res = Math.max(res, x);
        }
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public int getLargestOutlier2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int tot = 0;
        for (int x : nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
            tot += x;
        }

        int res = -1010;
        for (int s : nums) {
            map.put(s, map.get(s) - 1);
            int x = tot - s * 2;
            if (map.containsKey(x) && map.get(x) > 0) res = Math.max(res, x);
            map.put(s, map.get(s) + 1);
        }
        return res;
    }
}