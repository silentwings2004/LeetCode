package LC3601_3900;
import java.util.*;
public class LC3610_MinimumNumberofPrimestoSumtoTarget {
    /**
     * You are given two integers n and m.
     *
     * You have to select a multiset of prime numbers from the first m prime numbers such that the sum of the selected
     * primes is exactly n. You may use each prime number multiple times.
     *
     * Return the minimum number of prime numbers needed to sum up to n, or -1 if it is not possible.
     *
     * Input: n = 10, m = 2
     * Output: 4
     *
     * Input: n = 15, m = 5
     * Output: 3
     *
     * Input: n = 7, m = 6
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     * 1 <= m <= 1000
     * @param n
     * @param m
     * @return
     */
    // time = O(n^2), space = O(n)
    final int inf = 0x3f3f3f3f;
    int[] primes;
    boolean[] st;
    int cnt;
    public int minNumberOfPrimes(int n, int m) {
        primes = new int[n + 1];
        st = new boolean[n + 1];
        get_primes(n);
        int[] f = new int[n + 1];
        Arrays.fill(f, inf);
        f[0] = 0;
        for (int i = 0; i < Math.min(m, cnt); i++) {
            for (int j = primes[i]; j <= n; j++) {
                f[j] = Math.min(f[j], f[j - primes[i]] + 1);
            }
        }
        return f[n] == inf ? -1 : f[n];
    }

    private void get_primes(int n) {
        for (int i = 2; i <= n; i++) {
            if (!st[i]) primes[cnt++] = i;
            for (int j = 0; i * primes[j] <= n; j++) {
                st[i * primes[j]] = true;
                if (i % primes[j] == 0) break;
            }
        }
    }
}