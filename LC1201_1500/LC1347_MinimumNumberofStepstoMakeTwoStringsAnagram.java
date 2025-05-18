package LC1201_1500;

public class LC1347_MinimumNumberofStepstoMakeTwoStringsAnagram {
    /**
     * You are given two strings of the same length s and t. In one step you can choose any character of t and replace
     * it with another character.
     *
     * Return the minimum number of steps to make t an anagram of s.
     *
     * An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.
     *
     * Input: s = "bab", t = "aba"
     * Output: 1
     *
     * Input: s = "leetcode", t = "practice"
     * Output: 5
     *
     * Input: s = "anagram", t = "mangaar"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 5 * 10^4
     * s.length == t.length
     * s and t consist of lowercase English letters only.
     * @param s
     * @param t
     * @return
     */
    // time = O(n), space = O(1)
    public int minSteps(String s, String t) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
            cnt[t.charAt(i) - 'a']--;
        }
        int res = 0;
        for (int i = 0; i < 26; i++) res += Math.abs(cnt[i]);
        return res / 2;
    }
}