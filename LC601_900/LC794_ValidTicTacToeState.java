package LC601_900;

public class LC794_ValidTicTacToeState {
    /**
     * Given a Tic-Tac-Toe board as a string array board, return true if and only if it is possible to reach this board
     * position during the course of a valid tic-tac-toe game.
     *
     * The board is a 3 x 3 array that consists of characters ' ', 'X', and 'O'. The ' ' character represents an empty
     * square.
     *
     * Here are the rules of Tic-Tac-Toe:
     *
     * Players take turns placing characters into empty squares ' '.
     * The first player always places 'X' characters, while the second player always places 'O' characters.
     * 'X' and 'O' characters are always placed into empty squares, never filled ones.
     * The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
     * The game also ends if all squares are non-empty.
     * No more moves can be played if the game is over.
     *
     * Input: board = ["O  ","   ","   "]
     * Output: false
     *
     * Constraints:
     *
     * board.length == 3
     * board[i].length == 3
     * board[i][j] is either 'X', 'O', or ' '.
     * @param board
     * @return
     */
    // time = O(1), space = O(1)
    String[] g;
    public boolean validTicTacToe(String[] board) {
        g = board;
        int sx = get('X'), so = get('O');
        boolean cx = check('X'), co = check('O');
        if (cx && co) return false;
        if (cx && sx != so + 1) return false;
        if (co && sx != so) return false;
        if (sx != so && sx != so + 1) return false;
        return true;
    }

    private int get(char c) {
        int res = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (g[i].charAt(j) == c) res++;
            }
        }
        return res;
    }

    private boolean check(char c) {
        for (int i = 0; i < 3; i++) {
            if (g[i].charAt(0) == c && g[i].charAt(1) == c && g[i].charAt(2) == c) return true;
            if (g[0].charAt(i) == c && g[1].charAt(i) == c && g[2].charAt(i) == c) return true;
        }

        if (g[0].charAt(0) == c && g[1].charAt(1) == c && g[2].charAt(2) == c) return true;
        if (g[2].charAt(0) == c && g[1].charAt(1) == c && g[0].charAt(2) == c) return true;
        return false;
    }
}
/**
 * sx so
 * cx co
 * if (cx && co) return false
 * if (cx && sx != so + 1) return false
 * if (co && sx != so) return false
 * if (sx != so && sx != so + 1) return false
 * return true
 */