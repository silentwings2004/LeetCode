package LC601_900;

public class LC806_NumberofLinesToWriteString {
    /**
     * You are given a string s of lowercase English letters and an array widths denoting how many pixels wide each
     * lowercase English letter is. Specifically, widths[0] is the width of 'a', widths[1] is the width of 'b', and so on.
     *
     * You are trying to write s across several lines, where each line is no longer than 100 pixels. Starting at the
     * beginning of s, write as many letters on the first line such that the total width does not exceed 100 pixels.
     * Then, from where you stopped in s, continue writing as many letters as you can on the second line. Continue this
     * process until you have written all of s.
     *
     * Return an array result of length 2 where:
     *
     * result[0] is the total number of lines.
     * result[1] is the width of the last line in pixels.
     *
     * Input: widths = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10],
     * s = "abcdefghijklmnopqrstuvwxyz"
     * Output: [3,60]
     *
     * Constraints:
     *
     * widths.length == 26
     * 2 <= widths[i] <= 10
     * 1 <= s.length <= 1000
     * s contains only lowercase Engli sh letters.
     * @param widths
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int[] numberOfLines(int[] widths, String s) {
        int r = 0, c = 0;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            int w = widths[u];
            if (c + w > 100) {
                r++;
                c = 0;
            }
            c += w;
        }
        return new int[]{r + 1, c};
    }
}