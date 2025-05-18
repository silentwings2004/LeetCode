package LC1801_2100;

public class LC1819_NumberofDifferentSubsequencesGCDs {
    /**
     * You are given an array nums that consists of positive integers.
     *
     * The GCD of a sequence of numbers is defined as the greatest integer that divides all the numbers in the sequence
     * evenly.
     *
     * For example, the GCD of the sequence [4,6,16] is 2.
     * A subsequence of an array is a sequence that can be formed by removing some elements (possibly none) of the array.
     *
     * For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
     * Return the number of different GCDs among all non-empty subsequences of nums.
     *
     * Input: nums = [6,10,3]
     * Output: 5
     *
     * Input: nums = [5,15,40,5,6]
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 2 * 10^5
     * @param nums
     * @return
     */
    // S1
    // time = O(nlogk), space = O(n)
    final int N = 200010;
    public int countDifferentSubsequenceGCDs(int[] nums) {
        int[] g = new int[N];
        for (int x : nums) { // O(n)
            for (int i = 1; i <= Math.sqrt(x); i++) { // O(logk)
                if (x % i == 0) {
                    if (g[i] == 0) g[i] = x;
                    else g[i] = gcd(g[i], x);
                    if (x / i != i) {
                        if (g[x / i] == 0) g[x / i] = x;
                        else g[x / i] = gcd(g[x / i], x);
                    }
                }
            }
        }

        int count = 0;
        for (int f = 1; f <= 200000; f++) {
            if (f == g[f]) count++;
        }
        return count;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
/**
 * for x = [1: 2e5]
 *   check (if x is the gcd of a subsequence of nums)
 * x = 4
 * nums = [4, 8, 12, 16, ...]
 * 数组里有x的倍数
 * nums = [x, x, 12, 16, ...]
 * for a given x:
 *    find all multipliers of x in nums
 *    check if their gcd is x
 * 2e5 * x
 * nums[1] = a => f1, f2, f3, ...
 * nums[2] = b => f2, f3, f4, ...
 * multiplier[f1] = {a}
 * multiplier[f2] = {a, b}
 * multiplier[f3] = {a, b}
 * multiplier[f4] = {b}
 * gcd_of_all_multiplier[fi] = a
 * gcd_of_all_multiplier[fi] = gcd(gcd_of_all_multiplier[fi], b)
 * gcd_of_all_multiplier[fi] = gcd(gcd_of_all_multiplier[fi], c)
 * gcd_of_all_multiplier[fi] = gcd(gcd_of_all_multiplier[fi], d)
 *                           = gcd(a, b, c, ...)
 */