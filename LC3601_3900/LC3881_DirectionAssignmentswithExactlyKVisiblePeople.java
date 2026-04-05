package LC3601_3900;

public class LC3881_DirectionAssignmentswithExactlyKVisiblePeople {
    /**
     * You are given three integers n, pos, and k.
     *
     * There are n people standing in a line indexed from 0 to n - 1. Each person independently chooses a direction:
     *
     * 'L': visible only to people on their right
     * 'R': visible only to people on their left
     * A person at index pos sees others as follows:
     * A person i < pos is visible if and only if they choose 'L'.
     * A person i > pos is visible if and only if they choose 'R'.
     * Return the number of possible direction assignments such that the person at index pos sees exactly k people.
     *
     * Since the answer may be large, return it modulo 10^9 + 7.
     *
     * Input: n = 3, pos = 1, k = 0
     * Output: 2
     *
     * Input: n = 3, pos = 2, k = 1
     * Output: 4
     *
     * Input: n = 1, pos = 0, k = 0
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * 0 <= pos, k <= n - 1
     * @param n
     * @param pos
     * @param k
     * @return
     */
    // time = O(n * logk), space = O(n)
    long mod = (long)(1e9 + 7);
    public int countVisiblePeople(int n, int pos, int k) {
        long[] f = new long[n + 1];
        long[] g = new long[n + 1];
        f[0] = g[0] = 1;
        for (int i = 1; i <= n; i++) {
            f[i] = f[i - 1] * i % mod;
            g[i] = g[i - 1] * qmi(i, mod - 2) % mod;
        }
        int a = n - 1, b = k;
        return (int)(f[a] * g[b] % mod * g[a - b] % mod * 2 % mod);
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