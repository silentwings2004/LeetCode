package LC3901_4200;
import java.util.*;
public class LC3984_DivisibleGame {
    /**
     * You are given an integer array nums of length n.
     *
     * Alice and Bob are playing a game. Alice chooses:
     *
     * An integer k such that k > 1.
     * Two integers l and r such that 0 <= l <= r < n.
     * Initially, both Alice's and Bob's scores are 0.
     *
     * For each index i in the range [l, r] (inclusive):
     *
     * If nums[i] is divisible by k, Alice's score increases by nums[i].
     * Otherwise, Bob's score increases by nums[i].
     * The score difference is Alice's score minus Bob's score.
     *
     * Alice wants to maximize the score difference. If there are multiple values of k that achieve the maximum score
     * difference, she chooses the smallest such k.
     *
     * Return the product of the maximum score difference and the chosen value of k. Since the result can be large,
     * return it modulo 10^9 + 7.
     *
     * Input: nums = [1,4,6,8]
     * Output: 36
     *
     * Input: nums = [2,1,2]
     * Output: 6
     *
     * Input: nums = [1]
     * Output: 1000000005
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // time = O(D * n), space = O(D)  D: nums 中不同质因子的个数
    public int divisibleGame(int[] nums) {
       long mod = (long)(1e9 + 7);
       List<Integer> q = new ArrayList<>();
       for (int x : nums) {
           for (int i = 2; i <= x / i; i++) {
               if (x % i == 0) {
                   q.add(i);
                   while (x % i == 0) x /= i;
               }
           }
           if (x > 1) q.add(x);
       }
       if (q.size() == 0) return (int)(mod - 2);

       Collections.sort(q);
       int maxDiff = Integer.MIN_VALUE, bestK = 0, preK = 0;
       for (int k : q) {
           if (k == preK) continue;
           int diff = maxSubArray(nums, k);
           if (diff > maxDiff) {
               maxDiff = diff;
               bestK = k;
           }
           preK = k;
       }
       return (int)((1L * maxDiff * bestK % mod + mod) % mod);
    }

    private int maxSubArray(int[] nums, int k) {
        int res = Integer.MIN_VALUE, f = 0;
        for (int x : nums) {
            f = Math.max(f, 0) + (x % k == 0 ? x : -x);
            res = Math.max(res, f);
        }
        return res;
    }
}
/**
 * 只需要枚举质因子
 */