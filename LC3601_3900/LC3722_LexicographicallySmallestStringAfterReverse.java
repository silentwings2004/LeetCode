package LC3601_3900;

public class LC3722_LexicographicallySmallestStringAfterReverse {
    /**
     * You are given a string s of length n consisting of lowercase English letters.
     *
     * You must perform exactly one operation by choosing any integer k such that 1 <= k <= n and either:
     *
     * reverse the first k characters of s, or
     * reverse the last k characters of s.
     * Return the lexicographically smallest string that can be obtained after exactly one such operation.
     *
     * A string a is lexicographically smaller than a string b if, at the first position where they differ, a has a
     * letter that appears earlier in the alphabet than the corresponding letter in b. If the first
     * min(a.length, b.length) characters are the same, then the shorter string is considered lexicographically smaller.
     *
     * Input: s = "dcab"
     * Output: "acdb"
     *
     * Input: s = "abba"
     * Output: "aabb"
     *
     * Input: s = "zxy"
     * Output: "xzy"
     *
     * Constraints:
     *
     * 1 <= n == s.length <= 1000
     * s consists of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n^2), space = O(n)
    public String lexSmallest(String s) {
        int n = s.length();
        String res = s;
        for (int k = 1; k <= n; k++) {
            String s1 = revert(s, 0, k) + s.substring(k);
            String s2 = s.substring(0, n - k) + revert(s, n - k, n);
            if (res.compareTo(s1) > 0) res = s1;
            if (res.compareTo(s2) > 0) res = s2;
        }
        return res;
    }

    private String revert(String s, int l, int r) {
        StringBuilder sb = new StringBuilder(s.substring(l, r));
        return sb.reverse().toString();
    }
}