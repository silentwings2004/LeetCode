package LC3601_3900;
import java.util.*;
public class LC3761_MinimumAbsoluteDistanceBetweenMirrorPairs {
    /**
     * You are given an integer array nums.
     *
     * A mirror pair is a pair of indices (i, j) such that:
     *
     * 0 <= i < j < nums.length, and
     * reverse(nums[i]) == nums[j], where reverse(x) denotes the integer formed by reversing the digits of x. Leading
     * zeros are omitted after reversing, for example reverse(120) = 21.
     * Return the minimum absolute distance between the indices of any mirror pair. The absolute distance between
     * indices i and j is abs(i - j).
     *
     * If no mirror pair exists, return -1.
     *
     * Input: nums = [12,21,45,33,54]
     * Output: 1
     *
     * Input: nums = [120,21]
     * Output: 1
     *
     * Input: nums = [21,120]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int minMirrorPairDistance(int[] nums) {
        int n = nums.length, res = n + 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) res = Math.min(res, i - map.get(nums[i]));
            map.put(reverse(nums[i]), i);
        }
        return res == n + 1 ? -1 : res;
    }

    private int reverse(int x) {
        int t = 0;
        while (x > 0) {
            t = t * 10 + x % 10;
            x /= 10;
        }
        return t;
    }
}
/**
 * 双变量问题，枚举右，维护左
 */
