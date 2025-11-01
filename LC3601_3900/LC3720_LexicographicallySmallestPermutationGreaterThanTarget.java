package LC3601_3900;

public class LC3720_LexicographicallySmallestPermutationGreaterThanTarget {
    /**
     * You are given two strings s and target, both having length n, consisting of lowercase English letters.
     *
     * Return the lexicographically smallest permutation of s that is strictly greater than target. If no permutation
     * of s is lexicographically strictly greater than target, return an empty string.
     *
     * A string a is lexicographically strictly greater than a string b (of the same length) if in the first position
     * where a and b differ, string a has a letter that appears later in the alphabet than the corresponding letter in b.
     *
     * A permutation is a rearrangement of all the characters of a string.
     *
     * Input: s = "abc", target = "bba"
     * Output: "bca"
     *
     * Input: s = "leet", target = "code"
     * Output: "eelt"
     *
     * Input: s = "baba", target = "bbaa"
     * Output: ""
     *
     * Constraints:
     *
     * 1 <= s.length == target.length <= 300
     * s and target consist of only lowercase English letters.
     * @param s
     * @param target
     * @return
     */
    // time = O(n * 26^2), space = O(n)
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            cnt[u]++;
        }

        for (int i = n - 1; i >= 0; i--) {
            String p = target.substring(0, i);
            int[] r = cnt.clone();
            int m = p.length();
            boolean f = true;
            for (int j = 0; j < m; j++) {
                int u = p.charAt(j) - 'a';
                r[u]--;
                if (r[u] < 0) {
                    f = false;
                    break;
                }
            }
            if (!f) continue;
            int u = target.charAt(i) - 'a';
            for (int c = u + 1; c < 26; c++) {
                if (r[c] > 0) {
                    r[c]--;
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < 26; j++) {
                        for (int k = 0; k < r[j]; k++) {
                            sb.append((char)('a' + j));
                        }
                    }
                    return p + (char)(c + 'a') + sb.toString();
                }
            }
        }
        return "";
    }
}
/**
 * 从右往左思考 --> 刚刚好大于
 * s = "abc", t = "bba" -> bb?
 * t'= b??
 */