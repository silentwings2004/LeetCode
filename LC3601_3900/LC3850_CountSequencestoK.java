package LC3601_3900;
import java.util.*;
public class LC3850_CountSequencestoK {
    /**
     * You are given an integer array nums, and an integer k.
     *
     * Start with an initial value val = 1 and process nums from left to right. At each index i, you must choose exactly
     * one of the following actions:
     *
     * Multiply val by nums[i].
     * Divide val by nums[i].
     * Leave val unchanged.
     * After processing all elements, val is considered equal to k only if its final rational value exactly equals k.
     *
     * Return the count of distinct sequences of choices that result in val == k.
     *
     * Note: Division is rational (exact), not integer division. For example, 2 / 4 = 1 / 2.
     *
     * Input: nums = [2,3,2], k = 6
     * Output: 2
     *
     * Input: nums = [4,6,3], k = 2
     * Output: 2
     *
     * Input: nums = [1,5], k = 1
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 19
     * 1 <= nums[i] <= 6
     * 1 <= k <= 10^15
     * @param nums
     * @param k
     * @return
     */
    // time = O(3^n), space = O(3^n)
    int[] nums;
    long k;
    HashMap<String, Integer> memo;
    public int countSequences(int[] nums, long k) {
        this.nums = nums;
        this.k = k;
        memo = new HashMap<>();
        return dfs(0, 1, 1);
    }

    private int dfs(int u, long a, long b) {
        long g = gcd(a, b);
        a /= g;
        b /= g;
        if (u == nums.length) return a == b * k ? 1 : 0;
        String h = u + "#" + a + "#" + b;
        if (memo.containsKey(h)) return memo.get(h);

        int res = dfs(u + 1, a, b) + dfs(u + 1, a * nums[u], b) + dfs(u + 1, a, b * nums[u]);
        memo.put(h, res);
        return res;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
/**
 * 为了分析有多少个不同的乘积
 * 考虑质因数分解，唯一
 */