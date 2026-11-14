package LC3901_4200;
import java.util.*;
public class LC4110_FindtheMaximumLengthofValidSubsequenceIII {
    /**
     * You are given an integer array nums and a positive integer k.
     *
     * A subsequence of nums is valid if the sum of every pair of adjacent elements in the subsequence is divisible by k.
     *
     * A subsequence of length 1 is always valid.
     *
     * Return the length of the longest valid subsequence of nums.
     *
     * Input: nums = [1,2,3,4,5], k = 3
     * Output: 4
     *
     * Input: nums = [5,10,15], k = 5
     * Output: 3
     *
     * Input: nums = [1,3,5,7,9], k = 4
     * Output: 5
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= 10^5
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(k)
    public int maximumLength(int[] nums, int k) {
        int n = nums.length, res = 1;
        int[] f = new int[n + 1];
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1] % k, y = (k - x) % k;
            if (map.containsKey(y)) f[i] = f[map.get(y)] + 1;
            else f[i] = 1;
            res = Math.max(res, f[i]);
            if (!map.containsKey(x) || f[map.get(x)] < f[i]) map.put(x, i);
        }
        return res;
    }
}