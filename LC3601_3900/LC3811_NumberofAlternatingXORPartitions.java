package LC3601_3900;

import java.util.HashMap;

public class LC3811_NumberofAlternatingXORPartitions {
    /**
     * You are given an integer array nums and two distinct integers target1 and target2.
     *
     * A partition of nums splits it into one or more contiguous, non-empty blocks that cover the entire array without
     * overlap.
     *
     * A partition is valid if the bitwise XOR of elements in its blocks alternates between target1 and target2,
     * starting with target1.
     *
     * Formally, for blocks b1, b2, …:
     *
     * XOR(b1) = target1
     * XOR(b2) = target2 (if it exists)
     * XOR(b3) = target1, and so on.
     * Return the number of valid partitions of nums, modulo 10^9 + 7.
     *
     * Note: A single block is valid if its XOR equals target1.
     *
     * Input: nums = [2,3,1,4], target1 = 1, target2 = 5
     * Output: 1
     *
     * Input: nums = [1,0,0], target1 = 1, target2 = 0
     * Output: 3
     *
     * Input: nums = [7], target1 = 1, target2 = 7
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i], target1, target2 <= 10^5
     * target1 != target2
     * @param nums
     * @param target1
     * @param target2
     * @return
     */
    // time = O(n), space = O(n);
    public int alternatingXOR(int[] nums, int target1, int target2) {
        long mod = (long)(1e9 + 7);
        int n = nums.length;
        long[][] f = new long[n][2];
        HashMap<Integer, Long> mp0 = new HashMap<>();
        HashMap<Integer, Long> mp1 = new HashMap<>();
        mp1.put(0, 1L);
        for (int i = 0, t = 0; i < n; i++) {
            t ^= nums[i];
            f[i][0] = mp1.getOrDefault(t ^ target1, 0L);
            f[i][1] = mp0.getOrDefault(t ^ target2, 0L);
            mp0.put(t, (mp0.getOrDefault(t, 0L) + f[i][0]) % mod);
            mp1.put(t, (mp1.getOrDefault(t, 0L) + f[i][1]) % mod);
        }
        return (int)((f[n - 1][0] + f[n - 1][1]) % mod);
    }
}