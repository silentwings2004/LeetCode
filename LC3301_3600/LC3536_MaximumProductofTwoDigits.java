package LC3301_3600;

public class LC3536_MaximumProductofTwoDigits {
    /**
     * You are given a positive integer n.
     *
     * Return the maximum product of any two digits in n.
     *
     * Note: You may use the same digit twice if it appears more than once in n.
     *
     * Input: n = 31
     * Output: 3
     *
     * Input: n = 22
     * Output: 4
     *
     * Input: n = 124
     * Output: 8
     *
     * Constraints:
     *
     * 10 <= n <= 10^9
     * @param n
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    public int maxProduct(int n) {
        String s = String.valueOf(n);
        int m = s.length();
        int[] a = new int[m];
        for (int i = 0; i < m; i++) a[i] = s.charAt(i) - '0';
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                res = Math.max(res, a[i] * a[j]);
            }
        }
        return res;
    }

    // S2
    // time = O(m), space = O(1)
    public int maxProduct2(int n) {
        int a = 0, b = 0;
        while (n > 0) {
            int x = n % 10;
            n /= 10;
            if (x > a) {
                b = a;
                a = x;
            } else if (x > b) b = x;
        }
        return a * b;
    }
}