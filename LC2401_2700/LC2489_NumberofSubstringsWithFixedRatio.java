package LC2401_2700;
import java.util.*;
public class LC2489_NumberofSubstringsWithFixedRatio {
    /**
     * You are given a binary string s, and two integers num1 and num2. num1 and num2 are coprime numbers.
     *
     * A ratio substring is a substring of s where the ratio between the number of 0's and the number of 1's in the
     * substring is exactly num1 : num2.
     *
     * For example, if num1 = 2 and num2 = 3, then "01011" and "1110000111" are ratio substrings, while "11000" is not.
     * Return the number of non-empty ratio substrings of s.
     *
     * Note that:
     *
     * A substring is a contiguous sequence of characters within a string.
     * Two values x and y are coprime if gcd(x, y) == 1 where gcd(x, y) is the greatest common divisor of x and y.
     *
     * Input: s = "0110011", num1 = 1, num2 = 2
     * Output: 4
     *
     * Input: s = "10101", num1 = 3, num2 = 1
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * 1 <= num1, num2 <= s.length
     * num1 and num2 are coprime integers.
     * @param s
     * @param num1
     * @param num2
     * @return
     */
    // time = O(n), space = O(n)
    public long fixedRatio(String s, int num1, int num2) {
        int n = s.length(), sum = 0;
        long res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < n; i++) {
            sum += s.charAt(i) == '0' ? 1 : 0;
            int val = (num1 + num2) * sum - (i + 1) * num1;
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        for (int v : map.values()) res += (long) v * (v - 1) / 2;
        return res;
    }
}
