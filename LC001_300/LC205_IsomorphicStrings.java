package LC001_300;
import java.util.*;
public class LC205_IsomorphicStrings {
    /**
     * Given two strings s and t, determine if they are isomorphic.
     *
     * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
     *
     * All occurrences of a character must be replaced with another character while preserving the order of characters.
     * No two characters may map to the same character, but a character may map to itself.
     *
     * Input: s = "egg", t = "add"
     * Output: true
     *
     * Constraints:
     *
     * 1 <= s.length <= 5 * 10^4
     * t.length == s.length
     * s and t consist of any valid ascii character.
     * @param s
     * @param t
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public boolean isIsomorphic(String s, String t) {
        int m = s.length(), n = t.length();
        if (m != n) return false;

        HashMap<Character, Character> m1 = new HashMap<>();
        HashMap<Character, Character> m2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char a = s.charAt(i), b = t.charAt(i);
            if (m1.containsKey(a) && m1.get(a) != b) return false;
            if (m2.containsKey(b) && m2.get(b) != a) return false;
            m1.put(a, b);
            m2.put(b, a);
        }
        return true;
    }

    // S2
    // time = O(n), space = O(1)
    public boolean isIsomorphic2(String s, String t) {
        int n = s.length(), m = t.length();
        if (n != m) return false;

        char[] f = new char[256];
        char[] g = new char[256];

        for (int i = 0; i < n; i++) {
            char a = s.charAt(i), b = t.charAt(i);
            if (f[a] != g[b]) return false;
            f[a] = g[b] = b;
        }
        return true;
    }
}