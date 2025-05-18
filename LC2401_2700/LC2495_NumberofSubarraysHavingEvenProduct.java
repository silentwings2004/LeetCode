package LC2401_2700;
import java.util.*;
public class LC2495_NumberofSubarraysHavingEvenProduct {
    /**
     * Given a 0-indexed integer array nums, return the number of
     * subarrays
     *  of nums having an even product.
     *
     * Input: nums = [9,6,7,13]
     * Output: 6
     *
     * Input: nums = [7,3,5]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public long evenProduct(int[] nums) {
        int n = nums.length;
        long[] f = new long[n];
        long res = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 1) f[i] = i == 0 ? 0 : f[i - 1];
            else f[i] = i + 1;
            res += f[i];
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public long evenProduct2(int[] nums) {
        int n = nums.length, s = 0;
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) s = i + 1;
            res += s;
        }
        return res;
    }
}
/**
 * For the product of a sequence of whole numbers (nums[i] >= 1) to be even,
 * there must be at least one even number.
 */