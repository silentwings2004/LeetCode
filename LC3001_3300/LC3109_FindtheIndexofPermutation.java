package LC3001_3300;
import java.util.*;
public class LC3109_FindtheIndexofPermutation {
    /**
     * Given an array perm of length n which is a permutation of [1, 2, ..., n], return the index of perm in the
     * lexicographically sorted
     *  array of all of the permutations of [1, 2, ..., n].
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Input: perm = [1,2]
     * Output: 0
     *
     * Input: perm = [3,1,2]
     *
     * Output: 4
     * Constraints:
     *
     * 1 <= n == perm.length <= 10^5
     * perm is a permutation of [1, 2, ..., n].
     * @param perm
     * @return
     */
    // time = O(nlogn), space = O(n)
    long mod = (long)(1e9 + 7);
    int[] tr;
    int n;
    public int getPermutationIndex(int[] perm) {
        n = perm.length;
        tr = new int[n + 1];
        for (int i = 1; i <= n; i++) add(i, 1);
        long[] f = new long[n];
        f[0] = 1;
        for (int i = 1; i < n; i++) f[i] = f[i - 1] * i % mod;
        long res = 0;
        for (int i = 0; i < n; i++) {
            int x = query(perm[i] - 1);
            res = (res + 1L * x * f[n - 1 - i] % mod) % mod;
            add(perm[i], -1);
        }
        return (int)res;
    }

    private int lowbit(int x) {
        return x & -x;
    }

    private void add(int x, int c) {
        for (int i = x; i <= n; i += lowbit(i)) tr[i] += c;
    }

    private int query(int x) {
        int res = 0;
        for (int i = x; i > 0; i -= lowbit(i)) res += tr[i];
        return res;
    }
}