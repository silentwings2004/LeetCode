package LC601_900;
import java.util.*;
public class LC625_MinimumFactorization {
    /**
     * Given a positive integer num, return the smallest positive integer x whose multiplication of each digit equals
     * num. If there is no answer or the answer is not fit in 32-bit signed integer, return 0.
     *
     * Input: num = 48
     * Output: 68
     *
     * Input: num = 15
     * Output: 35
     *
     * Constraints:
     *
     * 1 <= num <= 2^31 - 1
     * @param num
     * @return
     */
    // time = O(8logn), space = O(1)
    public int smallestFactorization(int num) {
        if (num == 1) return num;
        long res = 0, mul = 1;
        for (int i = 9; i > 1; i--) {
            while (num % i == 0) {
                num /= i;
                res = mul * i + res;
                mul *= 10;
            }
        }
        return num == 1 && res <= Integer.MAX_VALUE ? (int) res : 0;
    }
}