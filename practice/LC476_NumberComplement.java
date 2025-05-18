package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: NumberComplement
 * Creater: Silentwings
 * Date: May, 2020
 * Description: 476. Number Complement
 */
public class LC476_NumberComplement {
    /**
     * Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary
     * representation.
     *
     *
     *
     * Example 1:
     *
     * Input: 5
     * Output: 2
     * Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need
     * to output 2.
     *
     *
     * Example 2:
     *
     * Input: 1
     * Output: 0
     * Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to
     * output 0.
     *
     *
     * Note:
     *
     * The given integer is guaranteed to fit within the range of a 32-bit signed integer.
     * You could assume no leading zero bit in the integer’s binary representation.
     * This question is the same as 1009: https://leetcode.com/problems/complement-of-base-10-integer/
     * @param num
     * @return
     */
    // time = O(1), space = O(1)
    public int findComplement(int num) {
        int res = 0;
        int count = 0;
        while (num != 0) { // 一旦有效位都被右移出去而只剩leading zero了，num == 0跳出了while loop。
            if ((num & 1) == 0) res += Math.pow(2.0, count);
            count++;
            num >>= 1;
        }
        return res;
    }
}
