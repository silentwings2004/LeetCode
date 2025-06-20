package LC3301_3600;
import java.util.*;
public class LC3583_CountSpecialTriplets {
    /**
     * You are given an integer array nums.
     *
     * A special triplet is defined as a triplet of indices (i, j, k) such that:
     *
     * 0 <= i < j < k < n, where n = nums.length
     * nums[i] == nums[j] * 2
     * nums[k] == nums[j] * 2
     * Return the total number of special triplets in the array.
     *
     * Since the answer may be large, return it modulo 10^9 + 7.
     *
     * Input: nums = [6,3,6]
     * Output: 1
     *
     * Input: nums = [0,1,0,0]
     * Output: 1
     *
     * Input: nums = [8,4,2,8,4]
     * Output: 2
     *
     * Constraints:
     *
     * 3 <= n == nums.length <= 10^5
     * 0 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int specialTriplets(int[] nums) {
        HashMap<Integer, Integer> lf = new HashMap<>();
        HashMap<Integer, Integer> rf = new HashMap<>();
        long mod = (long)(1e9 + 7), res = 0;
        for (int x : nums) rf.put(x, rf.getOrDefault(x, 0) + 1);
        for (int x : nums) {
            rf.put(x, rf.get(x) - 1);
            int v = x * 2;
            int l = lf.getOrDefault(v, 0), r = rf.getOrDefault(v, 0);
            res = (res + 1L * l * r) % mod;
            lf.put(x, lf.getOrDefault(x, 0) + 1);
        }
        return (int)res;
    }
}