package LC901_1200;

public class LC999_AvailableCapturesforRook {
    /**
     * On an 8 x 8 chessboard, there is exactly one white rook 'R' and some number of white bishops 'B', black pawns
     * 'p', and empty squares '.'.
     *
     * When the rook moves, it chooses one of four cardinal directions (north, east, south, or west), then moves in
     * that direction until it chooses to stop, reaches the edge of the board, captures a black pawn, or is blocked by
     * a white bishop. A rook is considered attacking a pawn if the rook can capture the pawn on the rook's turn. The
     * number of available captures for the white rook is the number of pawns that the rook is attacking.
     *
     * Return the number of available captures for the white rook.
     *
     * Input: board = [[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],
     * [".",".",".","R",".",".",".","p"],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."],
     * [".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
     * Output: 3
     *
     * Input: board = [[".",".",".",".",".",".",".","."],[".","p","p","p","p","p",".","."],
     * [".","p","p","B","p","p",".","."],[".","p","B","R","B","p",".","."],[".","p","p","B","p","p",".","."],
     * [".","p","p","p","p","p",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
     * Output: 0
     *
     * Input: board = [[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],
     * [".",".",".","p",".",".",".","."],["p","p",".","R",".","p","B","."],[".",".",".",".",".",".",".","."],
     * [".",".",".","B",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."]]
     * Output: 3
     *
     * Constraints:
     *
     * board.length == 8
     * board[i].length == 8
     * board[i][j] is either 'R', '.', 'B', or 'p'
     * There is exactly one cell with board[i][j] == 'R'
     * @param board
     * @return
     */
    // time = O(m * n), space = O(1)
    public int numRookCaptures(char[][] board) {
        int m = board.length, n = board[0].length;
        int sx = 0, sy = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'R') {
                    sx = i;
                    sy = j;
                }
            }
        }

        int res = 0;
        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            int x = sx, y = sy;
            while (true) {
                x += dx[i];
                y += dy[i];
                if (x < 0 || x >= m || y < 0 || y >= n) break;
                if (board[x][y] == 'B') break;
                if (board[x][y] == 'p') {
                    res++;
                    break;
                }
            }
        }
        return res;
    }
}