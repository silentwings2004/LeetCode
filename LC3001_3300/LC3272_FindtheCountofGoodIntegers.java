package LC3001_3300;
import java.util.*;
public class LC3272_FindtheCountofGoodIntegers {
    /**
     * You are given two positive integers n and k.
     *
     * An integer x is called k-palindromic if:
     *
     * x is a palindrome.
     * x is divisible by k.
     *
     * An integer is called good if its digits can be rearranged to form a k-palindromic integer. For example, for
     * k = 2, 2020 can be rearranged to form the k-palindromic integer 2002, whereas 1010 cannot be rearranged to form
     * a k-palindromic integer.
     *
     * Return the count of good integers containing n digits.
     *
     * Note that any integer must not have leading zeros, neither before nor after rearrangement. For example, 1010
     * cannot be rearranged to form 101.
     *
     * Input: n = 3, k = 5
     * Output: 27
     *
     * Input: n = 1, k = 4
     * Output: 2
     *
     * Input: n = 5, k = 6
     * Output: 2468
     *
     * Constraints:
     *
     * 1 <= n <= 10
     * 1 <= k <= 9
     * @param n
     * @param k
     * @return
     */
    // time = O(10^m * nlogn), space = O(10^m * n), m = n / 2
    int n, k;
    long res;
    long[] p;
    HashSet<String> set;
    public long countGoodIntegers(int n, int k) {
        this.n = n;
        this.k = k;
        res = 0;
        p = new long[n];
        p[0] = 1;
        for (int i = 1; i < n; i++) p[i] = p[i - 1] * 10;
        int[] cnt = new int[10];
        set = new HashSet<>();
        dfs(0, 0, cnt);

        long[] f = new long[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) f[i] = f[i - 1] * i;

        long res = 0;
        for (String s : set) {
            String[] strs = s.split("#");
            long v = f[n];
            for (int i = 0; i < 10; i++) {
                int x = Integer.parseInt(strs[i]);
                v /= f[x];
            }
            res += v;
            if (strs[0].equals("0")) continue;
            v = f[n - 1];
            for (int i = 0; i < 10; i++) {
                int x = Integer.parseInt(strs[i]);
                int t = i == 0 ? x - 1 : x;
                v /= f[t];
            }
            res -= v;
        }
        return res;
    }

    private void dfs(int u, long sum, int[] cnt) {
        if (u == (n + 1) / 2) {
            if (sum % k != 0) return;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                sb.append(cnt[i]).append('#');
            }
            set.add(sb.toString());
            return;
        }

        for (int i = 9; i >= (u == 0 ? 1 : 0); i--) {
            if (n - 1 - u == u) {
                cnt[i]++;
                dfs(u + 1, sum + i * p[n - 1 - u], cnt);
                cnt[i]--;
            } else {
                cnt[i] += 2;
                dfs(u + 1, sum + i * p[n - 1 - u] + i * p[u], cnt);
                cnt[i] -= 2;
            }
        }
    }

    // S2
    // time = O(10^m * nlogn), space = O(10^m * n), m = (n - 1) / 2
    public long countGoodIntegers2(int n, int k) {
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) f[i] = f[i - 1] * i;

        long res = 0;
        int base = (int) Math.pow(10, (n - 1) / 2);
        HashSet<String> set = new HashSet<>();
        for (int i = base; i < 10 * base; i++) {
            String s = String.valueOf(i);
            String t = new StringBuilder(s).reverse().toString().substring(n % 2);
            s += t;
            if (Long.parseLong(s) % k != 0) continue;
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String ns = String.valueOf(chars);
            if (!set.add(ns)) continue;
            int[] cnt = new int[10];
            for (char c : chars) cnt[c - '0']++;
            long v = 1L * (n - cnt[0]) * f[n - 1];
            for (int x : cnt) v /= f[x];
            res += v;
        }
        return res;
    }
}
/**
 * 1. 枚举所有的长为 n 的回文数(枚举左半边)
 * 2. 如果回文数可以被 k 整除
 *    1. 怎么计算有多少个与之对应的好整数 (重排后的数字)
 *    2. 如何不重不漏的统计，重点在于不重复
 *
 */