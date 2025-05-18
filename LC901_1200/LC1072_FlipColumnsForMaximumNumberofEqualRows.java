package LC901_1200;
import java.util.*;
public class LC1072_FlipColumnsForMaximumNumberofEqualRows {
    /**
     * You are given an m x n binary matrix matrix.
     *
     * You can choose any number of columns in the matrix and flip every cell in that column (i.e., Change the value of
     * the cell from 0 to 1 or vice versa).
     *
     * Return the maximum number of rows that have all values equal after some number of flips.
     *
     * Input: matrix = [[0,1],[1,1]]
     * Output: 1
     *
     * Input: matrix = [[0,1],[1,0]]
     * Output: 2
     *
     * Input: matrix = [[0,0,0],[0,0,1],[1,1,0]]
     * Output: 2
     *
     * Constraints:
     *
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 300
     * matrix[i][j] is either 0 or 1.
     * @param matrix
     * @return
     */
    // S1
    // time = O(m ^ 2 * n), space = O(1)
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            int cnt = 1;
            for (int j = i + 1; j < m; j++) {
                if (check(matrix[i], matrix[j])) cnt++;
            }
            res = Math.max(res, cnt);
        }
        return res;
    }

    private boolean check(int[] a, int[] b) {
        int n = a.length;
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (a[i] != b[i]) {
                flag = false;
                break;
            }
        }
        if (flag) return true;
        for (int i = 0; i < n; i++) {
            if ((a[i] ^ b[i]) == 0) return false;
        }
        return true;
    }

    // S2
    // time = O(m * n), space = O(m * n)
    public int maxEqualRowsAfterFlips2(int[][] matrix) {
        HashMap<String, Integer> map = new HashMap<>();
        int res = 0;
        for (int[] x : matrix) {
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (int y : x) {
                sb1.append(y);
                sb2.append(y ^ 1);
            }
            String s1 = sb1.toString(), s2 = sb2.toString();
            map.put(s1, map.getOrDefault(s1, 0) + 1);
            map.put(s2, map.getOrDefault(s2, 0) + 1);
            res = Math.max(res, Math.max(map.get(s1), map.get(s2)));
        }
        return res;
    }
}