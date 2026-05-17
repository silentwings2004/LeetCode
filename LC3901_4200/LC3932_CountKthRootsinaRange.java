package LC3901_4200;

public class LC3932_CountKthRootsinaRange {
    /**
     * You are given three integers l, r, and k.
     *
     * An integer y is said to be a perfect kth power if there exists an integer x such that y = xk.
     *
     * Return the number of integers y in the range [l, r] (inclusive) that are perfect kth powers.
     *
     * Input: l = 1, r = 9, k = 3
     * Output: 2
     *
     * Input: l = 8, r = 30, k = 2
     * Output: 3
     *
     * Constraints:
     *
     * 0 <= l <= r <= 10^9
     * 1 <= k <= 30
     * @param l
     * @param r
     * @param k
     * @return
     */
    // S1
    // time = O(r^(1/k)), space = O(1)
    public int countKthRoots(int l, int r, int k) {
        if (k == 1) return r - l + 1;
        int res = 0;
        for (int i = (int)Math.pow(l, 1.0 / k);; i++) {
            long x = (long)Math.pow(i, k);
            if (x < l) continue;
            if (x > r) break;
            res++;
        }
        return res;
    }

    // S2
    // time = O(logk), space = O(1)
    public int countKthRoots2(int l, int r, int k) {
        return f(r, k) - f(l - 1, k);
    }

    private int f(int n, int k) {
        if (n < 0) return 0;
        int x = (int)Math.pow(n, 1.0 / k);
        // 可能 x 的正确值是 6，但算出来的 x = int(5.99999...) = 5
        if (qmi(x + 1, k) <= n) x++; // 为避免浮点误差，这里用整数计算 pow
        return x + 1;
    }

    private long qmi(long a, long k) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a;
            a = a * a;
            k >>= 1;
        }
        return res;
    }
}
/**
 * x^k <= n => x <= n^(1/k) => x <= pow(n, 1/k)
 */