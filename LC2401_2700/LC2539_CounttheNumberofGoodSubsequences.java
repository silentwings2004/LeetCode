package LC2401_2700;
import java.util.*;
public class LC2539_CounttheNumberofGoodSubsequences {
    /**
     * A subsequence of a string is good if it is not empty and the frequency of each one of its characters is the same.
     *
     * Given a string s, return the number of good subsequences of s. Since the answer may be too large, return it
     * modulo 10^9 + 7.
     *
     * A subsequence is a string that can be derived from another string by deleting some or no characters without
     * changing the order of the remaining characters.
     *
     * Input: s = "aabb"
     * Output: 11
     *
     * Input: s = "leet"
     * Output: 12
     *
     * Input: s = "abcd"
     * Output: 15
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^4
     * s consists of only lowercase English letters.
     * @param s
     * @return
     */
    // time = O(26n + NlogN), space = O(N)
    final int N = 10010;
    long mod = (long)(1e9 + 7);
    long[] f, g;
    public int countGoodSubsequences(String s) {
        int n = s.length();
        f = new long[N];
        g = new long[N];
        f[0] = g[0] = 1;
        for (int i = 1; i < N; i++) {
            f[i] = f[i - 1] * i % mod;
            g[i] = g[i - 1] * qmi(i, mod - 2, mod) % mod;
        }

        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) cnt[s.charAt(i) - 'a']++;

        long res = 0;
        for (int i = 1; i <= n; i++) {
            long t = 1;
            for (int j = 0; j < 26; j++) {
                if (cnt[j] >= i) t = t * (C(cnt[j], i) + 1) % mod; // + 1 -> j不选的情况
            }
            res = (res + t - 1) % mod; // 去掉全不选的情况
        }
        return (int) res;
    }

    private long C(int a, int b) {
        return f[a] * g[b] % mod * g[a - b] % mod;
    }

    private long qmi(long a, long k, long p) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % p;
            a = a * a % p;
            k >>= 1;
        }
        return res;
    }
}