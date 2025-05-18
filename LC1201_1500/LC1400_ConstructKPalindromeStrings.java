package LC1201_1500;

public class LC1400_ConstructKPalindromeStrings {
    /**
     * Given a string s and an integer k, return true if you can use all the characters in s to construct k palindrome
     * strings or false otherwise.
     *
     * Input: s = "annabelle", k = 2
     * Output: true
     *
     * Input: s = "leetcode", k = 3
     * Output: false
     *
     * Input: s = "true", k = 4
     * Output: true
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of lowercase English letters.
     * 1 <= k <= 10^5
     * @param s
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public boolean canConstruct(String s, int k) {
        int n = s.length();
        if (n < k) return false;
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) cnt[s.charAt(i) - 'a']++;
        int res = 0;
        for (int i = 0; i < 26; i++) res += cnt[i] % 2;
        return res <= k;
    }
}