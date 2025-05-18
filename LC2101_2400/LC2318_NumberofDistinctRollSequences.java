package LC2101_2400;

public class LC2318_NumberofDistinctRollSequences {
    /**
     * You are given an integer n. You roll a fair 6-sided dice n times. Determine the total number of distinct
     * sequences of rolls possible such that the following conditions are satisfied:
     *
     * The greatest common divisor of any adjacent values in the sequence is equal to 1.
     * There is at least a gap of 2 rolls between equal valued rolls. More formally, if the value of the ith roll is
     * equal to the value of the jth roll, then abs(i - j) > 2.
     * Return the total number of distinct sequences possible. Since the answer may be very large, return it modulo
     * 10^9 + 7.
     *
     * Two sequences are considered distinct if at least one element is different.
     *
     * Input: n = 4
     * Output: 184
     *
     * Input: n = 2
     * Output: 22
     *
     * Constraints:
     *
     * 1 <= n <= 10^4
     * @param n
     * @return
     */
    // time = O(6^3 * n), space = O(36n)
    public int distinctSequences(int n) {
        final int N = 10010, M = (int) 1e9 + 7;
        int[][][] f = new int[N][6][6];

        if (n == 1) return 6;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (gcd(i + 1, j + 1) == 1 && i != j) {
                    f[2][i][j] = 1;
                }
            }
        }

        for (int i = 3; i <= n; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 6; k++) {
                    if (j != k && gcd(j + 1, k + 1) == 1) {
                        for (int u = 0; u < 6; u++) {
                            if (u != j && u != k && gcd(u + 1, j + 1) == 1) {
                                f[i][j][k] = (f[i][j][k] + f[i - 1][u][j]) % M;
                            }
                        }
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                res = (res + f[n][i][j]) % M;
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // S1.2
    public int distinctSequences2(int n) {
        long[][][] f = new long[10010][7][7];
        long M = (long)(1e9 + 7);

        if (n == 1) return 6;

        long count = 0;
        for (int a = 1; a <= 6; a++) {
            for (int b = 1; b <= 6; b++) {
                if (a != b && gcd(b, a) == 1) {
                    f[2][b][a] = 1;
                    count++;
                }
            }
        }
        if (n == 2) return (int) count;

        for (int i = 3; i <= n; i++) {
            for (int a = 1; a <= 6; a++) {
                for (int b = 1; b <= 6; b++) {
                    if (a == b || gcd(b, a) != 1) continue;
                    for (int c = 1; c <= 6; c++) {
                        if (c != a) {
                            f[i][b][a] = (f[i][b][a] + f[i - 1][c][b]) % M;
                        }
                    }
                }
            }
        }

        long res = 0;
        for (int a = 1; a <= 6; a++) {
            for (int b = 1; b <= 6; b++) {
                res = (res + f[n][b][a]) % M;
            }
        }
        return (int) res;
    }
}
/**
 * 状态表示：f(i,j,k)
 * 集合：所有长度为i的，且最后两个数分别是j和k的合法序列的集合  f[n,j,k]
 * 属性：数量
 * 状态计算：根据最后一个不同点来划分
 * 最后一个元素：k, 前一个元素j,
 * 枚举下倒数第三个元素：1~6 -> 分成6个子集 相加
 * eg.看下第4类，4 - j - k
 * 4 != k
 * (4,j) = 1
 * 最后一位k固定不变 => 前面方案数 i - 1 类，最后两位是4和j => f(i-1,4,j)
 * time = O(6^3 * n), space = O(36n)
 *
 * 1. b != a
 * 2. gcd(b,a) == 1
 * 3. c != a
 * dp[i][b][a] => dp[i-1][c][b]
 * x x x x i
 *   ? c b a
 * for (int i = 1; i <= n; i++) {
 *     for (int a = 1; a <= 6; a++) {
 *         for (int b = 1; b <= 6; b++) {
 *              for (int c = 1; c <= 6; c++) {
 *                  if (b != a && gcd(b, a) == 1 && c != a) {
 *                      dp[i][b][a] += dp[i - 1][c][b]
 *                  }
 *              }
 *         }
 *     }
 * }
 * sum dp[n][x][x]
 * i 至少从3开始
 * i == 2 => dp[2][x][x]
 * i == 1 => dp[1][x][x] => 6 扔一次，共6种可能
 */
