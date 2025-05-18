package LC2700_3000;
import java.util.*;
public class LC2954_CounttheNumberofInfectionSequences {
    /**
     * You are given an integer n and a 0-indexed integer array sick which is sorted in increasing order.
     *
     * There are n children standing in a queue with positions 0 to n - 1 assigned to them. The array sick contains the
     * positions of the children who are infected with an infectious disease. An infected child at position i can spread
     * the disease to either of its immediate neighboring children at positions i - 1 and i + 1 if they exist and are
     * currently not infected. At most one child who was previously not infected can get infected with the disease in
     * one second.
     *
     * It can be shown that after a finite number of seconds, all the children in the queue will get infected with the
     * disease. An infection sequence is the sequential order of positions in which all of the non-infected children get
     * infected with the disease. Return the total number of possible infection sequences.
     *
     * Since the answer may be large, return it modulo 109 + 7.
     *
     * Note that an infection sequence does not contain positions of children who were already infected with the disease
     * in the beginning.
     *
     * Input: n = 5, sick = [0,4]
     * Output: 4
     *
     * Input: n = 4, sick = [1]
     * Output: 3
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * 1 <= sick.length <= n - 1
     * 0 <= sick[i] <= n - 1
     * sick is sorted in increasing order.
     * @param n
     * @param sick
     * @return
     */
    // time = O(nlogn), space = O(n)
    final int mod = (int)1e9 + 7;
    long[] f, p;
    public int numberOfSequence(int n, int[] sick) {
        f = new long[n + 1];
        p = new long[n + 1];
        f[0] = p[0] = 1;
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * 2 % mod;
            f[i] = f[i - 1] * i % mod;
        }

        int m = sick.length;
        List<Integer> q = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if (i == 0) {
                if (sick[i] != 0) q.add(sick[i]);
            } else {
                if (sick[i] - sick[i - 1] > 1) q.add(sick[i] - sick[i - 1] - 1);
            }
        }
        if (sick[m - 1] != n - 1) q.add(n - 1 - sick[m - 1]);
        int tot = 0;
        for (int x : q) tot += x;
        long res = f[tot];
        for (int x : q) res = res * inv(f[x]) % mod;
        for (int i = 1; i < m; i++) {
            int x = sick[i] - sick[i - 1] - 1;
            if (x > 0) res = res * p[x - 1] % mod;
        }
        return (int)res;
    }

    private long inv(long x) {
        return qmi(x, mod - 2);
    }

    private long qmi(long a, long k) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            k >>= 1;
        }
        return res;
    }
}