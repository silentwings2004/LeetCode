package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: PacificAtlanticWaterFlow
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 417. Pacific Atlantic Water Flow
 */
public class LC417_PacificAtlanticWaterFlow {
    /**
     * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent,
     * the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and
     * bottom edges.
     * <p>
     * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal
     * or lower.
     * <p>
     * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
     * <p>
     * Note:
     * <p>
     * The order of returned grid coordinates does not matter.
     * Both m and n are less than 150.
     * <p>
     * <p>
     * Example:
     * <p>
     * Given the following 5x5 matrix:
     * <p>
     * Pacific ~   ~   ~   ~   ~
     * ~  1   2   2   3  (5) *
     * ~  3   2   3  (4) (4) *
     * ~  2   4  (5)  3   1  *
     * ~ (6) (7)  1   4   5  *
     * ~ (5)  1   1   2   4  *
     * *   *   *   *   * Atlantic
     * <p>
     * Return:
     * <p>
     * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
     *
     * @param matrix
     * @return
     */
    // time = O(m + n), space = O(m * n)
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return res;
        }
        int row = matrix.length, col = matrix[0].length;
        Queue<Integer> queue = new LinkedList<>();
        boolean[][] pacific = new boolean[row][col];
        boolean[][] atlantic = new boolean[row][col];

        addPacificStartPoints(matrix, pacific, queue);
        bfs(matrix, queue, pacific, atlantic, res);

        addAtlanticStartPoints(matrix, atlantic, queue);
        bfs(matrix, queue, atlantic, pacific, res);

        return res;
    }

    private void bfs(int[][] matrix, Queue<Integer> queue, boolean[][] self, boolean[][] other, List<List<Integer>> res) {
        int row = matrix.length, col = matrix[0].length;
        while (!queue.isEmpty()) {
            // 不需要分层
            int cur = queue.poll();
            int i = cur / col, j = cur % col;
            if (other[i][j]) res.add(Arrays.asList(i, j));
            for (int[] dir : DIRECTIONS) {
                int ii = i + dir[0];
                int jj = j + dir[1];
                if (ii >= 0 && ii < row && jj >= 0 && jj < col && !self[ii][jj] && matrix[i][j] <= matrix[ii][jj]) {
                    queue.offer(ii * col + jj);
                    self[ii][jj] = true;
                }
            }
        }
    }

    private void addPacificStartPoints(int[][] matrix, boolean[][] pacific, Queue<Integer> queue) {
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            pacific[i][0] = true;
            queue.offer(i * col);
        }
        for (int j = 1; j < col; j++) {
            pacific[0][j] = true;
            queue.offer(j);
        }
    }

    private void addAtlanticStartPoints(int[][] matrix, boolean[][] atlantic, Queue<Integer> queue) {
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            atlantic[i][col - 1] = true;
            queue.offer(i * col + col - 1);
        }
        for (int j = 0; j < col - 1; j++) {
            atlantic[row - 1][j] = true;
            queue.offer((row - 1) * col + j);
        }
    }
}