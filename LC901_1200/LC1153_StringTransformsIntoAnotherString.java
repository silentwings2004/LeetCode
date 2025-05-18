package LC901_1200;
import java.util.*;
public class LC1153_StringTransformsIntoAnotherString {
    /**
     * Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing
     * zero or more conversions.
     *
     * In one conversion you can convert all occurrences of one character in str1 to any other lowercase English
     * character.
     *
     * Return true if and only if you can transform str1 into str2.
     *
     * Input: str1 = "aabcc", str2 = "ccdee"
     * Output: true
     *
     * Input: str1 = "leetcode", str2 = "codeleet"
     * Output: false
     *
     * Constraints:
     *
     * 1 <= str1.length == str2.length <= 10^4
     * str1 and str2 contain only lowercase English letters.
     * @param str1
     * @param str2
     * @return
     */
    // time = O(n), space = O(1)
    public boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) return true;
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = str1.length();
        boolean[] st = new boolean[26];
        for (int i = n - 1; i >= 0; i--) {
            int a = str1.charAt(i) - 'a', b = str2.charAt(i) - 'a';
            if (map.getOrDefault(a, b) != b) return false;
            map.put(a, b);
            st[b] = true;
        }
        for (int i = 0; i < 26; i++) {
            if (!st[i]) return true;
        }
        return false;
    }
}
/**
 * whenever the conversion mappings make a cyclic linked list, we must have a temporary character to break the loop.
 * The temporary character should not be present in the string str1;
 * otherwise, the other instances of that character in the string str1 will also change.
 */