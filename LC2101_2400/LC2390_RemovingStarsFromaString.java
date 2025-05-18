package LC2101_2400;

public class LC2390_RemovingStarsFromaString {
    /**
     * You are given a string s, which contains stars *.
     *
     * In one operation, you can:
     *
     * Choose a star in s.
     * Remove the closest non-star character to its left, as well as remove the star itself.
     * Return the string after all stars have been removed.
     *
     * Note:
     *
     * The input will be generated such that the operation is always possible.
     * It can be shown that the resulting string will always be unique.
     *
     * Input: s = "leet**cod*e"
     * Output: "lecoe"
     *
     * Input: s = "erase*****"
     * Output: ""
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of lowercase English letters and stars *.
     * The operation above can be performed on s.
     * @param s
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public String removeStars(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c != '*') sb.append(c);
            else sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    // S2:
    // time = O(n), space = O(n)
    public String removeStars2(String s) {
        int n = s.length(), stars = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '*') stars++;
            else if (stars > 0) stars--;
            else sb.append(c);
        }
        return sb.reverse().toString();
    }
}