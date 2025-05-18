package LC301_600;

public class LC409_LongestPalindrome {
    /**
     * Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome
     * that can be built with those letters.
     *
     * Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
     *
     * Input: s = "abccccdd"
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= s.length <= 2000
     * s consists of lowercase and/or uppercase English letters only.
     * @param s
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int longestPalindrome(String s) {
        int[] cnt = new int[128];
        int n = s.length();
        for (int i = 0; i < n; i++) cnt[s.charAt(i)]++;
        int res = 0, odd = 0;
        for (int i = 0; i < 128; i++) {
            if (cnt[i] > 0) {
                if (cnt[i] % 2 == 0) res += cnt[i];
                else {
                    res += cnt[i] - 1;
                    odd++;
                }
            }
        }
        return res + (odd > 0 ? 1 : 0);
    }

    // S2
    // time = O(n), space = O(1)
    public int longestPalindrome2(String s) {
        int[] cnt = new int[128];
        for (char c : s.toCharArray()) cnt[c]++;
        int res = 0;
        for (int i = 0; i < 128; i++) res += cnt[i] / 2 * 2;
        if (res < s.length()) res++; // 中间还可以再加上1个字符
        return res;
    }
}