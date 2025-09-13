package LC3601_3900;

public class LC3677_CountBinaryPalindromicNumbers {
    /**
     * You are given a non-negative integer n.
     *
     * A non-negative integer is called binary-palindromic if its binary representation (written without leading zeros)
     * reads the same forward and backward.
     *
     * Return the number of integers k such that 0 <= k <= n and the binary representation of k is a palindrome.
     *
     * Note: The number 0 is considered binary-palindromic, and its representation is "0".
     *
     * Input: n = 9
     * Output: 6
     *
     * Input: n = 0
     * Output: 1
     *
     * Constraints:
     *
     * 0 <= n <= 10^15
     * @param n
     * @return
     */
    // S1: 二分
    // time = O(logn), space = O(1)
    public int countBinaryPalindromes(long n) {
        if (n == 0) return 1;
        int res = 1;
        int m = 64 - Long.numberOfLeadingZeros(n);
        for (int i = 1; i < m; i++) {
            int l = (i + 1) / 2;
            res += 1 << (l - 1);
        }

        int k = (m + 1) / 2;
        int l = 1 << (k - 1), r = (1 << k) - 1;
        int st = l;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(mid, m, n)) l = mid;
            else r = mid - 1;
        }
        if (check(r, m, n)) res += r - st + 1;
        return res;
    }

    private boolean check(int l, int m, long n) {
        long v = l;
        int t = l;
        if ((m & 1) == 1) t >>= 1;
        while (t > 0) {
            v = (v << 1) | (t & 1);
            t >>= 1;
        }
        return v <= n;
    }

    // S2
    // time = O(logn), space = O(1)
    public int countBinaryPalindromes2(long n) {
        if (n == 0) return 1;
        int m = 64 - Long.numberOfLeadingZeros(n);
        int res = 1;
        for (int i = 1; i < m; i++) res += 1 << ((i - 1) / 2);
        for (int i = m - 2; i >= m / 2; i--) {
            if ((n >> i & 1) == 1) res += 1 << (i - m / 2);
        }

        long pal = n >> (m / 2);
        for (long v = pal >> (m % 2); v > 0; v /= 2) pal = pal * 2 + v % 2;
        if (pal <= n) res++;
        return res;
    }
}