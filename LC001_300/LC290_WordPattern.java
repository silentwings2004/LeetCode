package LC001_300;
import java.util.*;
public class LC290_WordPattern {
    /**
     * Given a pattern and a string s, find if s follows the same pattern.
     *
     * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word
     * in s.
     *
     * Input: pattern = "abba", s = "dog cat cat dog"
     * Output: true
     *
     * Constraints:
     *
     * 1 <= pattern.length <= 300
     * pattern contains only lower-case English letters.
     * 1 <= s.length <= 3000
     * s contains only lower-case English letters and spaces ' '.
     * s does not contain any leading or trailing spaces.
     * All the words in s are separated by a single space.
     * @param pattern
     * @param s
     * @return
     */
    // S1: one hashmap
    // time = O(n^2), space = O(n)
    public boolean wordPattern(String pattern, String s) {
        HashMap<Character, String> map = new HashMap<>();
        String[] strs = s.split(" ");
        if (pattern.length() != strs.length) return false;

        int n = pattern.length();
        for (int i = 0; i < n; i++) {
            char c = pattern.charAt(i);
            if (!map.containsKey(c)) {
                if (map.containsValue(strs[i])) return false;
                map.put(c, strs[i]);
            } else if (!map.get(c).equals(strs[i])) return false;
        }
        return true;
    }

    // S2: two hashmap
    // time = O(n), space = O(n)
    public boolean wordPattern2(String pattern, String s) {
        String[] strs = s.split(" ");
        int n = pattern.length(), m = strs.length;
        if (n != m) return false;
        HashMap<Character, String> cs = new HashMap<>();
        HashMap<String, Character> sc = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = pattern.charAt(i);
            String b = strs[i];
            if (cs.containsKey(c) && !cs.get(c).equals(b)) return false;
            if (sc.containsKey(b) && sc.get(b) != c) return false;
            cs.put(c, b);
            sc.put(b, c);
        }
        return true;
    }
}
/**
 * 对于映射 f: A -> B
 * f 既是单射，又是满射
 */