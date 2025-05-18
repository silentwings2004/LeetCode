package LC2700_3000;
import java.util.*;
public class LC2747_CountZeroRequestServers {
    /**
     * You are given an integer n denoting the total number of servers and a 2D 0-indexed integer array logs, where
     * logs[i] = [server_id, time] denotes that the server with id server_id received a request at time time.
     *
     * You are also given an integer x and a 0-indexed integer array queries.
     *
     * Return a 0-indexed integer array arr of length queries.length where arr[i] represents the number of servers that
     * did not receive any requests during the time interval [queries[i] - x, queries[i]].
     *
     * Note that the time intervals are inclusive.
     *
     * Input: n = 3, logs = [[1,3],[2,6],[1,5]], x = 5, queries = [10,11]
     * Output: [1,2]
     *
     * Input: n = 3, logs = [[2,4],[2,1],[1,2],[3,1]], x = 2, queries = [3,4]
     * Output: [0,1]
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * 1 <= logs.length <= 10^5
     * 1 <= queries.length <= 10^5
     * logs[i].length == 2
     * 1 <= logs[i][0] <= n
     * 1 <= logs[i][1] <= 10^6
     * 1 <= x <= 10^5
     * x < queries[i] <= 10^6
     * @param n
     * @param logs
     * @param x
     * @param queries
     * @return
     */
    // time = O(nlogn + mlogm), space = O(n)
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[][] q = new int[m][2];
        for (int i = 0; i < m; i++) q[i] = new int[]{queries[i], i};
        Arrays.sort(q, (o1, o2) -> o1[0] - o2[0]);
        Arrays.sort(logs, (o1, o2) -> o1[1] - o2[1]);

        HashMap<Integer, Integer> map = new HashMap<>();
        int[] res = new int[m];
        for (int i = 0, j = 0, k = 0; i < m; i++) {
            int l = Math.max(0, q[i][0] - x), r = q[i][0];
            while (j < logs.length && logs[j][1] <= r) {
                int id = logs[j][0];
                map.put(id, map.getOrDefault(id, 0) + 1);
                j++;
            }
            while (k < j && logs[k][1] < l) {
                int id = logs[k][0];
                map.put(id, map.get(id) - 1);
                if (map.get(id) == 0) map.remove(id);
                k++;
            }
            res[q[i][1]] = n - map.size();
        }
        return res;
    }
}