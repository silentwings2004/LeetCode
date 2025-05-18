package LC2700_3000;

import java.util.TreeMap;

public class LC2842_CountKSubsequencesofaStringWithMaximumBeauty {
    /**
     * You are given a string s and an integer k.
     *
     * A k-subsequence is a subsequence of s, having length k, and all its characters are unique, i.e., every character
     * occurs once.
     *
     * Let f(c) denote the number of times the character c occurs in s.
     *
     * The beauty of a k-subsequence is the sum of f(c) for every character c in the k-subsequence.
     *
     * For example, consider s = "abbbdd" and k = 2:
     *
     * f('a') = 1, f('b') = 3, f('d') = 2
     * Some k-subsequences of s are:
     * "abbbdd" -> "ab" having a beauty of f('a') + f('b') = 4
     * "abbbdd" -> "ad" having a beauty of f('a') + f('d') = 3
     * "abbbdd" -> "bd" having a beauty of f('b') + f('d') = 5
     * Return an integer denoting the number of k-subsequences whose beauty is the maximum among all k-subsequences.
     * Since the answer may be too large, return it modulo 109 + 7.
     *
     * A subsequence of a string is a new string formed from the original string by deleting some (possibly none) of the
     * characters without disturbing the relative positions of the remaining characters.
     *
     * Notes
     *
     * f(c) is the number of times a character c occurs in s, not a k-subsequence.
     * Two k-subsequences are considered different if one is formed by an index that is not present in the other. So,
     * two k-subsequences may form the same string.
     *
     * Input: s = "bcca", k = 2
     * Output: 4
     *
     * Input: s = "abbcd", k = 4
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= s.length <= 2 * 10^5
     * 1 <= k <= s.length
     * s consists only of lowercase English letters.
     * @param s
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    final int mod = (int)1e9 + 7;
    public int countKSubsequencesWithMaxBeauty(String s, int k) {
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            cnt[c - 'a']++;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) map.put(cnt[i], map.getOrDefault(cnt[i], 0) + 1);
        }

        int[][] c = new int[27][27];
        for (int i = 0; i <= 26; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) c[i][j] = 1;
                else c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % mod;
            }
        }

        long res = 1;
        Integer t = -1;
        while (true) {
            if (t == -1) t = map.lastKey();
            else t = map.lowerKey(t);
            if (t == null) return 0;
            int m = map.get(t);
            if (m <= k) {
                res = res * qmi(t, m) % mod;
                k -= m;
                if (k == 0) break;
            } else {
                res = res * c[m][k] % mod * qmi(t, k) % mod;
                break;
            }
        }
        return (int)res;
    }

    private long qmi(long a, long k) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            k >>= 1;
        }
        return res;
    }
}
/**
 * 剥洋葱
 * k = 1 的时候答案怎么算？
 *
 * k = 2 的时候答案怎么算？
 * abbbdd
 * 3 * 2 = 6  乘法原理
 * C(3,k)
 * 1. 统计每个字符的出现次数
 * 2. 统计出现次数的次数
 * 3. 按照出现次数的次数从大到小枚举
 *
 * 思考方式：从特殊到一般
 */