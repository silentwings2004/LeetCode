package LC3901_4200;
import java.util.*;
public class LC3904_SmallestStableIndexII {
    /**
     * You are given an integer array nums of length n and an integer k.
     *
     * For each index i, define its instability score as max(nums[0..i]) - min(nums[i..n - 1]).
     *
     * In other words:
     *
     * max(nums[0..i]) is the largest value among the elements from index 0 to index i.
     * min(nums[i..n - 1]) is the smallest value among the elements from index i to index n - 1.
     * An index i is called stable if its instability score is less than or equal to k.
     *
     * Return the smallest stable index. If no such index exists, return -1.
     *
     * Input: nums = [5,0,1,4], k = 3
     * Output: 3
     *
     * Input: nums = [3,2,1], k = 1
     * Output: -1
     *
     * Input: nums = [0], k = 0
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     * 0 <= k <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length, mx = nums[0];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1);
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            mx = Math.max(mx, x);
            if (mx - map.firstKey() <= k) return i;
            map.put(x, map.get(x) - 1);
            if (map.get(x) == 0) map.remove(x);
        }
        return -1;
    }

    // S2
    // time = O(n), space = O(n)
    public int firstStableIndex2(int[] nums, int k) {
        int n = nums.length;
        int[] suf = new int[n];
        suf[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) suf[i] = Math.min(suf[i + 1], nums[i]);
        int mx = nums[0];
        for (int i = 0; i < n; i++) {
            mx = Math.max(mx, nums[i]);
            if (mx - suf[i] <= k) return i;
        }
        return -1;
    }
}