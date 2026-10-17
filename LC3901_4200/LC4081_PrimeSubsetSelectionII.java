package LC3901_4200;
import java.util.*;
public class LC4081_PrimeSubsetSelectionII {
    /**
     * You are given two integers n and s.
     *
     * Consider all prime numbers less than or equal to n. Select a subset of these primes such that:
     *
     * Each prime can be selected at most once.
     * The sum of the selected primes is as large as possible while remaining less than or equal to s.
     * Among subsets with the maximum sum, select one containing the fewest primes.
     * If multiple subsets remain tied, select the one whose elements, written in increasing order, form the
     * lexicographically smallest array.
     * Return an integer array containing the selected primes in increasing order. If no valid non-empty subset exists,
     * return an empty array.
     *
     * Input: n = 7, s = 20
     * Output: [2, 3, 5, 7]
     *
     * Input: n = 15, s = 18
     * Output: [5,13]
     *
     * Input: n = 5, s = 1
     * Output: []
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     * 1 <= s <= 1000
     * @param n
     * @param s
     * @return
     */
    // time = O(n + s * n / logn), space = O(n * s / logn)
    public List<Integer> maxPrimeSubset(int n, int s) {
        int[] primes = new int[n + 1];
        boolean[] st = new boolean[n + 1];
        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (!st[i]) primes[cnt++] = i;
            for (int j = 0; i * primes[j] <= n; j++) {
                st[i * primes[j]] = true;
                if (i % primes[j] == 0) break;
            }
        }

        final int inf = 0x3f3f3f3f;
        int m = cnt;
        int[][] f = new int[m + 1][s + 1];
        for (int i = 0; i <= m; i++) Arrays.fill(f[i], inf);
        f[m][0] = 0;

        for (int i = m - 1; i >= 0; i--) {
            int x = primes[i];
            for (int j = 0; j <= s; j++) {
                f[i][j] = f[i + 1][j];
                if (j >= x && f[i + 1][j - x] != inf) {
                    f[i][j] = Math.min(f[i][j], f[i + 1][j - x] + 1);
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        int t = s;
        while (t > 0 && f[0][t] == inf) t--;
        if (t == 0) return res;

        int v = f[0][t], rem = t;
        for (int i = 0; i < cnt && rem > 0; i++) {
            int x = primes[i];
            if (x <= rem && f[i + 1][rem - x] == v - 1) {
                res.add(x);
                rem -= x;
                v--;
            }
        }
        return res;
    }
}
/**
 * 0-1 背包：恰好型
 * from[i][j] = (i+1,j) or (i+1,j-ai)
 *
 */