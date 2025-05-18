package LC301_600;

public class LC434_NumberofSegmentsinaString {
    /**
     * Given a string s, return the number of segments in the string.
     *
     * A segment is defined to be a contiguous sequence of non-space characters.
     *
     * Input: s = "Hello, my name is John"
     * Output: 5
     *
     * Input: s = "Hello"
     * Output: 1
     *
     * Constraints:
     *
     * 0 <= s.length <= 300
     * s consists of lowercase and uppercase English letters, digits, or one of the following characters
     * "!@#$%^&*()_+-=',.:".
     * The only space character in s is ' '.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int countSegments(String s) {
        int n = s.length(), res = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == ' ') continue;
            int j = i;
            while (j < n && s.charAt(j) != ' ') j++;
            res++;
            i = j - 1;
        }
        return res;
    }
}