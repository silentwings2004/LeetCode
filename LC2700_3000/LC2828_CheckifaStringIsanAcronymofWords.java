package LC2700_3000;
import java.util.*;
public class LC2828_CheckifaStringIsanAcronymofWords {
    /**
     * Given an array of strings words and a string s, determine if s is an acronym of words.
     *
     * The string s is considered an acronym of words if it can be formed by concatenating the first character of each
     * string in words in order. For example, "ab" can be formed from ["apple", "banana"], but it can't be formed from
     * ["bear", "aardvark"].
     *
     * Return true if s is an acronym of words, and false otherwise.
     *
     * Input: words = ["alice","bob","charlie"], s = "abc"
     * Output: true
     *
     * Input: words = ["an","apple"], s = "a"
     * Output: false
     *
     * Input: words = ["never","gonna","give","up","on","you"], s = "ngguoy"
     * Output: true
     *
     * Constraints:
     *
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 10
     * 1 <= s.length <= 100
     * words[i] and s consist of lowercase English letters.
     * @param words
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public boolean isAcronym(List<String> words, String s) {
        if (s.length() != words.size()) return false;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != words.get(i).charAt(0)) return false;
        }
        return true;
    }
}