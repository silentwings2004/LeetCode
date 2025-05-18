package LC1201_1500;
import java.util.*;
public class LC1271_Hexspeak {
    /**
     * A decimal number can be converted to its Hexspeak representation by first converting it to an uppercase
     * hexadecimal string, then replacing all occurrences of the digit '0' with the letter 'O', and the digit '1' with
     * the letter 'I'. Such a representation is valid if and only if it consists only of the letters in the set
     * {'A', 'B', 'C', 'D', 'E', 'F', 'I', 'O'}.
     *
     * Given a string num representing a decimal integer n, return the Hexspeak representation of n if it is valid,
     * otherwise return "ERROR".
     *
     * Input: num = "257"
     * Output: "IOI"
     *
     * Input: num = "3"
     * Output: "ERROR"
     *
     * Constraints:
     *
     * 1 <= num.length <= 12
     * num does not contain leading zeros.
     * num represents an integer in the range [1, 10^12].
     * @param num
     * @return
     */
    // time = O(nlogn), space = O(n)
    public String toHexspeak(String num) {
        long n = Long.parseLong(num);
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            long t = n % 16;
            if (t == 0) sb.append('O');
            else if (t == 1) sb.append('I');
            else if (t >= 10) sb.append((char)('A' + t - 10));
            else return "ERROR";
            n /= 16;
        }
        return sb.reverse().toString();
    }
}