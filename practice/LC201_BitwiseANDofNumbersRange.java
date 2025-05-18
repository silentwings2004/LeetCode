package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: BitwiseANDofNumbersRange
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 201. Bitwise AND of Numbers Range
 */
public class LC201_BitwiseANDofNumbersRange {
    /**
     * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range,
     * inclusive.
     *
     * Example 1:
     *
     * Input: [5,7]
     * Output: 4
     *
     * Example 2:
     *
     * Input: [0,1]
     * Output: 0
     * @param m
     * @param n
     * @return
     */
    public int rangeBitwiseAnd(int m, int n) {
        while(n > m) n = n & (n - 1);
        return n;
    }
}
