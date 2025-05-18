package LC1201_1500;

public class LC1422_MaximumScoreAfterSplittingaString {
    /**
     * Given a string s of zeros and ones, return the maximum score after splitting the string into two non-empty
     * substrings (i.e. left substring and right substring).
     *
     * The score after splitting a string is the number of zeros in the left substring plus the number of ones in the
     * right substring.
     *
     * Input: s = "011101"
     * Output: 5
     *
     * Constraints:
     *
     * 2 <= s.length <= 500
     * The string s consists of characters '0' and '1' only.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int maxScore(String s) {
        int n = s.length(), ones = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') ones++;
        }

        int res = 0, zeros = 0;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == '0') zeros++;
            else ones--;
            res = Math.max(res, zeros + ones);
        }
        return res;
    }
}
