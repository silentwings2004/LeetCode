package LC3001_3300;

import java.util.HashMap;
import java.util.TreeMap;

public class LC3193_CounttheNumberofInversions {
    /**
     * You are given an integer n and a 2D array requirements, where requirements[i] = [endi, cnti] represents the end
     * index and the inversion count of each requirement.
     *
     * A pair of indices (i, j) from an integer array nums is called an inversion if:
     *
     * i < j and nums[i] > nums[j]
     * Return the number of permutations perm of [0, 1, 2, ..., n - 1] such that for all requirements[i], perm[0..endi]
     * has exactly cnti inversions.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Input: n = 3, requirements = [[2,2],[0,0]]
     * Output: 2
     *
     * Input: n = 3, requirements = [[2,2],[1,1],[0,0]]
     * Output: 1
     *
     * Input: n = 2, requirements = [[0,0],[1,0]]
     * Output: 1
     *
     * Constraints:
     *
     * 2 <= n <= 300
     * 1 <= requirements.length <= n
     * requirements[i] = [endi, cnti]
     * 0 <= endi <= n - 1
     * 0 <= cnti <= 400
     * The input is generated such that there is at least one i such that endi == n - 1.
     * The input is generated such that all endi are unique.
     * @param n
     * @param requirements
     * @return
     */
    // time = O(n^3), space = O(n^3)
    public int numberOfPermutations(int n, int[][] requirements) {
        long mod = (long)(1e9 + 7);
        long[][] f = new long[n + 1][n * (n - 1) + 1];
        f[1][0] = 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] r : requirements) map.put(r[0], r[1]);
        for (int i = 2; i <= n; i++) {
            int limit = i * (i - 1);
            long s = 0;
            for (int j = 0; j <= limit; j++) {
                s += f[i - 1][j];
                if (j - i >= 0) s -= f[i - 1][j - i];
                f[i][j] = s % mod;
            }
            if (map.containsKey(i - 1)) {
                for (int j = 0; j <= limit; j++) {
                    if (j != map.get(i - 1)) f[i][j] = 0;
                }
            }
        }
        long res = 0;
        if (map.containsKey(n - 1)) res = (f[n][map.get(n - 1)] + mod) % mod;
        else {
            for (int i = 0; i <= n * (n - 1); i++) res = (res + f[n][i] + mod) % mod;
        }
        return (int)res;
    }

    // S2
    // time = O(n^3), space = O(n^3)
    public int numberOfPermutations2(int n, int[][] requirements) {
        long mod = (long)(1e9 + 7);
        long[][] f = new long[310][410];
        f[0][0] = 1;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] r : requirements) map.put(r[0], r[1]);

        int l = 0;
        long res = 0;
        for (int i = 1; i <= n; i++) {
            if (map.containsKey(i - 1)) l = map.get(i - 1);
            Integer ck = map.ceilingKey(i - 1);
            int r = map.get(ck);
            for (int j = l; j <= r; j++) {
                for (int k = 0; k <= j; k++) {
                    if (j - k <= i - 1) f[i][j] = (f[i][j] + f[i - 1][k]) % mod;
                }
            }
            if (map.higherKey(i - 1) == null) {
                res = f[i][r] * fact(n - i) % mod;
                break;
            }
        }
        return (int)res;
    }

    private long fact(int x) {
        long res = 1, mod = (long)1e9 + 7;
        for (int i = 1; i <= x; i++) res = res * i % mod;
        return res;
    }
}
/**
 * permutation dp
 * dp[i][j]: from the numbers 1~i elements, the number of valid permutations (i.e. with inverse pair numbers as j)
 * x x x i
 * dp[4] += dp[3]
 * dp[i] = i * dp[i-1]
 * for (int i = 1; i <= n; i++) {
 *     for (int j = a; j <= b; j++) {
 *         for (int k = 0; k <= j; k++) {
 *              if (j - k <= i - 1) {
 *                  dp[i][j] += dp[i-1][k];
 *              }
 *         }
 *     }
 * }
 * return dp[n][?]
 * [x x x i x x x]
 * dp[i][j] => dp[i-1][k]
 * dp[5][5] * 3!
 */