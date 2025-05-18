package LC301_600;

public class LC557_ReverseWordsinaStringIII {
    /**
     * Given a string s, reverse the order of characters in each word within a sentence while still preserving
     * whitespace and initial word order.
     *
     * Input: s = "Let's take LeetCode contest"
     * Output: "s'teL ekat edoCteeL tsetnoc"
     *
     * Input: s = "God Ding"
     * Output: "doG gniD"
     *
     * Constraints:
     *
     * 1 <= s.length <= 5 * 10^4
     * s contains printable ASCII characters.
     * s does not contain any leading or trailing spaces.
     * There is at least one word in s.
     * All the words in s are separated by a single space.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == ' ') sb.append(c);
            else {
                int j = i;
                while (j < n && s.charAt(j) != ' ') j++;
                for (int k = j - 1; k >= i; k--) sb.append(s.charAt(k));
                i = j - 1;
            }
        }
        return sb.toString();
    }
}
