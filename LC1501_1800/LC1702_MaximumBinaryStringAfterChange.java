package LC1501_1800;
import java.util.*;
public class LC1702_MaximumBinaryStringAfterChange {
    /**
     * You are given a binary string binary consisting of only 0's or 1's. You can apply each of the following
     * operations any number of times:
     *
     * Operation 1: If the number contains the substring "00", you can replace it with "10".
     * For example, "00010" -> "10010"
     * Operation 2: If the number contains the substring "10", you can replace it with "01".
     * For example, "00010" -> "00001"
     * Return the maximum binary string you can obtain after any number of operations. Binary string x is greater than
     * binary string y if x's decimal representation is greater than y's decimal representation.
     *
     * Input: binary = "000110"
     * Output: "111011"
     *
     * Input: binary = "01"
     * Output: "01"
     *
     * Constraints:
     *
     * 1 <= binary.length <= 10^5
     * binary consist of '0' and '1'.
     * @param binary
     * @return
     */
    // time = O(n), space = O(n)
    public String maximumBinaryString(String binary) {
        int k = 0, n = binary.length();
        while (k < n && binary.charAt(k) == '1') k++;
        if (k == n) return binary;
        int cnt = 0;
        for (int i = k + 1; i < n; i++) {
            if (binary.charAt(i) == '0') cnt++;
        }
        char[] chars = new char[n];
        Arrays.fill(chars, '1');
        chars[k + cnt] = '0';
        return String.valueOf(chars);
    }
}
