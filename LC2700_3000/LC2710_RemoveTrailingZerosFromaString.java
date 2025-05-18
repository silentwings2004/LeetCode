package LC2700_3000;

public class LC2710_RemoveTrailingZerosFromaString {
    /**
     * Given a positive integer num represented as a string, return the integer num without trailing zeros as a string.
     *
     * Input: num = "51230100"
     * Output: "512301"
     *
     * Input: num = "123"
     * Output: "123"
     *
     * Constraints:
     *
     * 1 <= num.length <= 1000
     * num consists of only digits.
     * num doesn't have any leading zeros.
     * @param num
     * @return
     */
    // time = O(n), space = O(1)
    public String removeTrailingZeros(String num) {
        int n = num.length(), i = n - 1;
        while (i >= 0 && num.charAt(i) == '0') i--;
        return num.substring(0, i + 1);
    }
}