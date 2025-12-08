package LC3601_3900;
import java.util.*;
public class LC3770_LargestPrimefromConsecutivePrimeSum {
    /**
     * You are given an integer n.
     *
     * Return the largest prime number less than or equal to n that can be expressed as the sum of one or more
     * consecutive prime numbers starting from 2. If no such number exists, return 0.
     *
     * Input: n = 20
     * Output: 17
     *
     * Input: n = 2
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n <= 5 * 10^5
     * @param n
     * @return
     */
    // time = O(n), space = O(n)
    int[] primes;
    boolean[] st;
    int cnt;
    public int largestPrime(int n) {
        if (n < 2) return 0;
        primes = new int[n + 1];
        st = new boolean[n + 1];
        cnt = 0;
        get_primes(n);
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < cnt; i++) set.add(primes[i]);
        int res = 0;
        for (int i = 0, s = 0; i < cnt; i++) {
            s += primes[i];
            if (s > n) break;
            if (set.contains(s)) res = s;
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

    // S2
    // time = O(logk), space = O(1) 预处理的时空复杂度为 O(UloglogU)，U = 5 * 10^5, 不计入。
    private static final int N = 500000;
    private static final boolean[] isPrime = new boolean[N + 1];
    private static final List<Integer> prime = new ArrayList<>();
    private static final List<Integer> specialPrimes = new ArrayList<>();
    private static boolean initialized = false;

    private void init() {
        if (initialized) return;
        initialized = true;
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                prime.add(i);
                for (long j = 1L * i * i; j <= N; j += i) {
                    isPrime[(int)j] = false;
                }
            }
        }
        specialPrimes.add(0); // 哨兵
        int sum = 0;
        for (int p : prime) {
            sum += p;
            if (sum > N) break;
            if (isPrime[sum]) specialPrimes.add(sum);
        }
    }

    public int largestPrime2(int n) {
        init();
        int i = Collections.binarySearch(specialPrimes, n + 1);
        if (i < 0) i = ~i;
        return specialPrimes.get(i - 1);
    }
}