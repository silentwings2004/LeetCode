package LC3901_4200;

public class LC4028_MinimumOperationstoMakeaRotatedPalindromeII {
    /**
     * You are given a string s consisting of lowercase English letters.
     *
     * You can perform the following operations any number of times (including zero) and in any order:
     *
     * Increment: Choose any index i and replace s[i] with the next lowercase English letter. The letter after 'z' is 'a'.
     * Left rotate: Move the first character of the string to the end.
     * Return the minimum number of operations required to make s a palindrome.
     *
     * Input: s = "abc"
     * Output: 2
     *
     * Input: s = "yb"
     * Output: 3
     *
     * Constraints:
     *
     * 2 <= s.length <= 5 * 10^4
     * s consists only of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int minOperations(String s) {
        int n = s.length();
        long[] convSum = new long[n];
        int[] a = new int[n];

        int tot = 0;
        for (int k = 0; k < 13; k++) {
            for (int i = 0; i < n; i++) {
                int u = s.charAt(i) - 'a';
                if (k <= u && u < k + 13) {
                    a[i] = 1;
                    tot++;
                } else a[i] = 0;
            }
            long[] c = selfCyclicConv(a);
            for (int i = 0; i < n; i++) convSum[i] += c[i];
        }

        int best = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int idx = (2 * i - 1 + n) % n;
            best = Math.min(best, i - (int)convSum[idx]);
        }
        return tot + best;
    }

    static long[] selfCyclicConv(int[] a) {
        int n = a.length;

        // FFT size for linear convolution.
        int size = 1;
        while (size < 2 * n - 1) {
            size <<= 1;
        }

        double[] re = new double[size];
        double[] im = new double[size];

        for (int i = 0; i < n; i++) {
            re[i] = a[i];
        }

        fft(re, im, false);

        // (FFT(a))^2
        for (int i = 0; i < size; i++) {
            double r = re[i];
            double x = im[i];

            re[i] = r * r - x * x;
            im[i] = 2 * r * x;
        }

        fft(re, im, true);

        // Linear convolution -> cyclic convolution.
        long[] res = new long[n];

        for (int i = 0; i < 2 * n - 1; i++) {
            res[i % n] += Math.round(re[i]);
        }

        return res;
    }

    static void fft(double[] re, double[] im, boolean invert) {
        int n = re.length;

        // Bit reversal.
        for (int i = 1, j = 0; i < n; i++) {
            int bit = n >> 1;

            while ((j & bit) != 0) {
                j ^= bit;
                bit >>= 1;
            }

            j ^= bit;

            if (i < j) {
                double tmp = re[i];
                re[i] = re[j];
                re[j] = tmp;

                tmp = im[i];
                im[i] = im[j];
                im[j] = tmp;
            }
        }

        for (int len = 2; len <= n; len <<= 1) {
            double angle = 2.0 * Math.PI / len * (invert ? 1 : -1);

            double wLenRe = Math.cos(angle);
            double wLenIm = Math.sin(angle);

            for (int i = 0; i < n; i += len) {
                double wRe = 1.0;
                double wIm = 0.0;

                for (int j = 0; j < len / 2; j++) {
                    int u = i + j;
                    int v = u + len / 2;

                    double vRe = re[v] * wRe - im[v] * wIm;
                    double vIm = re[v] * wIm + im[v] * wRe;

                    re[v] = re[u] - vRe;
                    im[v] = im[u] - vIm;

                    re[u] += vRe;
                    im[u] += vIm;

                    double nextWRe =
                            wRe * wLenRe - wIm * wLenIm;
                    double nextWIm =
                            wRe * wLenIm + wIm * wLenRe;

                    wRe = nextWRe;
                    wIm = nextWIm;
                }
            }
        }

        if (invert) {
            for (int i = 0; i < n; i++) {
                re[i] /= n;
                im[i] /= n;
            }
        }
    }
}