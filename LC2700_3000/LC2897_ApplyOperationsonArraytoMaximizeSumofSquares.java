package LC2700_3000;
import java.util.*;
public class LC2897_ApplyOperationsonArraytoMaximizeSumofSquares {
    /**
     * You are given a 0-indexed integer array nums and a positive integer k.
     *
     * You can do the following operation on the array any number of times:
     *
     * Choose any two distinct indices i and j and simultaneously update the values of nums[i] to (nums[i] AND nums[j])
     * and nums[j] to (nums[i] OR nums[j]). Here, OR denotes the bitwise OR operation, and AND denotes the bitwise
     * AND operation.
     * You have to choose k elements from the final array and calculate the sum of their squares.
     *
     * Return the maximum sum of squares you can achieve.
     *
     * Since the answer can be very large, return it modulo 10^9 + 7.
     *
     * Input: nums = [2,6,5,8], k = 2
     * Output: 261
     *
     * Input: nums = [4,5,4,7], k = 3
     * Output: 90
     *
     * Constraints:
     *
     * 1 <= k <= nums.length <= 10^5
     * 1 <= nums[i] <= 109
     * @param nums
     * @param k
     * @return
     */
    // time = O(nlogK), space = O(logK)
    public int maxSum(List<Integer> nums, int k) {
        int n = nums.size();
        int[] cnt = new int[30];
        for (int x : nums) {
            for (int i = 0; i < 30; i++) {
                if ((x >> i & 1) == 1) cnt[i]++;
            }
        }

        long mod = (long)(1e9 + 7), res = 0;
        while (k-- > 0) {
            long t = 0;
            for (int i = 0; i < 30; i++) {
                if (cnt[i] > 0) {  // 有就拿一个出来，构造一个尽量大的数
                    t += 1L << i;
                    cnt[i]--;
                }
            }
            res = (res + t * t % mod) % mod;
        }
        return (int)res;
    }
}
/**
 * 1.拆位
 * 2. XOR AND OR
 * 3. trie
 *
 * 没办法拆位来做，因为求的是平方之和
 * x = 1010
 * y = 0110
 * => 1110
 *    0010
 * 只对0 1 有影响，对1 1 and 0 0 无影响
 * 把 y 里的 1 挪到 x 里面去
 * 性质：当 x < y 的时候且x有个比特位是1，y有个比特位是0，
 * 那么可以把x中的1移到y中的0上
 * 操作之前：x^2 + y^2
 * 操作之后：(x-d)^2 + (y+d)^2 = x^2+y^2 + 2*d(y-x) + 2 * d^2 > x^2 + y^2
 * => 如果可以操作，就尽量操作
 * 从集合的角度理解，最后小的数对应的集合，一定是大的数对应的集合的子集
 * 直接统计每一位上1的个数
 * 1221 => 可以构造出1111 和 0110 出来
 * 1. 统计每一位上的1
 * 2. 每次构造/组合一个数，使用尽量多的1
 */