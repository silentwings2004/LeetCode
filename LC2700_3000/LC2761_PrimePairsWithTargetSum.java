package LC2700_3000;
import java.util.*;
public class LC2761_PrimePairsWithTargetSum {
    /**
     * You are given an integer n. We say that two integers x and y form a prime number pair if:
     *
     * 1 <= x <= y <= n
     * x + y == n
     * x and y are prime numbers
     * Return the 2D sorted list of prime number pairs [xi, yi]. The list should be sorted in increasing order of xi.
     * If there are no prime number pairs at all, return an empty array.
     *
     * Note: A prime number is a natural number greater than 1 with only two factors, itself and 1.
     *
     * Input: n = 10
     * Output: [[3,7],[5,5]]
     *
     * Input: n = 2
     * Output: []
     *
     * Constraints:
     *
     * 1 <= n <= 10^6
     * @param n
     * @return
     */
    // time = O(nlogn), space = O(n)
    int[] primes;
    boolean[] st;
    int cnt;
    public List<List<Integer>> findPrimePairs(int n) {
        primes = new int[n + 1];
        st = new boolean[n + 1];
        cnt = 0;
        get_primes(n);

        Arrays.sort(primes, 0, cnt);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0, j = cnt - 1; i <= j;) {
            int sum = primes[i] + primes[j];
            if (sum == n) {
                res.add(Arrays.asList(primes[i], primes[j]));
                i++;
                j--;
            }
            else if (sum < n) i++;
            else j--;
        }
        return res;
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