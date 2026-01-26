package LC3601_3900;

public class LC3821_FindNthSmallestIntegerWithKOneBits {
    /**
     * You are given two positive integers n and k.
     *
     * Return an integer denoting the nth smallest positive integer that has exactly k ones in its binary
     * representation. It is guaranteed that the answer is strictly less than 250.
     *
     * Input: n = 4, k = 2
     * Output: 9
     *
     * Input: n = 3, k = 1
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= n <= 2^50
     * 1 <= k <= 50
     * The answer is strictly less than 2^50.
     * @param n
     * @param k
     * @return
     */
    // time = O(50^2), space = O(50^2)
    final int N = 51;
    long[][] C;
    public long nthSmallest(long n, int k) {
        C = new long[N][N];
        init();

        long res = 0;
        for (int i = 49; i >= 0 && k > 0; i--) {
            if (C[i][k] < n) {
                n -= C[i][k];
                res |= 1L << i;
                k--;
            }
        }
        return res;
    }

    private void init() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) C[i][j] = 1;
                else C[i][j] = C[i - 1][j] + C[i - 1][j - 1];
            }
        }
    }
}
