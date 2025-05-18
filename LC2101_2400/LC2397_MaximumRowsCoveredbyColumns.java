package LC2101_2400;

public class LC2397_MaximumRowsCoveredbyColumns {
    /**
     * You are given a 0-indexed m x n binary matrix mat and an integer cols, which denotes the number of columns you
     * must choose.
     *
     * A row is covered by a set of columns if each cell in the row that has a value of 1 also lies in one of the
     * columns of the chosen set.
     *
     * Return the maximum number of rows that can be covered by a set of cols columns.
     *
     * Input: mat = [[0,0,0],[1,0,1],[0,1,1],[0,0,1]], cols = 2
     * Output: 3
     *
     * Input: mat = [[1],[0]], cols = 1
     * Output: 2
     *
     * Constraints:
     *
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 12
     * mat[i][j] is either 0 or 1.
     * 1 <= cols <= n
     */
    // S1
    // time = O(2^n * n), space = O(n)
    int res = 0;
    public int maximumRows(int[][] mat, int cols) {
        int m = mat.length, n = mat[0].length;
        int[] nums = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                nums[i] |= mat[i][j] << (n - 1 - j);
            }
        }

        for (int i = 0; i < m; i++) dfs(nums, i, nums[i], 1, cols);
        return res;
    }

    private void dfs(int[] nums, int cur, int sum, int cnt, int cols) {
        if (Integer.bitCount(sum) > cols) return;
        res = Math.max(res, cnt);

        for (int i = cur + 1; i < nums.length; i++) {
            dfs(nums, i, sum | nums[i], cnt + 1, cols);
        }
    }

    // S2
    // time = O(2^n * m * n), space = O(1)
    public int maximumRows2(int[][] mat, int cols) {
        int m = mat.length, n = mat[0].length, res = 0;
        for (int state = 0; state < (1 << n); state++) {
            int cnt = Integer.bitCount(state);
            if (cnt != cols) continue;

            int total = 0;
            for (int i = 0; i < m; i++) {
                boolean flag = true;
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] == 1 && (state >> j & 1) != 1) {
                        flag = false;
                        break;
                    }
                }
                if (flag) total++;
            }
            res = Math.max(res, total);
        }
        return res;
    }
}