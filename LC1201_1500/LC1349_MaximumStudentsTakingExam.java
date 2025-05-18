package LC1201_1500;
import java.util.*;
public class LC1349_MaximumStudentsTakingExam {
    /**
     * Given a m * n matrix seats  that represent seats distributions in a classroom. If a seat is broken, it is denoted
     * by '#' character otherwise it is denoted by a '.' character.
     *
     * Students can see the answers of those sitting next to the left, right, upper left and upper right, but he cannot
     * see the answers of the student sitting directly in front or behind him. Return the maximum number of students
     * that can take the exam together without any cheating being possible.
     *
     * Students must be placed in seats in good condition.
     *
     * Input: seats = [["#",".","#","#",".","#"],
     *                 [".","#","#","#","#","."],
     *                 ["#",".","#","#",".","#"]]
     * Output: 4
     *
     * Input: seats = [[".","#"],
     *                 ["#","#"],
     *                 ["#","."],
     *                 ["#","#"],
     *                 [".","#"]]
     * Output: 3
     *
     * Input: seats = [["#",".",".",".","#"],
     *                 [".","#",".","#","."],
     *                 [".",".","#",".","."],
     *                 [".","#",".","#","."],
     *                 ["#",".",".",".","#"]]
     * Output: 10
     *
     * Constraints:
     *
     * seats contains only characters '.' and'#'.
     * m == seats.length
     * n == seats[i].length
     * 1 <= m <= 8
     * 1 <= n <= 8
     * @param seats
     * @return
     */
    // S1: 状态压缩dp
    // time = O(m * n * 2^n * 2^n), space = O(m * 2^n)
    public int maxStudents(char[][] seats) {
        int m = seats.length, n = seats[0].length;
        final int inf = (int)1e8;
        int[] g = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (seats[i][j] == '#') g[i] |= 1 << j;
            }
        }

        int[][] f = new int[m + 1][1 << n];
        for (int i = 0; i <= m; i++) Arrays.fill(f[i], -inf);
        f[0][0] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < 1 << n; j++) {
                if ((g[i - 1] & j) == 0 && check(j)) {
                    for (int k = 0; k < 1 << n; k++) {
                        if (check(j | k)) {
                            f[i][j] = Math.max(f[i][j], f[i - 1][k] + get_count(j));
                        }
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < 1 << n; i++) res = Math.max(res, f[m][i]);
        return res;
    }

    private boolean check(int x) {
        for (int i = 1; i < 8; i++) {
            if ((x >> i & 1) == 1 && (x >> i - 1 & 1) == 1) return false;
        }
        return true;
    }

    private int get_count(int x) {
        int cnt = 0;
        while (x > 0) {
            cnt += x & 1;
            x >>= 1;
        }
        return cnt;
    }

    // S2: 匈牙利算法
    // time = O(V*E), space = O(m * n * 2)
    int[] dx = new int[]{-1, -1, 0, 0}, dy = new int[]{1, -1, -1, 1};
    public int maxStudents2(char[][] seats) {
        int m = seats.length, n = seats[0].length;
        int[][][] match = new int[m][n][2];
        int sc = 0, mc = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (seats[i][j] == '.') {
                    match[i][j][0] = match[i][j][1] = -1;
                    sc++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (seats[i][j] == '.' && match[i][j][0] == -1) {
                    boolean[][] st = new boolean[m][n];
                    if (dfs(seats, match, st, i, j)) mc++;
                }
            }
        }
        return sc - mc;
    }

    private boolean dfs(char[][] seats, int[][][] match, boolean[][] st, int x, int y) {
        int m = seats.length, n = seats[0].length;
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a < 0 || a >= m || b < 0 || b >= n) continue;
            if (seats[a][b] == '#') continue;
            if (st[a][b]) continue;
            st[a][b] = true;
            if (match[a][b][0] == -1 || dfs(seats, match, st, match[a][b][0], match[a][b][1])) {
                match[x][y][0] = a;
                match[x][y][1] = b;
                match[a][b][0] = x;
                match[a][b][1] = y;
                return true;
            }
        }
        return false;
    }
}
/**
 * 状态压缩dp
 * 二进制表示上一行摆放的方案
 * f(i,j): 第i行摆放的学生状态为j的所有方案
 * 枚举下第i-1行的状态，发生冲突的时候，只和第i-1行有关
 * f(i,j) = f(i-1,k) + 2
 * 1.同一行内不能有相邻的1
 * 2. j|k 也不能有相邻的1
 */