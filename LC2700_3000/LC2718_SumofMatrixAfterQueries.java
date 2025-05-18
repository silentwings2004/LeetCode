package LC2700_3000;

public class LC2718_SumofMatrixAfterQueries {
    /**
     * You are given an integer n and a 0-indexed 2D array queries where queries[i] = [typei, indexi, vali].
     *
     * Initially, there is a 0-indexed n x n matrix filled with 0's. For each query, you must apply one of the following
     * changes:
     *
     * if typei == 0, set the values in the row with indexi to vali, overwriting any previous values.
     * if typei == 1, set the values in the column with indexi to vali, overwriting any previous values.
     * Return the sum of integers in the matrix after all queries are applied.
     *
     * Input: n = 3, queries = [[0,0,1],[1,2,2],[0,2,3],[1,0,4]]
     * Output: 23
     *
     * Input: n = 3, queries = [[0,0,4],[0,1,2],[1,0,1],[0,2,3],[1,2,1]]
     * Output: 17
     *
     * Constraints:
     *
     * 1 <= n <= 10^4
     * 1 <= queries.length <= 5 * 10^4
     * queries[i].length == 3
     * 0 <= typei <= 1
     * 0 <= indexi < n
     * 0 <= vali <= 10^5
     * @param n
     * @param queries
     * @return
     */
    // time = O(m), space = O(n)
    public long matrixSumQueries(int n, int[][] queries) {
        int m = queries.length, r = 0, c = 0;
        boolean[] sr = new boolean[n], sc = new boolean[n];
        long res = 0;
        for (int i = m - 1; i >= 0; i--) {
            int t = queries[i][0], idx = queries[i][1], val = queries[i][2];
            if (t == 0) {
                if (!sr[idx]) {
                    res += (long)(n - c) * val;
                    sr[idx] = true;
                    r++;
                }
            } else {
                if (!sc[idx]) {
                    res += (long)(n - r) * val;
                    sc[idx] = true;
                    c++;
                }
            }
        }
        return res;
    }
}