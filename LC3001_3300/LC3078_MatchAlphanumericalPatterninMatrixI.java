package LC3001_3300;
import java.util.*;
public class LC3078_MatchAlphanumericalPatterninMatrixI {
    /**
     * You are given a 2D integer matrix board and a 2D character matrix pattern. Where 0 <= board[r][c] <= 9 and each
     * element of pattern is either a digit or a lowercase English letter.
     *
     * Your task is to find a
     * submatrix
     *  of board that matches pattern.
     *
     * An integer matrix part matches pattern if we can replace cells containing letters in pattern with some digits
     * (each distinct letter with a unique digit) in such a way that the resulting matrix becomes identical to the
     * integer matrix part. In other words,
     *
     * The matrices have identical dimensions.
     * If pattern[r][c] is a digit, then part[r][c] must be the same digit.
     * If pattern[r][c] is a letter x:
     * For every pattern[i][j] == x, part[i][j] must be the same as part[r][c].
     * For every pattern[i][j] != x, part[i][j] must be different than part[r][c].
     * Return an array of length 2 containing the row number and column number of the upper-left corner of a submatrix
     * of board which matches pattern. If there is more than one such submatrix, return the coordinates of the
     * submatrix with the lowest row index, and in case there is still a tie, return the coordinates of the submatrix
     * with the lowest column index. If there are no suitable answers, return [-1, -1].
     *
     * Input: board = [[1,2,2],[2,2,3],[2,3,3]], pattern = ["ab","bb"]
     * Output: [0,0]
     *
     * Input: board = [[1,1,2],[3,3,4],[6,6,6]], pattern = ["ab","66"]
     * Output: [1,1]
     *
     * Input: board = [[1,2],[2,1]], pattern = ["xx"]
     * Output: [-1,-1]
     *
     * Constraints:
     *
     * 1 <= board.length <= 50
     * 1 <= board[i].length <= 50
     * 0 <= board[i][j] <= 9
     * 1 <= pattern.length <= 50
     * 1 <= pattern[i].length <= 50
     * pattern[i][j] is either a digit represented as a string or a lowercase English letter.
     * @param board
     * @param pattern
     * @return
     */
    // time = O(m * n * a * b), space = O(1)
    public int[] findPattern(int[][] board, String[] pattern) {
        int m = board.length, n = board[0].length, a = pattern.length, b = pattern[0].length();
        for (int i = 0; i + a - 1 < m; i++) {
            for (int j = 0; j + b - 1 < n; j++) {
                if (check(board, i, j, pattern)) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    private boolean check(int[][] board, int x, int y, String[] pattern) {
        int m = pattern.length, n = pattern[0].length();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int a = board[x + i][y + j], b = pattern[i].charAt(j) - '0';
                if (Character.isDigit(pattern[i].charAt(j))) {
                    if (a != b) return false;
                } else {
                    if (map.containsKey(b) && map.get(b) != a) return false;
                    if (map.containsKey(a) && map.get(a) != b) return false;
                    map.put(a, b);
                    map.put(b, a);
                }
            }
        }
        return true;
    }
}