package LC3601_3900;
import java.util.*;
public class LC3823_ReverseLettersThenSpecialCharactersinaString {
    /**
     * You are given a string s consisting of lowercase English letters and special characters.
     *
     * Your task is to perform these in order:
     *
     * Reverse the lowercase letters and place them back into the positions originally occupied by letters.
     * Reverse the special characters and place them back into the positions originally occupied by special characters.
     * Return the resulting string after performing the reversals.
     *
     * Input: s = ")ebc#da@f("
     * Output: "(fad@cb#e)"
     *
     * Input: s = "z"
     * Output: "z"
     *
     * Input: s = "!@#$%^&*()"
     * Output: ")(*&^%$#@!"
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s consists only of lowercase English letters and the special characters in "!@#$%^&*()".
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String reverseByType(String s) {
        char[] t = s.toCharArray();
        int n = t.length;
        List<Character> p = new ArrayList<>();
        List<Character> q = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (Character.isLowerCase(t[i])) p.add(t[i]);
            else q.add(t[i]);
        }
        int j = p.size() - 1, k = q.size() - 1;
        for (int i = 0; i < n; i++) {
            if (Character.isLowerCase(t[i])) t[i] = p.get(j--);
            else t[i] = q.get(k--);
        }
        return String.valueOf(t);
    }
}