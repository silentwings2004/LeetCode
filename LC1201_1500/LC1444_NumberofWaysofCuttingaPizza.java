package LC1201_1500;

public class LC1444_NumberofWaysofCuttingaPizza {
    /**
     * Given a rectangular pizza represented as a rows x cols matrix containing the following characters: 'A' (an apple)
     * and '.' (empty cell) and given the integer k. You have to cut the pizza into k pieces using k-1 cuts.
     *
     * For each cut you choose the direction: vertical or horizontal, then you choose a cut position at the cell
     * boundary and cut the pizza into two pieces. If you cut the pizza vertically, give the left part of the pizza to
     * a person. If you cut the pizza horizontally, give the upper part of the pizza to a person. Give the last piece
     * of pizza to the last person.
     *
     * Return the number of ways of cutting the pizza such that each piece contains at least one apple. Since the
     * answer can be a huge number, return this modulo 10^9 + 7.
     *
     * Input: pizza = ["A..","AAA","..."], k = 3
     * Output: 3
     *
     * Input: pizza = ["A..","AA.","..."], k = 3
     * Output: 1
     *
     * Input: pizza = ["A..","A..","..."], k = 1
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= rows, cols <= 50
     * rows == pizza.length
     * cols == pizza[i].length
     * 1 <= k <= 10
     * pizza consists of characters 'A' and '.' only.
     * @param pizza
     * @param k
     * @return
     */
    // time = O(k * n^3), space = O(k * m * n)
    public int ways(String[] pizza, int k) {
        int m = pizza.length, n = pizza[0].length();
        long[][][] f = new long[k][m][n];
        int[][] s = new int[m + 1][n + 1];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                s[i][j] = s[i + 1][j] + s[i][j + 1] - s[i + 1][j + 1] + (pizza[i].charAt(j) == 'A' ? 1 : 0);
            }
        }

        long mod = (long)(1e9 + 7);
        f[0][0][0] = 1;
        for (int c = 1; c < k; c++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (!check(s, i, j, m, n)) continue;
                    for (int t = 0; t < i; t++) {
                        if (!check(s, t, j, i, j)) continue;
                        f[c][i][j] = (f[c][i][j] + f[c - 1][t][j]) % mod;
                    }
                    for (int t = 0; t < j; t++) {
                        if (!check(s, i, t, i, j)) continue;
                        f[c][i][j] = (f[c][i][j] + f[c - 1][i][t]) % mod;
                    }
                }
            }
        }

        long res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = (res + f[k - 1][i][j]) % mod;
            }
        }
        return (int) res;
    }

    private boolean check(int[][] s, int x1, int y1, int x2, int y2) {
        return s[x1][y1] - s[x2][y2] != 0;
    }
}
