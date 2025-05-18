package LC1201_1500;

public class LC1310_XORQueriesofaSubarray {
    /**
     * You are given an array arr of positive integers. You are also given the array queries where queries[i] =
     * [lefti, righti].
     *
     * For each query i compute the XOR of elements from lefti to righti (that is, arr[lefti] XOR arr[lefti + 1] XOR
     * ... XOR arr[righti] ).
     *
     * Return an array answer where answer[i] is the answer to the ith query.
     *
     * Input: arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
     * Output: [2,7,14,8]
     *
     * Input: arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
     * Output: [8,0,4,4]
     *
     * Constraints:
     *
     * 1 <= arr.length, queries.length <= 3 * 10^4
     * 1 <= arr[i] <= 10^9
     * queries[i].length == 2
     * 0 <= lefti <= righti < arr.length
     * @param arr
     * @param queries
     * @return
     */
    // time = O(n), space = O(n)
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length, m = queries.length;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] ^ arr[i - 1];

        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int l = queries[i][0] + 1, r = queries[i][1] + 1;
            res[i] = s[r] ^ s[l - 1];
        }
        return res;
    }
}