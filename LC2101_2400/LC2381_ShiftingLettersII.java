package LC2101_2400;

public class LC2381_ShiftingLettersII {
    /**
     * You are given a string s of lowercase English letters and a 2D integer array shifts where shifts[i] = [starti,
     * endi, directioni]. For every i, shift the characters in s from the index starti to the index endi (inclusive)
     * forward if directioni = 1, or shift the characters backward if directioni = 0.
     *
     * Shifting a character forward means replacing it with the next letter in the alphabet (wrapping around so that 'z'
     * becomes 'a'). Similarly, shifting a character backward means replacing it with the previous letter in the
     * alphabet (wrapping around so that 'a' becomes 'z').
     *
     * Return the final string after all such shifts to s are applied.
     *
     * Input: s = "abc", shifts = [[0,1,0],[1,2,1],[0,2,1]]
     * Output: "ace"
     *
     * Input: s = "dztz", shifts = [[0,0,0],[1,1,1]]
     * Output: "catz"
     *
     * Constraints:
     *
     * 1 <= s.length, shifts.length <= 5 * 10^4
     * shifts[i].length == 3
     * 0 <= starti <= endi < s.length
     * 0 <= directioni <= 1
     * s consists of lowercase English letters.
     * @param s
     * @param shifts
     * @return
     */
    // time = O(n), space = O(n)
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int[] b = new int[n + 1];
        for (int[] p : shifts) {
            int l = p[0], r = p[1], w = p[2];
            if (w == 0) w = -1;
            b[l] += w;
            b[r + 1] -= w;
        }

        for (int i = 1; i < n; i++) b[i] += b[i - 1];

        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i++) {
            int x = chars[i] - 'a';
            x = (x + b[i] % 26 + 26) % 26;
            chars[i] = (char)('a' + x);
        }
        return String.valueOf(chars);
    }
}
