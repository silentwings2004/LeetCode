package LC2401_2700;
import java.util.*;
public class LC2681_PowerofHeroes {
    /**
     * You are given a 0-indexed integer array nums representing the strength of some heroes. The power of a group of
     * heroes is defined as follows:
     *
     * Let i0, i1, ... ,ik be the indices of the heroes in a group. Then, the power of this group is max(nums[i0],
     * nums[i1], ... ,nums[ik])2 * min(nums[i0], nums[i1], ... ,nums[ik]).
     * Return the sum of the power of all non-empty groups of heroes possible. Since the sum could be very large, return
     * it modulo 10^9 + 7.
     *
     * Input: nums = [2,1,4]
     * Output: 141
     *
     * Input: nums = [1,1,1]
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int sumOfPower(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long sum = 0, res = 0;
        long mod = (long)(1e9 + 7);
        for (int i = 0; i < n; i++) {
            if (i >= 1) sum = (sum * 2 + nums[i - 1]) % mod;
            res += sum * nums[i] % mod * nums[i] % mod + (long)nums[i] * nums[i] % mod * nums[i] % mod;
            res %= mod;
        }
        return (int)res;
    }
}
/**
 * [x y z i] w x x x
 *      j
 * 2^(i-2) 确定最小值
 * i作为最大值的子序列power之和
 * z: z * 2^0 * nums[i] ^2
 * y: y * 2^1 * nums[i] ^2
 * x: x * 2^2 * nums[i] ^2
 *
 * w: w * 2^0
 * x: x * 2^1
 * y: y * 2^2
 * z: z * 2^3
 * sum = sum * 2 + w
 * res += sum * nums[i]^2 + nums[i] * nums[i]^2
 */