package LC2700_3000;
import java.util.*;
public class LC2864_MaximumOddBinaryNumber {
    /**
     * You are given a binary string s that contains at least one '1'.
     *
     * You have to rearrange the bits in such a way that the resulting binary number is the maximum odd binary number
     * that can be created from this combination.
     *
     * Return a string representing the maximum odd binary number that can be created from the given combination.
     *
     * Note that the resulting string can have leading zeros.
     *
     * Input: s = "010"
     * Output: "001"
     *
     * Input: s = "0101"
     * Output: "1001"
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s consists only of '0' and '1'.
     * s contains at least one '1'.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String maximumOddBinaryNumber(String s) {
        int n = s.length(), cnt = 0;
        char[] chars = new char[n];
        Arrays.fill(chars, '0');
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') cnt++;
        }
        chars[n - 1] = '1';
        for (int i = 0; i < cnt - 1; i++) chars[i] = '1';
        return String.valueOf(chars);
    }
}