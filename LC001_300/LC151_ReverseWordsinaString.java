package LC001_300;
import java.util.*;
public class LC151_ReverseWordsinaString {
    /**
     * Given an input string s, reverse the order of the words.
     *
     * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
     *
     * Return a string of the words in reverse order concatenated by a single space.
     *
     * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string
     * should only have a single space separating the words. Do not include any extra spaces.
     *
     * Input: s = "the sky is blue"
     * Output: "blue is sky the"
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^4
     * s contains English letters (upper-case and lower-case), digits, and spaces ' '.
     * There is at least one word in s.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String reverseWords(String s) {
        s = s.trim();
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            int j = i - 1;
            while (j >= 0 && s.charAt(j) != ' ') j--;
            sb.append(s.substring(j + 1, i + 1)).append(' ');
            while (j >= 0 && s.charAt(j) == ' ') j--;
            i = j + 1;
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}