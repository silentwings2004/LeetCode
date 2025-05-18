package LC901_1200;
import java.util.*;
public class LC903_ValidPermutationsforDISequence {
    /**
     * We are given s, a length n string of characters from the set {'D', 'I'}. (These letters stand for "decreasing"
     * and "increasing".)
     *
     * A valid permutation is a permutation p[0], p[1], ..., p[n] of integers {0, 1, ..., n}, such that for all i:
     *
     * If s[i] == 'D', then p[i] > p[i+1], and;
     * If s[i] == 'I', then p[i] < p[i+1].
     * How many valid permutations are there?  Since the answer may be large, return your answer modulo 10^9 + 7.
     *
     * Input: s = "DID"
     * Output: 5
     *
     * Note:
     *
     * 1 <= s.length <= 200
     * s consists only of characters from the set {'D', 'I'}.
     * @param s
     * @return
     */
    // S1
    // time = O(n^3), space = O(n^2)
    public int numPermsDISequence(String s) {
        int n = s.length();
        s = "#" + s;
        int mod = (int)(1e9 + 7);
        int[][] f = new int[n + 1][n + 1];
        f[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == 'I') {
                    for (int k = 0; k < j; k++) {
                        f[i][j] = (f[i][j] + f[i - 1][k]) % mod;
                    }
                } else {
                    for (int k = j; k < i; k++) {
                        f[i][j] = (f[i][j] + f[i - 1][k]) % mod;
                    }
                }
            }
        }

        long res = 0;
        for (int i = 0; i <= n; i++) res = (res + f[n][i]) % mod;
        return (int) res;
    }

    // S1.2
    // time = O(n^2), space = O(n^2)
    public int numPermsDISequence2(String s) {
        int n = s.length();
        s = "#" + s;
        int[][] f = new int[n + 1][n + 1];
        f[0][0] = 1;

        int mod = (int) 1e9 + 7;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i) == 'I') {
                for (int j = 1; j <= i; j++) {
                    f[i][j] = (f[i - 1][j - 1] + f[i][j - 1]) % mod;
                }
            } else {
                for (int j = i - 1; j >= 0; j--) {
                    f[i][j] = (f[i - 1][j] + f[i][j + 1]) % mod;
                }
            }
        }

        long res = 0;
        for (int i = 0; i <= n; i++) res = (res + f[n][i]) % mod;
        return (int) res;
    }
}
/**
 * ref: LC1866
 * dp[i][j]: how many permutations of [0:i] s.t. the i-th element is j
 * 前i个，考虑一个大的permutation问题，前i个位置上就是0~i的permutation
 * #DIDI
 * [xxxxi]xxxx
 * i-1th: 0, 1, 2, ...j-1
 * dp[i][j] = sum{dp[i-1][0], dp[i-1][1], ..., dp[i-1][j-1]}
 * dp[i][j] = sum{dp[i-1][j], dp[i-1][j+1], dp[i-1][j+2], ..., dp[i-1][i-1]}
 * 0312 3 -> 0412 3 (提升1位)
 * 0312 4 -> 0312 4
 * dp[i-1][2]
 *      I
 * 0321 1 => 0432 1
 *
 * f(i, j)表示0~i的一个满足s[0-i-1]要求且最后一位为j的所有方案
 * f(i,j)
 * 1. 降序：f(i,j) = f(i-1,j) + f(i-1,j+1)+...+ f(i-1,i-1)  => 所有第二维 >= j 的和
 * 2. 升序：f(i,j) = f(i-1,0) + f(i-1,1) + ... + f(i-1,j-1)
 * 优化：
 * f(i,j+1) = f(i-1,j+1) + f(i-1,j+2)+...+f(i-1,i-1) => f(i,j) = f(i-1,j) + f(i,j+1)
 * f(i,j-1) = f(i-1,j-1)+ + f(i-1,j) + ... + f(i-1,0) => f(i,j) = f(i-1,j-1) + f(i,j-1)
 *
 * f(i,j) = f(i-1,j) + f(i,j+1)
 * f(i,j) = f(i-1,j-1) + f(i,j-1)
 */
