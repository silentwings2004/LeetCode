package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: GameofLife
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 289. Game of Life
 */
public class LC289_GameofLife {
    /**
     * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton
     * devised by the British mathematician John Horton Conway in 1970."
     *
     * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with
     * its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above
     * Wikipedia article):
     *
     * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
     * Any live cell with two or three live neighbors lives on to the next generation.
     * Any live cell with more than three live neighbors dies, as if by over-population..
     * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     * Write a function to compute the next state (after one update) of the board given its current state. The next
     * state is created by applying the above rules simultaneously to every cell in the current state, where births and
     * deaths occur simultaneously.
     *
     * Example:
     *
     * Input:
     * [
     *   [0,1,0],
     *   [0,0,1],
     *   [1,1,1],
     *   [0,0,0]
     * ]
     * Output:
     * [
     *   [0,0,0],
     *   [1,0,1],
     *   [0,1,1],
     *   [0,1,0]
     * ]
     * Follow up:
     *
     * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some
     * cells first and then use their updated values to update other cells.
     * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause
     * problems when the active area encroaches the border of the array. How would you address these problems?
     * @param board
     */
    // S1: deep copy the board
    // time = O(m * n), space = O(m * n)
    public void gameOfLife(int[][] board) {
        int[] neighbors = {0, -1, 1};

        int rows = board.length;
        int cols = board[0].length;

        int[][] copyBoard = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                copyBoard[i][j] = board[i][j];
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int liveNeighbors = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                            int ii = row + neighbors[i];
                            int jj = col + neighbors[j];
                            if (ii >= 0 && ii < rows && jj >= 0 && jj < cols && copyBoard[ii][jj] == 1) {
                                liveNeighbors++;
                            }
                        }
                    }
                }
                if ((copyBoard[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[row][col] = 0;
                }
                if (copyBoard[row][col] == 0 && liveNeighbors == 3) {
                    board[row][col] = 1;
                }
            }
        }
    }

    // S2: Use extra states
    // time = O(m * n), space = O(1)
    public void gameOfLife2(int[][] board) {
        int[] neighbors = {0, -1, 1};

        int rows = board.length;
        int cols = board[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int liveNeighbors = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                            int ii = row + neighbors[i];
                            int jj = col + neighbors[j];
                            if (ii >= 0 && ii < rows && jj >= 0 && jj < cols && Math.abs(board[ii][jj]) == 1) {
                                liveNeighbors++;
                            }
                        }
                    }
                }
                if ((board[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[row][col] = -1;
                }
                if (board[row][col] == 0 && liveNeighbors == 3) {
                    board[row][col] = 2;
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] > 0) board[i][j] = 1;
                else board[i][j] = 0;
            }
        }
    }
}
