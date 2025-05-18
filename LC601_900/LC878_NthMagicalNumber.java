package LC601_900;

public class LC878_NthMagicalNumber {
    /**
     * A positive integer is magical if it is divisible by either a or b.
     *
     * Given the three integers n, a, and b, return the nth magical number. Since the answer may be very large, return
     * it modulo 10^9 + 7.
     *
     * Input: n = 1, a = 2, b = 3
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     * 2 <= a, b <= 4 * 10^4
     * @param n
     * @param a
     * @param b
     * @return
     */
    // S1: Math
    // time = O(a + b), space = O(1)
    public int nthMagicalNumber(int n, int a, int b) {
        long M = (long)(1e9 + 7);
        long lcm = a * b / gcd(a, b);
        long p = (long) n / (lcm / a + lcm / b - 1);
        long s = p * (lcm / a + lcm / b - 1);
        long t = (long) n - s;
        if (t == 0) return (int) (lcm * p % M);

        int i = 1, j = 1, count = 0;
        long remainder = 0;
        while (count < t) {
            if (a * i < b * j) {
                remainder = a * i;
                i++; // if a * i == b * j,那么一定是lcm，不会走到这一步！
            }
            else {
                remainder = b * j;
                j++;
            }
            count++;
        }
        return (int) ((lcm * p % M + remainder) % M);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // S2: BS
    // time = O((logn)^2), space = O(1)
    public int nthMagicalNumber2(int n, int a, int b) {
        long mod = (long)(1e9 + 7);
        long l = 1, r = (long) 4e13;
        while (l < r) {
            long mid = l + r >> 1;
            if (get(mid, a, b) >= n) r = mid;
            else l = mid + 1;
        }
        return (int)(r % mod);
    }

    private long get(long mid, int a, int b) {
        return mid / a + mid / b - mid / (a * b / gcd(a, b));
    }
}
/**
 * ref: LC1201
 * m <= magic(<= M) >= N => decrease M
 *                  < N => increase M
 * 2 3 6  M = 6, 7
 * lcm(2,3) = 6 => 2,3,4,6
 * 最小公倍数为一个周期, k
 * lcm(2,3) => k
 * period = n/lcm(2,3)   k*period
 * n - k * period
 */