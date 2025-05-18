package LC2101_2400;

public class LC2400_NumberofWaystoReachaPositionAfterExactlykSteps {
    /**
     * You are given two positive integers startPos and endPos. Initially, you are standing at position startPos on an
     * infinite number line. With one step, you can move either one position to the left, or one position to the right.
     *
     * Given a positive integer k, return the number of different ways to reach the position endPos starting from
     * startPos, such that you perform exactly k steps. Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Two ways are considered different if the order of the steps made is not exactly the same.
     *
     * Note that the number line includes negative integers.
     *
     * Input: startPos = 1, endPos = 2, k = 3
     * Output: 3
     *
     * Input: startPos = 2, endPos = 5, k = 10
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= startPos, endPos, k <= 1000
     * @param startPos
     * @param endPos
     * @param k
     * @return
     */
    // S1: inv
    // time = O(nlogn), space = O(1)
    public int numberOfWays(int startPos, int endPos, int k) {
        int m = Math.abs(startPos - endPos);
        if ((m - k) % 2 != 0 || k < m) return 0;
        long mod = (long)(1e9 + 7);
        int r = (m + k) / 2;

        long res = 1;
        for (int i = k; i > k - r; i--) res = res * i % mod;
        for (int i = 1; i <= r; i++) res = res * qmi(i, mod - 2, mod) % mod;
        return (int) res;
    }

    private long qmi(long a, long b, long p) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % p;
            a = a * a % p;
            b >>= 1;
        }
        return res;
    }

    // S2
    // time = O(k * n), space = O(k * n)
    final int N = 2010, mod = (int) 1e9 + 7;
    public int numberOfWays2(int startPos, int endPos, int k) {
        int[][] f = new int[1010][N];
        startPos += 500;
        endPos += 500;
        f[0][startPos] = 1;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < N; j++) {
                if (j > 0) f[i][j] = f[i - 1][j - 1];
                if (j + 1 < N) f[i][j] = (f[i][j] + f[i - 1][j + 1]) % mod;
            }
        }
        return f[k][endPos];
    }
}
/**
 * 2维dp
 * 第一维表示当前在哪个位置，第二维表示走了几步
 * r - l = m
 * r + l = k
 * r = (m + k) / 2;
 * C(k, r)
 * C(a, b) = C(a-1,b-1) + C(a-1,b)
 */