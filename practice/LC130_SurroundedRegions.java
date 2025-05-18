package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: SurroundedRegions
 * Creater: Silentwings
 * Date: May, 2020
 * Description: 130. Surrounded Regions
 */
public class LC130_SurroundedRegions {
    /**
     * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
     *
     * A region is captured by flipping all 'O's into 'X's in that surrounded region.
     *
     * Example:
     *
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     * After running your function, the board should be:
     *
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     * Explanation:
     *
     * Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not
     * flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped
     * to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
     * @param board
     */
    // S1: BFS
    // time = O(m * n), space = O(k) k为0的个数
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public void solve(char[][] board) {
        // corner case
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }

        int row = board.length, col = board[0].length;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if ((i == 0 || i == row - 1 || j == 0 || j == col - 1) && board[i][j] == 'O') {
                    queue.offer(i * col + j);
                    board[i][j] = 'Y';
                    bfs(board, queue);
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == 'Y') board[i][j] = 'O';
            }
        }
    }

    private void bfs(char[][] board, Queue<Integer> queue) {
        int row = board.length, col = board[0].length;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int i = cur / col, j = cur % col;
            for (int[] dir : DIRECTIONS) {
                int ii = i + dir[0];
                int jj = j + dir[1];
                if (ii >= 0 && ii < row && jj >= 0 && jj < col && board[ii][jj] == 'O') {
                    queue.offer(ii * col + jj);
                    board[ii][jj] = 'Y';
                }
            }
        }
    }
}
