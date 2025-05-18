package LC001_300;
import java.util.*;
public class LC58_LengthofLastWord {
    /**
     * Given a string s consists of some words separated by spaces, return the length of the last word in the string.
     * If the last word does not exist, return 0.
     *
     * A word is a maximal substring consisting of non-space characters only.
     *
     * Input: s = "Hello World"
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^4
     * s consists of only English letters and spaces ' '.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int lengthOfLastWord(String s) {
        int n = s.length();
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') continue;
            int j = i - 1;
            while (j >= 0 && s.charAt(j) != ' ') j--;
            return i - j;
        }
        return 0;
    }
}