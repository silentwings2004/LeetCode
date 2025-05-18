package LC1801_2100;
import java.util.*;
public class LC1830_MinimumNumberofOperationstoMakeStringSorted {
    /**
     * ou are given a string s (0-indexed). You are asked to perform the following operation on s until you get a sorted
     * string:
     *
     * Find the largest index i such that 1 <= i < s.length and s[i] < s[i - 1].
     * Find the largest index j such that i <= j < s.length and s[k] < s[i - 1] for all the possible values of k in the
     * range [i, j] inclusive.
     * Swap the two characters at indices i - 1 and j.
     * Reverse the suffix starting at index i.
     * Return the number of operations needed to make the string sorted. Since the answer can be too large, return it m
     * odulo 10^9 + 7.
     *
     * Input: s = "cba"
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= s.length <= 3000
     * s consists only of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    long mod = (long)(1e9 + 7);
    public int makeStringSorted(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) cnt[s.charAt(i) - 'a']++;

        long[] fact = new long[3001];
        fact[0] = 1;
        for (int i = 1; i <= 3000; i++) fact[i] = fact[i - 1] * i % mod;

        long res = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int countSmaller = 0;
            for (int k = 0; k < 26; k++) {
                if (k < c - 'a') countSmaller += cnt[k];
                else break;
            }
            // 求阶乘，后面有多少位
            long ans = countSmaller * fact[n - 1 - i] % mod;

            // 去重
            for (int k = 0; k < 26; k++) {
                ans = ans * inv(fact[cnt[k]]) % mod;
            }
            res = (res + ans) % mod;
            cnt[c - 'a']--;
        }
        return (int) res;
    }

    private long inv(long x) {
        long s = 1;
        for (; x > 1; x = mod % x) {
            s = s * (mod - mod / x) % mod;
        }
        return s;
    }

    // S2
    final int N = 3010, M = (int) 1e9 + 7;
    long[] f, g;
    public int makeStringSorted2(String s) {
        f = new long[N];
        g = new long[N];
        f[0] = g[0] = 1;

        int n = s.length();
        for (int i = 1; i <= n; i++) {
            f[i] = f[i - 1] * i % M;
            g[i] = qmi(f[i], M - 2);
        }

        int res = 0;
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;
        for (char c : s.toCharArray()) {
            int x = c - 'a';
            for (int i = 0; i < x; i++) {
                if (cnt[i] == 0) continue;
                cnt[i]--;
                res = (res + get(cnt)) % M;
                cnt[i]++;
            }
            cnt[x]--;
        }
        return res;
    }

    private int get(int[] cnt) {
        int sum = 0;
        for (int i = 0; i < 26; i++) sum += cnt[i];
        long res = f[sum];
        for (int i = 0; i < 26; i++) {
            res = res * g[cnt[i]] % M;
        }
        return (int) res;
    }

    private long qmi(long a, int b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % M;
            a = a * a % M;
            b >>= 1;
        }
        return res;
    }
}
/**
 * 12412357
 * 12 4   1    2  3  5 7  =>  1 2 3     1  2  4  5 7  => 1 2 3 | 7 5 4 2 1
 *  i - 1 i       j             i - 1   i     j
 *
 *  previous permutation
 *
 *  next permutation (LC31)
 *
 *  8!  -> 贪心找一个稍微比它大一点点的数，尽量保持高位，低位大一点点
 *  123 | 75421 => 不可能保持前3位不动，第3位必须要改 -> 稍微大一点点，找一个比3大一点的数字 3 -> 4
 *  1 2 4 | 1 2 3 5 7   后5位升序，都会变大 -> 只能改动第3位
 *  1 2 3 | 7 5 4 2 1 (倒序)
 *  how many permutations are smaller than s ?  问 s 是第几个permutation => LC60
 *
 *  38xxxxx
 *  Axxxxxx
 *  3Bxxxxx
 *  38Cxxxx
 *  .....
 *  最后就是跟它一模一样
 *  b * 5! / k!  how many elements smaller than 8
 *
 *  1xxxxxx
 *  Axxxxxx
 *
 *  a * 6！满足 A < 3  how many elements smaller than 3
 *  A221332 重复数，内部全排列都重复数了 => a * 6! / 3! / 2!
 *
 *  互异的permutation并没有这么多
 *  这题是反过来操作，找的就是previous permutation
 *
 *  (a * b) % mod = a % mod * b % mod
 *  (a / b) % mod = a % mod / b % mod
 *  (a / b) % mod = a % mod * inv(b) % mod  逆元 给一个算一个
 *
 *  求小于当前排列的第一个排列
 *  本质求当前排列的排名是多少
 *  康拓展开，数位dp
 *  重复排列问题
 *  n1个a， n2个b, n3个c
 *  n = n1 + n2 + n3
 *  n! / (n1! * n2! * n3!)
 *  n! % mod
 *  (n!)^-1 % mod
 *  费马定理
 *  a^(p-1) % p == 1
 *  a^(p-2)
 */
