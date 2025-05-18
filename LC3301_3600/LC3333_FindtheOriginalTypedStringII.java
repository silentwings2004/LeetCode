package LC3301_3600;
import java.util.*;
public class LC3333_FindtheOriginalTypedStringII {
    /**
     * Alice is attempting to type a specific string on her computer. However, she tends to be clumsy and may press a
     * key for too long, resulting in a character being typed multiple times.
     *
     * You are given a string word, which represents the final output displayed on Alice's screen. You are also given a
     * positive integer k.
     *
     * Return the total number of possible original strings that Alice might have intended to type, if she was trying to
     * type a string of size at least k.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Input: word = "aabbccdd", k = 7
     * Output: 5
     *
     * Input: word = "aabbccdd", k = 8
     * Output: 1
     *
     * Input: word = "aaabbb", k = 3
     * Output: 8
     *
     * Constraints:
     *
     * 1 <= word.length <= 5 * 10^5
     * word consists only of lowercase English letters.
     * 1 <= k <= 2000
     * @param word
     * @param k
     * @return
     */
    // time = O(n + m * k), space = O(n + m * k)
    public int possibleStringCount(String word, int k) {
        long mod = (long)(1e9 + 7);
        int n = word.length();
        long res = 1;
        List<Integer> q = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && word.charAt(j) == word.charAt(i)) j++;
            q.add(j - i);
            res = res * (j - i) % mod;
            i = j - 1;
        }

        if (q.size() >= k) return (int)res;
        int m = q.size();
        long[][] f = new long[m + 1][k];
        f[0][0] = 1;
        long[] s = new long[n + 1];
        Arrays.fill(s, 1);
        s[0] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < k; j++) {
                if (j >= q.get(i - 1)) f[i][j] = (s[j] - s[j - q.get(i - 1)] + mod) % mod;
                else f[i][j] = s[j];
            }
            for (int j = 1; j <= k; j++) s[j] = (s[j - 1] + f[i][j - 1]) % mod;
        }

        for (int i = 0; i < k; i++) res = (res - f[m][i] + mod) % mod;
        return (int)res;
    }
}