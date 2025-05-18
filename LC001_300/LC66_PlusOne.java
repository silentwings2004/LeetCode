package LC001_300;
import java.util.*;
public class LC66_PlusOne {
    /**
     * Given a non-empty array of decimal digits representing a non-negative integer, increment one to the integer.
     *
     * The digits are stored such that the most significant digit is at the head of the list, and each element in the
     * array contains a single digit.
     *
     * You may assume the integer does not contain any leading zero, except the number 0 itself.
     *
     * Input: digits = [1,2,3]
     * Output: [1,2,4]
     *
     * Constraints:
     *
     * 1 <= digits.length <= 100
     * 0 <= digits[i] <= 9
     * @param digits
     * @return
     */
    // time = O(n), space = O(n)
    public int[] plusOne(int[] digits) {
        int n = digits.length, t = 1;
        for (int i = n - 1; i >= 0; i--) {
            t += digits[i];
            digits[i] = t % 10;
            t /= 10;
        }
        if (t == 0) return digits;
        int[] res = new int[n + 1];
        res[0] = 1;
        return res;
    }
}