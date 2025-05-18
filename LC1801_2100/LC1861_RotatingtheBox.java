package LC1801_2100;
import java.util.*;
public class LC1861_RotatingtheBox {
    /**
     * You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one
     * of the following:
     *
     * A stone '#'
     * A stationary obstacle '*'
     * Empty '.'
     * The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone falls down
     * until it lands on an obstacle, another stone, or the bottom of the box. Gravity does not affect the
     * obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.
     *
     * It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.
     *
     * Return an n x m matrix representing the box after the rotation described above.
     *
     * Input: box = [["#","#","*",".","*","."],
     *               ["#","#","#","*",".","."],
     *               ["#","#","#",".","#","."]]
     * Output: [[".","#","#"],
     *          [".","#","#"],
     *          ["#","#","*"],
     *          ["#","*","."],
     *          ["#",".","*"],
     *          ["#",".","."]]
     *
     * Constraints:
     *
     * m == box.length
     * n == box[i].length
     * 1 <= m, n <= 500
     * box[i][j] is either '#', '*', or '.'.
     * @param box
     * @return
     */
    // time = O(m * n), space = O(1)
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length, n = box[0].length;
        char[][] res = new char[n][m];
        for (int i = 0; i < m; i++) {
            int k = n;
            for (int j = n - 1; j >= 0; j--) {
                if (box[i][j] == '*') k = j;
                else if (box[i][j] == '#') {
                    box[i][j] = '.';
                    box[i][--k] = '#';
                }
            }
            for (int j = 0; j < n; j++) res[j][m - 1 - i] = box[i][j];
        }
        return res;
    }
}
/**
 * 挡板上面有多少个石头，在第二个挡板之前有多少个石头
 * => 对于原数组而言，逐行扫描，在同一行里以挡板为分界点，找上面有多少个石头，再恢复这一行的扫描，看第二个挡板之前有多少个石头
 */

