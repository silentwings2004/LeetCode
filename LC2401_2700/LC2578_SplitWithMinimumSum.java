package LC2401_2700;
import java.util.*;
public class LC2578_SplitWithMinimumSum {
    /**
     * Given a positive integer num, split it into two non-negative integers num1 and num2 such that:
     *
     * The concatenation of num1 and num2 is a permutation of num.
     * In other words, the sum of the number of occurrences of each digit in num1 and num2 is equal to the number of
     * occurrences of that digit in num.
     * num1 and num2 can contain leading zeros.
     * Return the minimum possible sum of num1 and num2.
     *
     * Notes:
     *
     * It is guaranteed that num does not contain any leading zeros.
     * The order of occurrence of the digits in num1 and num2 may differ from the order of occurrence of num.
     *
     * Input: num = 4325
     * Output: 59
     *
     * Input: num = 687
     * Output: 75
     *
     * Constraints:
     *
     * 10 <= num <= 10^9
     * @param num
     * @return
     */
    // time = O(klogk), space = O(k)
    public int splitNum(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        Arrays.sort(chars);
        int n = chars.length, a = 0, b = 0;
        for (int i = 0; i < n; i += 2) {
            a = a * 10 + (chars[i] - '0');
            if (i + 1 < n) b = b * 10 + (chars[i + 1] - '0');
        }
        return a + b;
    }
}