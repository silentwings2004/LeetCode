package LC301_600;

public class LC598_RangeAdditionII {
    /**
     * You are given an m x n matrix M initialized with all 0's and an array of operations ops, where ops[i] = [ai, bi]
     * means M[x][y] should be incremented by one for all 0 <= x < ai and 0 <= y < bi.
     *
     * Count and return the number of maximum integers in the matrix after performing all the operations.
     *
     * Input: m = 3, n = 3, ops = [[2,2],[3,3]]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= m, n <= 4 * 10^4
     * 1 <= ops.length <= 10^4
     * ops[i].length == 2
     * 1 <= ai <= m
     * 1 <= bi <= n
     * @param m
     * @param n
     * @param ops
     * @return
     */
    // time = O(n), space = O(1)
    public int maxCount(int m, int n, int[][] ops) {
        int x = m, y = n;
        for (int[] p : ops) {
            x = Math.min(x, p[0]);
            y = Math.min(y, p[1]);
        }
        return x * y;
    }
}
/**
 * 左上角这个点每次都会被加到
 * 问题等价为问有多少个值每次都会被加到，和左上角这个点一样？
 * => 所有子矩阵操作的交集
 */