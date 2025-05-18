package LC1201_1500;
import java.util.*;
public class LC1250_CheckIfItIsaGoodArray {
    /**
     * iven an array nums of positive integers. Your task is to select some subset of nums, multiply each element by
     * an integer and add all these numbers. The array is said to be good if you can obtain a sum of 1 from the array by
     * any possible subset and multiplicand.
     *
     * Return True if the array is good otherwise return False.
     *
     * Input: nums = [12,5,7,23]
     * Output: true
     *
     * Input: nums = [29,6,10]
     * Output: true
     *
     * Input: nums = [3,6]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(1)
    public boolean isGoodArray(int[] nums) {
        int res = 0;
        for (int x : nums) res = gcd(res, x);
        return res == 1;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
/**
 * 裴蜀定理的扩展
 * a1x1 + a2x2 + ... + anxn = 1 => gcd(a1,a2,...an) = 1
 */