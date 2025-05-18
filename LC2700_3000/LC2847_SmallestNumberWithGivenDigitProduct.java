package LC2700_3000;
import java.util.*;
public class LC2847_SmallestNumberWithGivenDigitProduct {
    /**
     * Given a positive integer n, return a string representing the smallest positive integer such that the product of
     * its digits is equal to n, or "-1" if no such number exists.
     *
     * Input: n = 105
     * Output: "357"
     *
     * Input: n = 7
     * Output: "7"
     *
     * Input: n = 44
     * Output: "-1"
     *
     * Constraints:
     *
     * 1 <= n <= 10^18
     * @param n
     * @return
     */
    // time = O(logn), space = O(1)
    public String smallestNumber(long n) {
        if (n < 10) return String.valueOf(n);

        int[] cnt = new int[10];
        for (int i = 9; i >= 2; i--) {
            if (n % i == 0) {
                int t = 0;
                while (n % i == 0) {
                    n /= i;
                    t++;
                }
                cnt[i] = t;
            }
        }
        if (n > 1) return "-1";

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < 10; i++) {
            for (int j = 0; j < cnt[i]; j++) sb.append(i);
        }
        return sb.toString();
    }
}