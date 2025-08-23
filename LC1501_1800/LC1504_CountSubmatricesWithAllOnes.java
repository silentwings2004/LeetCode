package LC1501_1800;
import java.util.*;
public class    LC1504_CountSubmatricesWithAllOnes {
    /**
     * Given an m x n binary matrix mat, return the number of submatrices that have all ones.
     *
     * Input: mat = [[1,0,1],[1,1,0],[1,1,0]]
     * Output: 13
     *
     * Input: mat = [[0,1,1,0],[0,1,1,1],[1,1,1,0]]
     * Output: 24
     *
     * Constraints:
     *
     * 1 <= m, n <= 150
     * mat[i][j] is either 0 or 1.
     * @param mat
     * @return
     */
    // time = O(n * m), space = O(n)
    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] f = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    f[i][j] = 1;
                    if (i > 0) f[i][j] += f[i - 1][j];
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            Stack<int[]> stk = new Stack<>();
            for (int j = 0; j < n; j++) {
                while (!stk.isEmpty() && f[i][stk.peek()[0]] >= f[i][j]) stk.pop();
                int s = 0;
                if (!stk.isEmpty()) s += (j - stk.peek()[0]) * f[i][j] + stk.peek()[1];
                else s += (j + 1) * f[i][j];
                stk.push(new int[]{j, s});
                res += s;
            }
        }
        return res;
    }
}
/**
 * 枚举：
 * 1. 右下角
 * 2. 宽度
 * 对于每个数j，找到左边第一个比它小的数k
 */