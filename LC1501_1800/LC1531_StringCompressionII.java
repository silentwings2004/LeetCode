package LC1501_1800;
import java.util.*;
public class LC1531_StringCompressionII {
    /**
     * Run-length encoding is a string compression method that works by replacing consecutive identical characters
     * (repeated 2 or more times) with the concatenation of the character and the number marking the count of the
     * characters (length of the run). For example, to compress the string "aabccc" we replace "aa" by "a2" and replace
     * "ccc" by "c3". Thus the compressed string becomes "a2bc3".
     *
     * Notice that in this problem, we are not adding '1' after single characters.
     *
     * Given a string s and an integer k. You need to delete at most k characters from s such that the run-length
     * encoded version of s has minimum length.
     *
     * Find the minimum length of the run-length encoded version of s after deleting at most k characters.
     *
     * Input: s = "aaabcccd", k = 2
     * Output: 4
     *
     * Input: s = "aabbaa", k = 2
     * Output: 2
     *
     * Input: s = "aaaaaaaaaaa", k = 0
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * 0 <= k <= s.length
     * s contains only lowercase English letters.
     * @param s
     * @param k
     * @return
     */
    public int getLengthOfOptimalCompression(String s, int k) {
        int n = s.length();

        // special case n = 100
        if (n == 100 && k == 0) {
            boolean flag = true;
            for (int i = 1; i < n; i++) {
                if (s.charAt(i) != s.charAt(0)) {
                    flag = false;
                    break;
                }
            }
            if (flag) return 4;
        }

        s = "#" + s;
        int[][][][] f = new int[101][101][27][11];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                for (int ch = 0; ch <= 26; ch++) {
                    for (int num = 0; num <= 10; num++) {
                        f[i][j][ch][num] = Integer.MAX_VALUE;
                    }
                }
            }
        }
        f[0][0][26][0] = 0; // 没有字母，没有删除，最后一个字母是虚拟字母，长度是0

        for (int i = 0; i < n; i++) { // 从i的状态来确定i+1的状态，所以只要到n
            for (int j = 0; j <= Math.min(k, i); j++) {
                for (int ch = 0; ch <= 26; ch++) {
                    for (int num = 0; num <= 10; num++) { // 大于10的状态都归到10内
                        int cur = f[i][j][ch][num];
                        if (cur == Integer.MAX_VALUE) continue;
                        // delete s[i + 1]
                        f[i + 1][j + 1][ch][num] = Math.min(f[i + 1][j + 1][ch][num], cur);

                        // keeps s[i + 1]
                        if (ch != s.charAt(i + 1) - 'a') {
                            f[i + 1][j][s.charAt(i + 1) - 'a'][1] = Math.min(f[i + 1][j][s.charAt(i + 1) - 'a'][1], cur + 1);
                        } else {
                            int add = 0;
                            if (num == 1) add = 1;
                            else if (num >= 2 && num <= 8) add = 0;
                            else if (num == 9) add = 1;
                            else if (num == 10) add = 0;
                            f[i + 1][j][s.charAt(i + 1) - 'a'][Math.min(10, num + 1)] = Math.min(f[i + 1][j][s.charAt(i + 1) - 'a'][Math.min(10, num + 1)], cur + add);
                        }
                    }
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int ch = 0; ch <= 26; ch++) {
            for (int num = 0; num <= 10; num++) {
                res = Math.min(res, f[n][k][ch][num]);
            }
        }
        return res;
    }

    // S2
    // time = O(n^2 * k), space = O(n * k)
    public int getLengthOfOptimalCompression2(String s, int k) {
        int n = s.length(), inf = 0x3f3f3f3f;
        int[][] f = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i], inf);
        f[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                int cnt = 0, del = 0;
                for (int l = i; l >= 1; l--) {
                    if (s.charAt(i - 1) == s.charAt(l - 1)) cnt++;
                    else del++;
                    if (j >= del) {
                        f[i][j] = Math.min(f[i][j], f[l - 1][j - del] + 1 + check(cnt));
                    }
                    if (j > 0) f[i][j] = Math.min(f[i][j], f[i - 1][j - 1]);
                }
            }
        }
        return f[n][k];
    }

    private int check(int x) {
        if (x >= 100) return 3;
        if (x >= 10) return 2;
        if (x >= 2) return 1;
        return 0;
    }
}
/**
 * dp[i][k]: for s[1:i] remove k letters removed, the optimal solution
 * xxxx j xx i
 * dp[i][k] = max{dp[j-1][k-1] + s[j+1:i] Run-length encoding } for j = 1, 2,...i
 * dp[i][k][ch][num]: for s[1:i], with k letters removed, the last letter as ch, the repeating number of the last letter
 * as num, the optimal solution
 * dp[i][k][ch][num] = f(dp[?][?][?][?])
 * dp[i][k][ch][num] => dp[i+1][?][?][?]
 * 1. we delete s[i+1]
 *  dp[i+1][k+1][ch][num] = min(dp[i][k][ch][num])
 * 2. we keep s[i+1]
 * 2.1 if ch != s[i+1]
 *  xxxxx ch ch ch  (s[i+1])
 *  k letters removed
 *  dp[i+1][k][s[i+1]-'a'][1] = min(dp[i][k][ch][num] + 1)
 * 2.2 if ch == s[i+1]
 *  dp[i+1][k][s[i+1]-'a'][num+1] = min(dp[i][k][ch][num] + add)   add = 0, 1
 *  (1) if (num == 1) add = 1 // a -> a2
 *  (2) if (num == 2...8) add = 0 // a2->a3
 *  (3) if (num == 9) add = 1 // a9 -> a10
 *  (4) if (num == 10..98) add = 0 // a10 -> a11
 * return min{dp[n][k][?][?]}
 */