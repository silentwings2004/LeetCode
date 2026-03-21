package LC3601_3900;
import java.util.*;
public class LC3867_SumofGCDofFormedPairs {
    /**
     * You are given an integer array nums of length n.
     *
     * Construct an array prefixGcd where for each index i:
     *
     * Let mxi = max(nums[0], nums[1], ..., nums[i]).
     * prefixGcd[i] = gcd(nums[i], mxi).
     * After constructing prefixGcd:
     *
     * Sort prefixGcd in non-decreasing order.
     * Form pairs by taking the smallest unpaired element and the largest unpaired element.
     * Repeat this process until no more pairs can be formed.
     * For each formed pair, compute the gcd of the two elements.
     * If n is odd, the middle element in the prefixGcd array remains unpaired and should be ignored.
     * Return an integer denoting the sum of the GCD values of all formed pairs.
     *
     * The term gcd(a, b) denotes the greatest common divisor of a and b.
     *
     * Input: nums = [2,6,4]
     * Output: 2
     *
     * Input: nums = [3,6,2,8]
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn + nlogk), space = O(n)
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] g = new int[n];
        g[0] = nums[0];
        for (int i = 1, mx = nums[0]; i < n; i++) {
            mx = Math.max(mx, nums[i]);
            g[i] = gcd(mx, nums[i]);
        }
        Arrays.sort(g);
        long res = 0;
        for (int i = 0, j = n - 1; i < j; i++, j--) res += gcd(g[i], g[j]);
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}