package LC2401_2700;
import java.util.*;
public class LC2484_CountPalindromicSubsequences {
    /**
     * Given a string of digits s, return the number of palindromic subsequences of s having length 5. Since the answer
     * may be very large, return it modulo 109 + 7.
     *
     * Note:
     *
     * A string is palindromic if it reads the same forward and backward.
     * A subsequence is a string that can be derived from another string by deleting some or no characters without
     * changing the order of the remaining characters.
     *
     * nput: s = "103301"
     * Output: 2
     *
     * Input: s = "0000000"
     * Output: 21
     *
     * Input: s = "9999900000"
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^4
     * s consists of digits.
     * @param s
     * @return
     */
    // time = O(100 * n), space = O(n)
    final int N = 10010, mod = (int) 1e9 + 7;
    public int countPalindromes(String s) {
        int[][][] prev = new int[N][10][10], next = new int[N][10][10];
        int n = s.length();
        int[] cnt = new int[10];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    prev[i][j][k] = prev[i - 1][j][k];
                }
            }
            int y = s.charAt(i - 1) - '0';
            for (int j = 0; j < 10; j++) {
                prev[i][j][y] += cnt[j];
            }
            cnt[y]++;
        }

        Arrays.fill(cnt, 0);
        for (int i = n; i > 0; i--) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    next[i][j][k] = next[i + 1][j][k];
                }
            }
            int y = s.charAt(i - 1) - '0';
            for (int j = 0; j < 10; j++) {
                next[i][y][j] += cnt[j];
            }
            cnt[y]++;
        }

        long res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    res = (res + (long) prev[i - 1][j][k] * next[i + 1][k][j]) % mod;
                }
            }
        }
        return (int) res;
    }
}
/**
 * 枚举中点，两边各2位，每一位的选择只有10种 => 10 * 10 = 100 => 10^4 * 100 = 10^6
 * 如何快速去求左边x,y，右边y，x的方案数
 * 互相独立，分别去左右两边快速统计出有多少xy和yx。
 * 统计2位，直接枚举
 * 如何快速枚举出前i-1位里有多少个xy => 从前往后枚举y的位置，取决于y前有多少个x => cnt
 * s[i,x,y]: 前i位中有多少对xy
 * s[i,x,y] = s[i-1,x,y]
 * 第i位 = str[i] = y
 * x: 0 ~ 9
 * cnt[0]: 前i-1位中0的数量 = 0y
 * s[i,0,y] += cnt[0]
 * s[i,1,y] += cnt[1]
 *
 * [* ?] X [? *]
 * dp1[i][j][k]: how many subsequences of {j,k} in the first i elements
 * dp2[i][j][k]: how many subsequences of {j,k} in the last i elements (reversely)
 * 中间不在乎是谁，遍历中间元素
 * 枚举下前面有多少个j k
 * for (int i = 1; i <= n; i++) {
 *     for (int j = 0; j <= 9; j++) {
 *         for (int k = 0; k <= 9; k++) {
 *             res += dp1[i-1][j][k] * dp2[i+1][j][k]
 *         }
 *     }
 * }
 * return res;
 *
 * x x x x x x x
 *             i
 *             k
 * a: dp1[i][j][k] += dp1[i-1][j][k]
 * b: if (s[i] == k) dp1[i][j][k] += count1[i-1][j]   i 参与到了构建中，只能作为k参与进来
 * 看前i-1个元素里有多少个j
 */
