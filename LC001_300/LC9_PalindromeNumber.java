package LC001_300;
import java.util.*;
public class LC9_PalindromeNumber {
    /**
     * Given an integer x, return true if x is palindrome integer.
     *
     * An integer is a palindrome when it reads the same backward as forward. For example, 121 is palindrome while 123
     * is not.
     *
     * Input: x = -121
     * Output: false
     *
     * Constraints:
     *
     * -2^31 <= x <= 2^31 - 1
     * @param x
     * @return
     */
    // time = O(logn), space = O(1)
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        long res = 0, y = x;
        while (x > 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res == y;
    }
}