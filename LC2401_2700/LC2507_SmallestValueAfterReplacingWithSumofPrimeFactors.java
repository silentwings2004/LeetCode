package LC2401_2700;
import java.util.*;
public class LC2507_SmallestValueAfterReplacingWithSumofPrimeFactors {
    /**
     * You are given a positive integer n.
     *
     * Continuously replace n with the sum of its prime factors.
     *
     * Note that if a prime factor divides n multiple times, it should be included in the sum as many times as it
     * divides n.
     * Return the smallest value n will take on.
     *
     * Input: n = 15
     * Output: 5
     *
     * Input: n = 3
     * Output: 3
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * @param n
     * @return
     */
    // S1
    // time = O(logn * sqrt(n)), space = O(1)
    public int smallestValue(int n) {
        while (true) {
            int next = work(n);
            if (next == n) break;
            n = next;
        }
        return n;
    }

    private int work(int n) {
        int res = 0;
        for (int i = 2; i <= n / i; i++) {
            if (n % i == 0) {
                while (n % i == 0) {
                    n /= i;
                    res += i;
                }
            }
        }
        if (n > 1) res += n;
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    final int N = 100010;
    int[] primes;
    boolean[] st;
    int cnt;
    public int smallestValue2(int n) {
        primes = new int[N];
        st = new boolean[N];
        cnt = 0;

        get_primes(n);
        HashSet<Integer> set = new HashSet<>();
        for (int x : primes) set.add(x);

        while (!set.contains(n)) {
            int sum = 0, last = n;
            for (int x : primes) {
                if (x > n) break;
                while (n % x == 0) {
                    sum += x;
                    n /= x;
                }
            }
            n = sum;
            if (n == last) break;
        }
        return n;
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
/**
 * 单调有界序列
 */