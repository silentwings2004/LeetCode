package LC2700_3000;

public class LC2946_MatrixSimilarityAfterCyclicShifts {
    /**
     * You are given a 0-indexed m x n integer matrix mat and an integer k. You have to cyclically right shift odd
     * indexed rows k times and cyclically left shift even indexed rows k times.
     *
     * Return true if the initial and final matrix are exactly the same and false otherwise.
     *
     * Input: mat = [[1,2,1,2],[5,5,5,5],[6,3,6,3]], k = 2
     * Output: true
     *
     * Input: mat = [[2,2],[2,2]], k = 3
     * Output: true
     *
     * Input: mat = [[1,2]], k = 1
     * Output: false
     *
     * Constraints:
     *
     * 1 <= mat.length <= 25
     * 1 <= mat[i].length <= 25
     * 1 <= mat[i][j] <= 25
     * 1 <= k <= 50
     * @param mat
     * @param k
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        k %= n;
        int[][] g = new int[m][n];
        for (int i = 0; i < m; i++) {
            int t = i % 2 == 0 ? k : -k;
            for (int j = 0; j < n; j++) {
                g[i][(j + t + n) % n] = mat[i][j];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] != mat[i][j]) return false;
            }
        }
        return true;
    }
}