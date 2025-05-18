package LC3001_3300;

public class LC3083_ExistenceofaSubstringinaStringandItsReverse {
    /**
     * Given a string s, find any substring of length 2 which is also present in the reverse of s.
     *
     * Return true if such a substring exists, and false otherwise.
     *
     * Input: s = "leetcode"
     * Output: true
     *
     * Input: s = "abcba"
     * Output: true
     *
     * Input: s = "abcd"
     * Output: false
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s consists only of lowercase English letters.
     * @param s
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public boolean isSubstringPresent(String s) {
        int n = s.length();
        String t = reverse(s);
        for (int i = 0; i + 2 <= n; i++) {
            if (t.contains(s.substring(i, i + 2))) return true;
        }
        return false;
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    // S2
    // time = O(n), space = O(1)
    public boolean isSubstringPresent2(String s) {
        int n = s.length();
        int[] vis = new int[26];
        for (int i = 1; i < s.length(); i++) {
            int x = s.charAt(i - 1) - 'a', y = s.charAt(i) - 'a';
            vis[x] |= 1 << y;
            if ((vis[y] >> x & 1) == 1) return true;
        }
        return false;
    }
}