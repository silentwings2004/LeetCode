package LC3901_4200;
import java.util.*;
public class LC4079_PrimeSubsetSelectionI {
    /**
     * You are given two integers n and s.
     *
     * Consider all prime numbers less than or equal to n. Select some of these primes such that:
     *
     * The sum of the selected primes is less than or equal to s.
     * The number of selected primes is maximized.
     * Each prime can be selected at most once.
     * Return an integer array containing the selected primes in increasing order. If multiple optimal selections exist,
     * return any of them.
     *
     * If no prime can be selected, return an empty array.
     *
     * Input: n = 7, s = 20
     * Output: [2,3,5,7]
     *
     * Input: n = 15, s = 15
     * Output: [3,5,7]
     *
     * Input: n = 5, s = 1
     * Output: []
     *
     * Constraints:
     *
     * 1 <= n <= 10^6
     * 1 <= s <= 10^9
     * @param n
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public List<Integer> maxPrimes(int n, int s) {
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

        List<Integer> res = new ArrayList<>();
        for (int i = 0, v = 0; i < cnt; i++) {
            int x = primes[i];
            if (v + x <= s) {
                v += x;
                res.add(x);
            } else break;
        }
        return res;
    }
}