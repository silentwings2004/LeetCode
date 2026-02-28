package LC3601_3900;
import java.util.*;
public class LC3852_SmallestPairWithDifferentFrequencies {
    /**
     * You are given an integer array nums.
     *
     * Consider all pairs of distinct values x and y from nums such that:
     *
     * x < y
     * x and y have different frequencies in nums.
     * Among all such pairs:
     *
     * Choose the pair with the smallest possible value of x.
     * If multiple pairs have the same x, choose the one with the smallest possible value of y.
     * Return an integer array [x, y]. If no valid pair exists, return [-1, -1].
     *
     * The frequency of a value x is the number of times it occurs in the array.
     *
     * Input: nums = [1,1,2,2,3,4]
     * Output: [1,3]
     *
     * Input: nums = [1,5]
     * Output: [-1,-1]
     *
     * Input: nums = [7]
     * Output: [-1,-1]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int[] minDistinctFreqPair(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int mn = nums[0], mx = nums[0];
        for (int x : nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
            mn = Math.min(mn, x);
            mx = Math.max(mx, x);
        }
        int[] res = new int[]{mn, mx + 1};
        for (int x : nums) {
            if (x == mn || map.get(x) == map.get(mn)) continue;
            res[1] = Math.min(res[1], x);
        }
        if (res[1] == mx + 1) return new int[]{-1, -1};
        return res;
    }
}