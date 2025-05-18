package LC3001_3300;
import java.util.*;
public class LC3144_MinimumSubstringPartitionofEqualCharacterFrequency {
    /**
     * Given a string s, you need to partition it into one or more balanced substrings. For example, if s == "ababcc"
     * then ("abab", "c", "c"), ("ab", "abc", "c"), and ("ababcc") are all valid partitions, but ("a", "bab", "cc"),
     * ("aba", "bc", "c"), and ("ab", "abcc") are not. The unbalanced substrings are bolded.
     *
     * Return the minimum number of substrings that you can partition s into.
     *
     * Note: A balanced string is a string where each character in the string occurs the same number of times.
     *
     * Input: s = "fabccddg"
     * Output: 3
     *
     * Input: s = "abababaccddb"
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= s.length <= 1000
     * s consists only of English lowercase letters.
     * @param s
     * @return
     */
    // S1
    // time = O(n^2 * 26), space = O(n)
    public int minimumSubstringsInPartition(String s) {
        int n = s.length(), inf = 0x3f3f3f3f;
        int[] f = new int[n + 1];
        Arrays.fill(f, inf);
        f[0] = 0;

        int[][] cnt = new int[n + 1][26];
        for (int i = 1; i <= n; i++) {
            cnt[i] = cnt[i - 1].clone();
            int u = s.charAt(i - 1) - 'a';
            cnt[i][u]++;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                boolean flag = true;
                int t = -1;
                for (int k = 0; k < 26; k++) {
                    int v = cnt[i][k] - cnt[j][k];
                    if (v == 0) continue;
                    if (t == -1) t = v;
                    else if (t != v) {
                        flag = false;
                        break;
                    }
                }
                if (!flag) continue;
                f[i] = Math.min(f[i], f[j] + 1);
            }
        }
        return f[n];
    }

    // S2
    // time = O(n^2), space = O(n)
    public int minimumSubstringsInPartition2(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        for (int i = 0; i < n; i++) {
            int[] cnt = new int[26];
            int t = 0, mc = 0;
            for (int j = i; j >= 0; j--) {
                int u = s.charAt(j) - 'a';
                t += cnt[u]++ == 0 ? 1 : 0;
                mc = Math.max(mc, cnt[u]);
                if (i - j + 1 == t * mc) f[i + 1] = Math.min(f[i + 1], f[j] + 1);
            }
        }
        return f[n];
    }
}