package LC1501_1800;

public class LC1582_SpecialPositionsinaBinaryMatrix {
    /**
     * Given an m x n binary matrix mat, return the number of special positions in mat.
     *
     * A position (i, j) is called special if mat[i][j] == 1 and all other elements in row i and column j are 0 (rows
     * and columns are 0-indexed).
     *
     * Input: mat = [[1,0,0],[0,0,1],[1,0,0]]
     * Output: 1
     *
     * Input: mat = [[1,0,0],[0,1,0],[0,0,1]]
     * Output: 3
     *
     * Constraints:
     *
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 100
     * mat[i][j] is either 0 or 1.
     * @param mat
     * @return
     */
    // S1
    // time = O(m * n * max(m, n)), space = O(1)
    public int numSpecial(int[][] mat) {
        int m = mat.length, n = mat[0].length, res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    boolean flag = true;
                    for (int k = 0; k < n; k++) {
                        if (k == j) continue;
                        if (mat[i][k] == 1) {
                            flag = false;
                            break;
                        }
                    }
                    if (!flag) continue;
                    for (int k = 0; k < m; k++) {
                        if (k == i) continue;
                        if (mat[k][j] == 1) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) res++;
                }
            }
        }
        return res;
    }

    // S1
    // time = O(m * n), space = O(m + n)
    public int numSpecial2(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] r = new int[m], c = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    r[i]++;
                    c[j]++;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1 && r[i] == 1 && c[j] == 1) res++;
            }
        }
        return res;
    }
}
