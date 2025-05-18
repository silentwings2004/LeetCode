package LC3301_3600;
import java.util.*;
public class LC3470_PermutationsIV {
    /**
     * Given two integers, n and k, an alternating permutation is a permutation of the first n positive integers such
     * that no two adjacent elements are both odd or both even.
     *
     * Return the k-th alternating permutation sorted in lexicographical order. If there are fewer than k valid
     * alternating permutations, return an empty list.
     *
     * Input: n = 4, k = 6
     * Output: [3,4,1,2]
     *
     * Input: n = 3, k = 2
     * Output: [3,2,1]
     *
     * Input: n = 2, k = 3
     * Output: []
     *
     * Constraints:
     *
     * 1 <= n <= 100
     * 1 <= k <= 10^15
     * @param n
     * @param k
     * @return
     */
    // time = O(n^2), space = O(n^2)
    final long inf = (long)1E16;
    long[][][] f;
    public int[] permute(int n, long k) {
        int odd = (n + 1) / 2, even = n / 2;
        f = new long[odd + 1][even + 1][3];
        for (int i = 0; i <= odd; i++) {
            for (int j = 0; j <= even; j++) {
                Arrays.fill(f[i][j], -1);
            }
        }
        long tot = dfs(odd, even, 0);
        if (k > tot) return new int[0];
        int[] res = new int[n];
        boolean[] st = new boolean[n + 1];
        int ro = odd, re = even, t = -1;

        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 1; j <= n; j++) {
                if (st[j]) continue;
                if (t != -1) {
                    if ((j % 2 == 0 && t != 0) || (j % 2 == 1 && t != 1)) continue;
                }
                long cnt = 0;
                if (j % 2 == 1) {
                    if (ro <= 0) continue;
                    cnt = dfs(ro - 1, re, 1);
                } else {
                    if (re <= 0) continue;
                    cnt = dfs(ro, re - 1, 2);
                }
                if (k > cnt) k-= cnt;
                else {
                    res[i] = j;
                    st[j] = true;
                    if (j % 2 == 1) {
                        ro--;
                        t = 0;
                    } else {
                        re--;
                        t = 1;
                    }
                    flag = true;
                    break;
                }
            }
            if (!flag) return new int[0];
        }
        return res;
    }

    private long dfs(int odd, int even, int r) {
        if (odd == 0 && even == 0) return 1;
        if (f[odd][even][r] != -1) return f[odd][even][r];

        long cnt = 0;
        if (r == 0) {
            if (odd > 0) cnt += odd * dfs(odd - 1, even, 1);
            if (even > 0) cnt += even * dfs(odd, even - 1, 2);
        } else if (r == 1) {
            if (even > 0) cnt += even * dfs(odd, even - 1, 2);
        } else {
            if (odd > 0) cnt += odd * dfs(odd - 1, even, 1);
        }
        cnt = Math.min(cnt, inf);
        f[odd][even][r] = cnt;
        return cnt;
    }
}