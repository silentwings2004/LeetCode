package LC001_300;
import java.util.*;
public class LC37_SudokuSolver {
    /**
     * Write a program to solve a Sudoku puzzle by filling the empty cells.
     *
     * A sudoku solution must satisfy all of the following rules:
     *
     * Each of the digits 1-9 must occur exactly once in each row.
     * Each of the digits 1-9 must occur exactly once in each column.
     * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
     * The '.' character indicates empty cells.
     *
     * Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],
     * [".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],
     * ["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],
     * [".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
     * Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],
     * ["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],
     * ["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],
     * ["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
     *
     * Constraints:
     *
     * board.length == 9
     * board[i].length == 9
     * board[i][j] is a digit or '.'.
     * It is guaranteed that the input board has only one solution.
     * @param board
     */
    // S1: DFS
    // time = O(1), space = O(1)
    boolean[][] row, col;
    boolean[][][] cell;
    public void solveSudoku(char[][] board) {
        row = new boolean[9][9];
        col = new boolean[9][9];
        cell = new boolean[3][3][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int t = board[i][j] - '1';
                    row[i][t] = col[j][t] = cell[i / 3][j / 3][t] = true;
                }
            }
        }
        dfs(board, 0, 0);
    }

    private boolean dfs(char[][] board, int x, int y) {
        if (y == 9) {
            x++;
            y = 0;
        }
        if (x == 9) return true;

        if (board[x][y] != '.') return dfs(board, x, y + 1);
        for (int i = 0; i < 9; i++) {
            if (!row[x][i] && !col[y][i] && !cell[x / 3][y / 3][i]) {
                board[x][y] = (char)(i + '1');
                row[x][i] = col[y][i] = cell[x / 3][y / 3][i] = true;
                if (dfs(board, x, y + 1)) return true;
                board[x][y] = '.';
                row[x][i] = col[y][i] = cell[x / 3][y / 3][i] = false;
            }
        }
        return false;
    }

    // S2: DFS
    // time = O(1), space = O(1)
//    public void solveSudoku2(char[][] board) {
//        // corner case
//        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;
//
//        dfs(board, 0, 0);
//    }
//
//    private boolean dfs(char[][] board, int i, int j) {
//        if (i == 9) return true;
//        if (j == 9) return dfs(board, i + 1, 0); // 进入下一行，该行已填完
//        if (board[i][j] != '.') return dfs(board, i, j + 1);
//
//        for (char k = '1'; k <= '9'; k++) {
//            if (!isOk(board, i, j, k)) continue;
//            board[i][j] = k;
//            if (dfs(board, i, j + 1)) return true;
//            board[i][j] = '.'; // backtrack
//        }
//        return false;
//    }
//
//    private boolean isOk(char[][] board, int i, int j, char k) {
//        for (int row = 0; row < 9; row++) {
//            if (board[row][j] == k) return false;
//        }
//
//        for (int col = 0; col < 9; col++) {
//            if (board[i][col] == k) return false;
//        }
//
//        // 以左上角来定义模块
//        int x = i / 3 * 3, y = j / 3 * 3; // 小模块的左上角
//        for (int p = 0; p < 3; p++) {
//            for (int q = 0; q < 3; q++) {
//                if (board[x + p][y + q] == k) return false;
//            }
//        }
//        return true;
//    }

    // S3: bit optimization
    // time = O(1), space = O(1)
    class Solution {
        final int N = 9, M = 1 << N;
        char[][] board;
        int[] row, col, ones, map;
        int[][] cell;
        public void solveSudoku(char[][] board) {
            this.board = board;
            row = new int[N];
            col = new int[N];
            ones = new int[M];
            map = new int[M];
            cell = new int[3][3];

            // init
            for (int i = 0; i < N; i++) row[i] = col[i] = M - 1;
            for (int i = 0; i < 3; i++) Arrays.fill(cell[i], M - 1);
            for (int i = 0; i < N; i++) map[1 << i] = i;
            for (int i = 0; i < M; i++) ones[i] = Integer.bitCount(i);

            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] != '.') {
                        int t = board[i][j] - '1';
                        draw(i, j, t, true);
                    } else cnt++;
                }
            }
            dfs(cnt);
        }

        private void draw(int x, int y, int t, boolean flag) {
            if (flag) board[x][y] = (char)(t + '1');
            else board[x][y] = '.';

            int v = 1 << t;
            if (!flag) v = -v;

            row[x] -= v;
            col[y] -= v;
            cell[x / 3][y / 3] -= v;
        }

        private boolean dfs(int cnt) {
            if (cnt == 0) return true;

            int minv = 10, x = -1, y = -1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == '.') {
                        int state = get(i, j);
                        if (ones[state] < minv) {
                            minv = ones[state];
                            x = i;
                            y = j;
                        }
                    }
                }
            }

            int state = get(x, y);
            for (int i = state; i > 0; i -= lowbit(i)) {
                int t = map[lowbit(i)];
                draw(x, y, t, true);
                if (dfs(cnt - 1)) return true;
                draw(x, y, t, false);
            }
            return false;
        }

        private int get(int x, int y) {
            return row[x] & col[y] & cell[x / 3][y / 3];
        }

        private int lowbit(int x) {
            return x & -x;
        }
    }

    // S4: Dancing Links
    class Solution2 {
        final int N = 4 * (9 * 9) * 10;
        int m = 9 * 9 * 4;
        int idx, top, hh, tt;
        int[] u, d, l, r, s, row, col;
        int[] ans;
        int[][] op;
        public void solveSudoku(char[][] board) {
            u = new int[N];
            d = new int[N];
            l = new int[N];
            r = new int[N];
            s = new int[N];
            row = new int[N];
            col = new int[N];
            ans = new int[N];
            op = new int[N][3];

            init();
            for (int i = 0, n = 1; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    int a = 0, b = 8;
                    if (board[i][j] != '.') a = b = board[i][j] - '1';
                    for (int k = a; k <= b; k++, n++) {
                        hh = idx;
                        tt = idx;
                        op[n] = new int[]{i, j, k};
                        add(n, i * 9 + j + 1);
                        add(n, 81 + i * 9 + k + 1);
                        add(n, 81 * 2 + j * 9 + k + 1);
                        add(n, 81 * 3 + (i / 3 * 3 + j / 3) * 9 + k + 1);
                    }
                }
            }
            dfs();
            for (int i = 1; i <= top; i++) {
                int[] t = op[ans[i]];
                board[t[0]][t[1]] = (char)(t[2] + '1');
            }
        }

        private void add(int x, int y) {
            row[idx] = x;
            col[idx] = y;
            s[y]++;

            u[idx] = y;
            d[idx] = d[y];
            u[d[y]] = idx;
            d[y] = idx;
            r[hh] = l[tt] = idx;
            l[idx] = hh;
            r[idx] = tt;
            tt = idx++;
        }

        private boolean dfs() {
            if (r[0] == 0) return true;
            int p = r[0];
            for (int i = r[0]; i != 0; i = r[i]) {
                if (s[i] < s[p]) p = i;
            }
            remove(p);
            for (int i = d[p]; i != p; i = d[i]) {
                ans[++top] = row[i];
                for (int j = r[i]; j != i; j = r[j]) remove(col[j]);
                if (dfs()) return true;
                for (int j = l[i]; j != i; j = l[j]) resume(col[j]);
                top--;
            }
            resume(p);
            return false;
        }

        private void remove(int p) {
            r[l[p]] = r[p];
            l[r[p]] = l[p];
            for (int i = d[p]; i != p; i = d[i]) {
                for (int j = r[i]; j != i; j = r[j]) {
                    s[col[j]]--;
                    u[d[j]] = u[j];
                    d[u[j]] = d[j];
                }
            }
        }

        private void resume(int p) {
            for (int i = u[p]; i != p; i = u[i]) {
                for (int j = l[i]; j != i; j = l[j]) {
                    u[d[j]] = j;
                    d[u[j]] = j;
                    s[col[j]]++;
                }
            }
            r[l[p]] = p;
            l[r[p]] = p;
        }

        private void init() {
            for (int i = 0; i <= m; i++) {
                l[i] = i - 1;
                r[i] = i + 1;
                s[i] = 0;
                d[i] = u[i] = i;
            }
            l[0] = m;
            r[m] = 0;
            idx = m + 1;
        }
    }
}
/**
 * 暴力搜搜
 * 不违反规则，我们就继续填
 * 无论填什么不符合规则，那就退回去上一层重新填
 * 遇到死胡同就换，不行就回溯上一层
 * 递归的思想
 * 当前没矛盾的话就move on，有矛盾的话就换一格
 */