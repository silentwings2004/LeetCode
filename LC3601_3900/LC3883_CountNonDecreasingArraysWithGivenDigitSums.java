package LC3601_3900;
import java.util.*;
public class LC3883_CountNonDecreasingArraysWithGivenDigitSums {
    /**
     * You are given an integer array digitSum of length n.
     *
     * An array arr of length n is considered valid if:
     *
     * 0 <= arr[i] <= 5000
     * it is non-decreasing.
     * the sum of the digits of arr[i] equals digitSum[i].
     * Return an integer denoting the number of distinct valid arrays. Since the answer may be large, return it modulo
     * 10^9 + 7.
     *
     * An array is said to be non-decreasing if each element is greater than or equal to the previous element, if it
     * exists.
     *
     * Input: digitSum = [25,1]
     * Output: 6
     *
     * Input: digitSum = [1]
     * Output: 4
     *
     * Input: digitSum = [2,49,23]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= digitSum.length <= 1000
     * 0 <= digitSum[i] <= 50
     * @param digitSum
     * @return
     */
    // time = O(n * k), space = O(k)
    public int countArrays(int[] digitSum) {
        int n = digitSum.length;
        long mod = (long)(1e9 + 7);
        int[] q = new int[5010];
        for (int i = 0; i <= 5000; i++) {
            int x = i, s = 0;
            while (x > 0) {
                s += x % 10;
                x /= 10;
            }
            q[i] = s;
        }

        long[] f = new long[5010];
        Arrays.fill(f, 1);
        for (int i = n - 1; i >= 0; i--) {
            long s = 0;
            for (int j = 5000; j >= 0; j--) {
                if (q[j] == digitSum[i]) s = (s + f[j]) % mod;
                f[j] = s;
            }
        }
        return (int)f[0];
    }
}