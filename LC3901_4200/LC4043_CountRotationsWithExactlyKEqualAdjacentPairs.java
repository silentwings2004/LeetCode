package LC3901_4200;

public class LC4043_CountRotationsWithExactlyKEqualAdjacentPairs {
    /**
     * You are given a string s of length n and an integer k.
     *
     * A cyclic rotation of s is obtained by choosing a prefix of s whose length is between 0 and n - 1 (inclusive),
     * and moving it to the end of the string while preserving the order of all characters.
     *
     * For every cyclic rotation of s, let its score be the number of indices i such that 0 <= i < n - 1 and the
     * characters at positions i and i + 1 are equal.
     *
     * Return the number of cyclic rotations of s whose score equals k.
     *
     * A prefix of a string is a substring that starts from the beginning of the string and extends to any point within
     * it.
     *
     * A substring is a contiguous sequence of characters within a string, which may be empty.
     *
     * Input: s = "aab", k = 1
     * Output: 2
     *
     * Input: s = "abca", k = 0
     * Output: 1
     *
     * Constraints:
     *
     * 2 <= n == s.length <= 100
     * s only consists of lowercase English letters.
     * 0 <= k <= n - 1
     * @param s
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int countRotations(String s, int k) {
        int n = s.length(), v = 0;
        for (int i = 0; i + 1 < n; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) v++;
        }

        int res = v == k ? 1 : 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) v--;
            int u = (i - 2 + n) % n;
            if (s.charAt(u) == s.charAt(i - 1)) v++;
            if (v == k) res++;
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int countRotations2(String s, int k) {
        int n = s.length();
        int c = s.charAt(0) == s.charAt(n - 1) ? 1 : 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i - 1) == s.charAt(i)) c++;
        }
        if (c == k) return n - c;
        if (c == k + 1) return c;
        return 0;
    }
}