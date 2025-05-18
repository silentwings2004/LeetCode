package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: FirstUniqueCharacterinaString
 * Creater: Silentwings
 * Date: May, 2020
 * Description: 387. First Unique Character in a String
 */
public class LC387_FirstUniqueCharacterinaString {
    /**
     * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist,
     * return -1.
     *
     * Examples:
     *
     * s = "leetcode"
     * return 0.
     *
     * s = "loveleetcode",
     * return 2.
     * Note: You may assume the string contain only lowercase letters.
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        // corner case
        if (s == null || s.length() == 0) return -1;

        int[] idx = new int[26];
        for (char c : s.toCharArray()) idx[c - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            if (idx[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }
}
