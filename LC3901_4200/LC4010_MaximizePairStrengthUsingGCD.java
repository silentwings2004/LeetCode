package LC3901_4200;
import java.util.*;
public class LC4010_MaximizePairStrengthUsingGCD {
    /**
     * You are given an integer array nums.
     *
     * Choose exactly one pair of distinct indices i and j. The strength of the pair is defined as
     * (nums[i] * nums[j]) / gcd(nums[i], nums[j])2.
     *
     * Return the maximum strength over all possible pairs.
     *
     * The term gcd(a, b) denotes the greatest common divisor of a and b.
     *
     * Input: nums = [2,3,5]
     * Output: 15
     *
     * Input: nums = [4,6,8]
     * Output: 12
     *
     * Input: nums = [3,3]
     * Output: 1
     *
     * Constraints:
     *
     * 2 <= nums.length <= 2000
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n^2 * logU), space = O(1)
    public long maxPairStrength(int[] nums) {
        int n = nums.length;
        long res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int g = gcd(nums[i], nums[j]);
                long v = 1L * nums[i] * nums[j] / (1L * g * g);
                res = Math.max(res, v);
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // S2
    // time = O(n^2 * logU), space = O(1)
    public long maxPairStrength2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long res = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                long mul = 1L * nums[i] * nums[j];
                if (mul <= res) break;
                long g = gcd(nums[i], nums[j]);
                res = Math.max(res, mul / (g * g));
            }
        }
        return res;
    }
}
/**
 * 从大到小枚举数对，如果 nums[i]⋅nums[j] ≤ res，则继续枚举不可能让答案变大，跳出循环。
 */