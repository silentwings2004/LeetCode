package LC3001_3300;
import java.util.*;
public class LC3160_FindtheNumberofDistinctColorsAmongtheBalls {
    /**
     * You are given an integer limit and a 2D array queries of size n x 2.
     *
     * There are limit + 1 balls with distinct labels in the range [0, limit]. Initially, all balls are uncolored.
     * For every query in queries that is of the form [x, y], you mark ball x with the color y. After each query, you
     * need to find the number of distinct colors among the balls.
     *
     * Return an array result of length n, where result[i] denotes the number of distinct colors after ith query.
     *
     * Note that when answering a query, lack of a color will not be considered as a color.
     *
     * Input: limit = 4, queries = [[1,4],[2,5],[1,3],[3,4]]
     * Output: [1,2,2,3]
     *
     * Input: limit = 4, queries = [[0,1],[1,2],[2,2],[3,4],[4,5]]
     * Output: [1,2,2,3,4]
     *
     * Constraints:
     *
     * 1 <= limit <= 10^9
     * 1 <= n == queries.length <= 10^5
     * queries[i].length == 2
     * 0 <= queries[i][0] <= limit
     * 1 <= queries[i][1] <= 10^9
     * @param limit
     * @param queries
     * @return
     */
    // time = O(n), space = O(n)
    public int[] queryResults(int limit, int[][] queries) {
        int n = queries.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> cnt = new HashMap<>();

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int x = queries[i][0], y = queries[i][1];
            if (!map.containsKey(x)) {
                map.put(x, y);
                cnt.put(y, cnt.getOrDefault(y, 0) + 1);
            } else {
                int c = map.get(x);
                cnt.put(c, cnt.get(c) - 1);
                if (cnt.get(c) == 0) cnt.remove(c);
                map.put(x, y);
                cnt.put(y, cnt.getOrDefault(y, 0) + 1);
            }
            res[i] = cnt.size();
        }
        return res;
    }
}