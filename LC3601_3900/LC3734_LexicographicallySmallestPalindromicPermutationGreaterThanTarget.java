package LC3601_3900;

public class LC3734_LexicographicallySmallestPalindromicPermutationGreaterThanTarget {
    /**
     * You are given two strings s and target, each of length n, consisting of lowercase English letters.
     *
     * Return the lexicographically smallest string that is both a palindromic permutation of s and strictly greater
     * than target. If no such permutation exists, return an empty string.
     *
     * A string a is lexicographically strictly greater than a string b (of the same length) if in the first position
     * where a and b differ, string a has a letter that appears later in the alphabet than the corresponding letter in b.
     *
     * A permutation is a rearrangement of all the characters of a string.
     *
     * A string is palindromic if it reads the same forward and backward.
     *
     * Input: s = "baba", target = "abba"
     * Output: "baab"
     *
     * Input: s = "baba", target = "bbaa"
     * Output: ""
     *
     * Input: s = "abc", target = "abb"
     * Output: ""
     *
     * Input: s = "aac", target = "abb"
     * Output: "aca"
     *
     * Constraints:
     *
     * 1 <= n == s.length == target.length <= 300
     * s and target consist of only lowercase English letters.
     * @param s
     * @param target
     * @return
     */
    // time = O(n * 26^(n / 2)), space = O(n)
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            cnt[u]++;
        }

        int odd = 0, midx = -1;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 == 1) {
                odd++;
                midx = i;
            }
            cnt[i] /= 2;
        }
        if (odd > 1) return "";
        if (n % 2 == 1) {
            if (odd != 1) return "";
        } else {
            if (odd != 0) return "";
        }
        String res = build(0, n, midx, cnt, new char[n / 2], target);
        return res.equals("#") ? "" : res;
    }

    private String build(int u, int n, int midx, int[] cnt, char[] b, String t) {
        if (u == n / 2) {
            String l = String.valueOf(b);
            String r = new StringBuilder(l).reverse().toString();
            String res = "";
            if (n % 2 == 1) res = l + (char)('a' + midx) + r;
            else res = l + r;
            if (res.compareTo(t) > 0) return res;
            return "#";
        }

        for (int i = 0; i < 26; i++) {
            if (cnt[i] == 0) continue;
            char c = (char)('a' + i);
            if (c < t.charAt(u)) continue;
            b[u] = c;
            cnt[i]--;
            if (c == t.charAt(u)) {
                String res = build(u + 1, n, midx, cnt, b, t);
                if (!res.equals("#")) return res;
            } else {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j <= u; j++) sb.append(b[j]);
                for (int j = 0; j < 26; j++) {
                    for (int k = 0; k < cnt[j]; k++) {
                        sb.append((char)('a' + j));
                    }
                }
                String l = sb.toString();
                String r = new StringBuilder(l).reverse().toString();
                String res = "";
                if (n % 2 == 1) res = l + (char)('a' + midx) + r;
                else res = l + r;
                cnt[i]++;
                return res;
            }
            cnt[i]++;
        }
        return "#";
    }
}