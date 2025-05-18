package LC001_300;
import java.util.*;
public class LC242_ValidAnagram {
    /**
     * Given two strings s and t , write a function to determine if t is an anagram of s.
     *
     * Input: s = "anagram", t = "nagaram"
     * Output: true
     *
     * Note:
     * You may assume the string contains only lowercase alphabets.
     *
     * Follow up:
     * What if the inputs contain unicode characters? How would you adapt your solution to such case?
     *
     * @param s
     * @param t
     * @return
     */
    // time = O(n), space = O(1)
    public boolean isAnagram(String s, String t) {
        int m = s.length(), n = t.length();
        if (m != n) return false;
        int[] cs = new int[26], ct = new int[26];
        for (int i = 0; i < n; i++) {
            char c1 = s.charAt(i), c2 = t.charAt(i);
            cs[c1 - 'a']++;
            ct[c2 - 'a']++;
        }

        return Arrays.equals(cs, ct);
    }
}