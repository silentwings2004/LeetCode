package LC3301_3600;

import java.util.HashMap;

public class LC3365_RearrangeKSubstringstoFormTargetString {
    /**
     * You are given two strings s and t, both of which are anagrams of each other, and an integer k.
     *
     * Your task is to determine whether it is possible to split the string s into k equal-sized substrings, rearrange
     * the substrings, and concatenate them in any order to create a new string that matches the given string t.
     *
     * Return true if this is possible, otherwise, return false.
     *
     * An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, using all the
     * original letters exactly once.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: s = "abcd", t = "cdab", k = 2
     * Output: true
     *
     * Input: s = "aabbcc", t = "bbaacc", k = 3
     * Output: true
     *
     * Input: s = "aabbcc", t = "bbaacc", k = 2
     * Output: false
     *
     * Constraints:
     *
     * 1 <= s.length == t.length <= 2 * 10^5
     * 1 <= k <= s.length
     * s.length is divisible by k.
     * s and t consist only of lowercase English letters.
     * The input is generated such that s and t are anagrams of each other.
     * @param s
     * @param t
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public boolean isPossibleToRearrange(String s, String t, int k) {
        int n = s.length(), m = n / k;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i += m) {
            String x = s.substring(i, i + m);
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        for (int i = 0; i < n; i += m) {
            String x = t.substring(i, i + m);
            if (!map.containsKey(x)) return false;
            map.put(x, map.get(x) - 1);
            if (map.get(x) == 0) map.remove(x);
        }
        return true;
    }
}