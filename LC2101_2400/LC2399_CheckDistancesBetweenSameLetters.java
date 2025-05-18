package LC2101_2400;

public class LC2399_CheckDistancesBetweenSameLetters {
    /**
     * You are given a 0-indexed string s consisting of only lowercase English letters, where each letter in s appears
     * exactly twice. You are also given a 0-indexed integer array distance of length 26.
     *
     * Each letter in the alphabet is numbered from 0 to 25 (i.e. 'a' -> 0, 'b' -> 1, 'c' -> 2, ... , 'z' -> 25).
     *
     * In a well-spaced string, the number of letters between the two occurrences of the ith letter is distance[i]. If
     * the ith letter does not appear in s, then distance[i] can be ignored.
     *
     * Return true if s is a well-spaced string, otherwise return false.
     *
     * Input: s = "abaccb", distance = [1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
     * Output: true
     *
     * Input: s = "aa", distance = [1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
     * Output: false
     *
     * Constraints:
     *
     * 2 <= s.length <= 52
     * s consists only of lowercase English letters.
     * Each letter appears in s exactly twice.
     * distance.length == 26
     * 0 <= distance[i] <= 50
     * @param s
     * @param distance
     * @return
     */
    // time = O(n), space = O(1)
    public boolean checkDistances(String s, int[] distance) {
        int n = s.length();
        boolean[] st = new boolean[26];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (st[c - 'a']) continue;
            int x = distance[c - 'a'];
            if (i + x + 1 >= n || s.charAt(i + x + 1) != c) return false;
            st[c - 'a'] = true;
        }
        return true;
    }
}
