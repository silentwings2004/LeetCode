package LC2700_3000;
import java.util.*;
public class LC2981_FindLongestSpecialSubstringThatOccursThriceI {
    /**
     * You are given a string s that consists of lowercase English letters.
     *
     * A string is called special if it is made up of only a single character. For example, the string "abc" is not
     * special, whereas the strings "ddd", "zz", and "f" are special.
     *
     * Return the length of the longest special substring of s which occurs at least thrice, or -1 if no special
     * substring occurs at least thrice.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: s = "aaaa"
     * Output: 2
     *
     * Input: s = "abcdef"
     * Output: -1
     *
     * Input: s = "abcaba"
     * Output: 1
     *
     * Constraints:
     *
     * 3 <= s.length <= 50
     * s consists of only lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n^3), space = O(n)
    public int maximumLength(String s) {
        int n = s.length();
        for (int len = n - 2; len >= 1; len--) {
            HashMap<String, Integer> map = new HashMap<>();
            for (int i = 0; i + len - 1 < n; i++) {
                if (check(s.substring(i, i + len))) {
                    String t = s.substring(i, i + len);
                    map.put(t, map.getOrDefault(t, 0) + 1);
                }
            }
            for (int v : map.values()) {
                if (v >= 3) return len;
            }
        }
        return -1;
    }

    private boolean check(String s) {
        HashSet<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) set.add(c);
        return set.size() == 1;
    }
}