package LC1801_2100;

public class LC2027_MinimumMovestoConvertString {
    /**
     * You are given a string s consisting of n characters which are either 'X' or 'O'.
     *
     * A move is defined as selecting three consecutive characters of s and converting them to 'O'. Note that if a
     * move is applied to the character 'O', it will stay the same.
     *
     * Return the minimum number of moves required so that all the characters of s are converted to 'O'.
     *
     * Input: s = "XXX"
     * Output: 1
     *
     * Constraints:
     *
     * 3 <= s.length <= 1000
     * s[i] is either 'X' or 'O'.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int minimumMoves(String s) {
        int n = s.length(), cnt = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'O') continue;
            else {
                cnt++;
                i += 2;
            }
        }
        return cnt;
    }
}