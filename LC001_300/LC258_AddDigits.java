package LC001_300;
import java.util.*;
public class LC258_AddDigits {
    /**
     * Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.
     *
     * Input: num = 38
     * Output: 2
     * Explanation: The process is
     * 38 --> 3 + 8 --> 11
     * 11 --> 1 + 1 --> 2
     * Since 2 has only one digit, return it.
     *
     * Constraints:
     *
     * 0 <= num <= 2^31 - 1
     *
     *
     * Follow up: Could you do it without any loop/recursion in O(1) runtime?
     * @param num
     * @return
     */
    // S1: recursion
    // time = O(1), space = O(1)
    public int addDigits(int num) {
        while (num >= 10) {
            int t = 0;
            while (num > 0) {
                t += num % 10;
                num /= 10;
            }
            num = t;
        }
        return num;
    }

    // S2: Math
    // time = O(1), space = O(1)
    public int addDigits2(int num) {
        if (num == 0) return 0;
        return num % 9 == 0 ? 9 : num % 9;
    }
}
/**
 * f(x) === x (mod 9)
 * 判断下0还是9
 * 最后只要判断下x mod 9 = ？
 * x = 0 => 0
 * x != 0:
 * 1~8 => 1~8
 * 0 => 9
 */