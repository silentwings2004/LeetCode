package LC301_600;
import java.util.*;
public class LC371_SumofTwoIntegers {
    /**
     * Given two integers a and b, return the sum of the two integers without using the operators + and -.
     *
     * Input: a = 1, b = 2
     * Output: 3
     *
     * Constraints:
     *
     * -1000 <= a, b <= 1000
     * @param a
     * @param b
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    public int getSum(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;

        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }
        return a;
    }

    // S2
    // time = O(1), space = O(1)
    public int getSum2(int a, int b) {
        if (a == 0) return b;
        int sum = a ^ b, carry = (a & b) << 1;
        return getSum2(carry, sum);
    }
}
/**
 * 0 + 0 = 00  ^
 * 1 + 0 = 01  ^
 * 0 + 1 = 01  ^
 * 1 + 1 = 10  &
 *
 * ((a & b) << 1) + (a ^ b) = a + b
 * a & b == 001, & 出来的结果表示需要进位的地方，将它左移1位得到010
 */