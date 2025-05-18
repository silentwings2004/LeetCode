package LC901_1200;
import java.util.*;
public class LC917_ReverseOnlyLetters {
    /**
     * Given a string s, reverse the string according to the following rules:
     *
     * All the characters that are not English letters remain in the same position.
     * All the English letters (lowercase or uppercase) should be reversed.
     * Return s after reversing it.
     *
     * Input: s = "ab-cd"
     * Output: "dc-ba"
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s consists of characters with ASCII values in the range [33, 122].
     * s does not contain '\"' or '\\'.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String reverseOnlyLetters(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            while (i < j && !Character.isLetter(chars[i])) i++;
            while (i < j && !Character.isLetter(chars[j])) j--;
            char c = chars[i];
            chars[i] = chars[j];
            chars[j] = c;
        }
        return String.valueOf(chars);
    }
}
