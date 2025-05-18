package LC3301_3600;

public class LC3517_SmallestPalindromicRearrangementI {
    /**
     * You are given a palindromic string s.
     *
     * Return the lexicographically smallest palindromic permutation of s.
     *
     * A string is palindromic if it reads the same forward and backward.
     *
     * A permutation is a rearrangement of all the characters of a string.
     *
     * A string a is lexicographically smaller than a string b if in the first position where a and b differ, string a
     * has a letter that appears earlier in the alphabet than the corresponding letter in b.
     * If the first min(a.length, b.length) characters do not differ, then the shorter string is the lexicographically
     * smaller one.
     *
     * Input: s = "z"
     * Output: "z"
     *
     * Input: s = "babab"
     * Output: "abbba"
     *
     * Input: s = "daccad"
     * Output: "acddca"
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of lowercase English letters.
     * s is guaranteed to be palindromic.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String smallestPalindrome(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            cnt[u]++;
        }

        StringBuilder sb = new StringBuilder();
        int mid = -1;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] == 0) continue;
            for (int j = 0; j < cnt[i] / 2; j++) {
                sb.append((char)('a' + i));
            }
            if (cnt[i] % 2 == 1) mid = i;
        }
        StringBuilder sb2 = new StringBuilder(sb);
        sb2.reverse();
        if (mid != -1) sb.append((char)(mid + 'a'));
        return sb.append(sb2).toString();
    }
}