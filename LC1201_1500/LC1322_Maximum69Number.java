package LC1201_1500;

public class LC1322_Maximum69Number {
    /**
     * You are given a positive integer num consisting only of digits 6 and 9.
     *
     * Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).
     *
     * Input: num = 9669
     * Output: 9969
     *
     * Input: num = 9996
     * Output: 9999
     *
     * Input: num = 9999
     * Output: 9999
     *
     * Constraints:
     *
     * 1 <= num <= 10^4
     * num consists of only 6 and 9 digits.
     * @param num
     * @return
     */
    // time = O(1), space = O(1)
    public int maximum69Number (int num) {
        char[] chars = (num + "").toCharArray();
        int n = chars.length;
        for (int i = 0; i < n; i++) {
            if (chars[i] == '6') {
                chars[i] = '9';
                break;
            }
        }
        return Integer.parseInt(String.valueOf(chars));
    }
}
