package LC3901_4200;
import java.util.*;
public class LC4091_CountMagicalSubstringPairsII {
    /**
     * You are given a string s of length n consisting of lowercase English letters.
     *
     * For every triplet of indices (i, j, k) satisfying 0 <= i <= j < k < n, define:
     *
     * s1 = s[i..j]
     * s2 = s[j + 1..k]
     * A pair of substrings (s1, s2) is called magical if the number of distinct characters in s1 is equal to the number
     * of distinct characters in s2.
     *
     * Return the number of triplets (i, j, k) for which (s1, s2) is a magical pair.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: s = "aba"
     * Output: 2
     *
     * Input: s = "kkk"
     * Output: 4
     *
     * Input: s = "abcac"
     * Output: 7
     *
     * Constraints:
     *
     * 2 <= s.length <= 10^5
     * s consists of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n * 26log26), space = O(27 * n)
    public long countMagicalPairs(String s) {
        int n = s.length();
        int[][] cnt = new int[n][27];
        int[] pos = new int[26];
        Arrays.fill(pos, n);
        for (int i = n - 1; i >= 0; i--) {
            int u = s.charAt(i) - 'a';
            pos[u] = i;
            List<Integer> q = new ArrayList<>();
            for (int j = 0; j < 26; j++) {
                if (pos[j] != n) q.add(pos[j]);
            }
            Collections.sort(q);
            int m = q.size();
            for (int j = 1; j <= m; j++) {
                int l = q.get(j - 1), r = j == m ? n : q.get(j);
                cnt[i][j] = r - l;
            }
        }

        long res = 0;
        Arrays.fill(pos, -1);
        for (int i = 0; i + 1 < n; i++) {
            pos[s.charAt(i) - 'a'] = i;
            List<Integer> q = new ArrayList<>();
            for (int j = 0; j < 26; j++) {
                if (pos[j] != -1) q.add(pos[j]);
            }
            Collections.sort(q);
            int m = q.size();
            for (int j = 1; j <= m; j++) {
                int r = q.get(m - j), l = j == m ? -1 : q.get(m - j - 1);
                int cl = r - l;
                int cr = cnt[i + 1][j];
                res += 1L * cl * cr;
            }
        }
        return res;
    }
}