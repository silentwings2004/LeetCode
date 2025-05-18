package LC2401_2700;
import java.util.*;
public class LC2523_ClosestPrimeNumbersinRange {
    /**
     * Given two positive integers left and right, find the two integers num1 and num2 such that:
     *
     * left <= nums1 < nums2 <= right .
     * nums1 and nums2 are both prime numbers.
     * nums2 - nums1 is the minimum amongst all other pairs satisfying the above conditions.
     * Return the positive integer array ans = [nums1, nums2]. If there are multiple pairs satisfying these conditions,
     * return the one with the minimum nums1 value or [-1, -1] if such numbers do not exist.
     *
     * A number greater than 1 is called prime if it is only divisible by 1 and itself.
     *
     * Input: left = 10, right = 19
     * Output: [11,13]
     *
     * Input: left = 4, right = 6
     * Output: [-1,-1]
     *
     * Constraints:
     *
     * 1 <= left <= right <= 10^6
     * @param left
     * @param right
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 1000010;
    int cnt;
    int[] primes;
    boolean[] st;
    public int[] closestPrimes(int left, int right) {
        primes = new int[N];
        st = new boolean[N];

        get_primes(right);

        int[] res = new int[2];
        Arrays.fill(res, -1);
        int d = (int) 1e8;
        for (int i = 1; i < cnt; i++) {
            int a = primes[i - 1], b = primes[i];
            if (a >= left && b - a < d) {
                d = b - a;
                res = new int[]{a, b};
            }
        }
        return res;
    }

    private void get_primes(int n) {
        cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (!st[i]) primes[cnt++] = i;
            for (int j = 0; primes[j] <= n / i; j++) {
                st[primes[j] * i] = true;
                if (i % primes[j] == 0) break;
            }
        }
    }
}