package LC1201_1500;
import java.util.*;
public class LC1409_QueriesonaPermutationWithKey {
    /**
     * Given the array queries of positive integers between 1 and m, you have to process all queries[i] (from i=0 to
     * i=queries.length-1) according to the following rules:
     *
     * In the beginning, you have the permutation P=[1,2,3,...,m].
     * For the current i, find the position of queries[i] in the permutation P (indexing from 0) and then move this at
     * the beginning of the permutation P. Notice that the position of queries[i] in P is the result for queries[i].
     * Return an array containing the result for the given queries.
     *
     * Input: queries = [3,1,2,1], m = 5
     * Output: [2,1,2,1]
     *
     * Input: queries = [4,1,2,2], m = 4
     * Output: [3,1,2,0]
     *
     * Input: queries = [7,5,5,8,3], m = 8
     * Output: [6,5,0,7,5]
     *
     * Constraints:
     *
     * 1 <= m <= 10^3
     * 1 <= queries.length <= m
     * 1 <= queries[i] <= m
     * @param queries
     * @param m
     * @return
     */
    // S1
    // time = O(n * m), space = O(m)
    public int[] processQueries(int[] queries, int m) {
        List<Integer> q = new LinkedList<>();
        for (int i = 1; i <= m; i++) q.add(i);
        int n = queries.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int x = queries[i];
            for (int j = 0; j < q.size(); j++) {
                if (q.get(j) == x) {
                    res[i] = j;
                    break;
                }
            }
            q.remove(res[i]);
            q.add(0, x);
        }
        return res;
    }

    // S2
    // time = O(m * n), space = O(m)
    public int[] processQueries2(int[] queries, int m) {
        int n = queries.length;
        int[] pos = new int[m + 1], res = new int[n];
        for (int i = 1; i <= m; i++) pos[i] = i - 1;
        for (int i = 0; i < n; i++) {
            res[i] = pos[queries[i]];
            for (int j = 0; j <= m; j++) {
                if (pos[j] < res[i]) pos[j]++;
            }
            pos[queries[i]] = 0;
        }
        return res;
    }
}