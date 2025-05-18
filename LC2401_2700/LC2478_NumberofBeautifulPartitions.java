package LC2401_2700;
import java.util.*;
public class LC2478_NumberofBeautifulPartitions {
    /**
     * You are given a string s that consists of the digits '1' to '9' and two integers k and minLength.
     *
     * A partition of s is called beautiful if:
     *
     * s is partitioned into k non-intersecting substrings.
     * Each substring has a length of at least minLength.
     * Each substring starts with a prime digit and ends with a non-prime digit. Prime digits are '2', '3', '5', and
     * '7', and the rest of the digits are non-prime.
     * Return the number of beautiful partitions of s. Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * A substring is a contiguous sequence of characters within a string.
     *
     * Input: s = "23542185131", k = 3, minLength = 2
     * Output: 3
     *
     * Input: s = "23542185131", k = 3, minLength = 3
     * Output: 1
     *
     * Input: s = "3312958", k = 3, minLength = 1
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= k, minLength <= s.length <= 1000
     * s consists of the digits '1' to '9'.
     * @param s
     * @param k
     * @param minLength
     * @return
     */
    // time = O(n^2), space = O(n^2)
    public int beautifulPartitions(String s, int k, int minLength) {
        int n = s.length();
        s = "#" + s;
        long[][] f = new long[n + 1][k + 1];
        f[0][0] = 1;

        long mod = (long)(1e9 + 7);
        for (int j = 1; j <= k; j++) {
            long presum = 0;
            for (int i = 1; i <= n; i++) {
                int u = i - minLength + 1;
                if (u >= 1 && isPrime(s.charAt(u)) && !isPrime(s.charAt(u - 1))) {
                    presum = (presum + f[u - 1][j - 1]) % mod;
                }
                if (!isPrime(s.charAt(i))) f[i][j] = presum;
            }
        }
        return (int) f[n][k];
    }

    private boolean isPrime(char x) {
        return x == '2' || x == '3' || x == '5' || x == '7';
    }
}
/**
 * x x x x [x x x x x x]
 *          k         i
 * dp[i][j] = sum{ dp[k-1][j-1] } k = 0, 1, 2, ...i-1
 */