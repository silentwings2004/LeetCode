package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: 01Matrix
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 542. 01 Matrix
 */
public class LC542_01Matrix {
    /**
     * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
     *
     * The distance between two adjacent cells is 1.
     *
     *
     *
     * Example 1:
     *
     * Input:
     * [[0,0,0],
     *  [0,1,0],
     *  [0,0,0]]
     *
     * Output:
     * [[0,0,0],
     *  [0,1,0],
     *  [0,0,0]]
     * Example 2:
     *
     * Input:
     * [[0,0,0],
     *  [0,1,0],
     *  [1,1,1]]
     *
     * Output:
     * [[0,0,0],
     *  [0,1,0],
     *  [1,2,1]]
     *
     *
     * Note:
     *
     * The number of elements of the given matrix will not exceed 10,000.
     * There are at least one 0 in the given matrix.
     * The cells are adjacent in only four directions: up, down, left and right.
     * @param matrix
     * @return
     */
    // 类似LC286，2D Board BFS
    // time = O(m * n), space = O(m * n)
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int[][] updateMatrix(int[][] matrix) {
        // corner case
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return null;
        }
        int row = matrix.length, col = matrix[0].length;
        int[][] res = new int[row][col];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(i * col + j);
                }
            }
        }
        int minLen = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                int i = cur / col, j = cur % col;
                for (int[] dir : DIRECTIONS) {
                    int ii = i + dir[0];
                    int jj = j + dir[1];
                    // 查重条件：防止1被重复访问，若是首次则对应res里的初始值为0，反之若被访问过，则会>0
                    if (ii >= 0 && ii < row && jj >= 0 && jj < col && matrix[ii][jj] == 1 && res[ii][jj] == 0) {
                        queue.offer(ii * col + jj);
                        res[ii][jj] = minLen;
                    }
                }
            }
            minLen++;
        }
        return res;
    }
}
