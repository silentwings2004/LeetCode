package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ValidPerfectSquare
 * Creater: Silentwings
 * Date: May, 2020
 * Description: 367. Valid Perfect Square
 */
public class LC367_ValidPerfectSquare {
    /**
     * Given a positive integer num, write a function which returns True if num is a perfect square else False.
     *
     * Note: Do not use any built-in library function such as sqrt.
     *
     * Example 1:
     *
     * Input: 16
     * Output: true
     * Example 2:
     *
     * Input: 14
     * Output: false
     * @param num
     * @return
     */
    // S1: B.S.
    // time = O(logn), space = O(1)
    public boolean isPerfectSquare(int num) {
        // corner case
        if (num == 1) return true;

        long left = 2, right = num / 2;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long square = mid * mid; // 注意：乘积可能会出界，所以必须要定义为long
            if (square == num) return true;
            if (square < num) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }
}
