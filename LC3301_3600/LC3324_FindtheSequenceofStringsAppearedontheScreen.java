package LC3301_3600;
import java.util.*;
public class LC3324_FindtheSequenceofStringsAppearedontheScreen {
    /**
     * You are given a string target.
     *
     * Alice is going to type target on her computer using a special keyboard that has only two keys:
     *
     * Key 1 appends the character "a" to the string on the screen.
     * Key 2 changes the last character of the string on the screen to its next character in the English alphabet. For
     * example, "c" changes to "d" and "z" changes to "a".
     * Note that initially there is an empty string "" on the screen, so she can only press key 1.
     *
     * Return a list of all strings that appear on the screen as Alice types target, in the order they appear, using
     * the minimum key presses.
     *
     * Input: target = "abc"
     * Output: ["a","aa","ab","aba","abb","abc"]
     *
     * Input: target = "he"
     * Output: ["a","b","c","d","e","f","g","h","ha","hb","hc","hd","he"]
     *
     * Constraints:
     * 1 <= target.length <= 400
     * target consists only of lowercase English letters.
     * @param target
     * @return
     */
    // time = O(n), space = O(n)
    public List<String> stringSequence(String target) {
        List<String> res = new ArrayList<>();
        int n = target.length();
        String t = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= target.charAt(i) - 'a'; j++) {
                res.add(t + (char)('a' + j));
            }
            t += target.charAt(i);
        }
        return res;
    }
}