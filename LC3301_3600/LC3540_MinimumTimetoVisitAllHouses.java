package LC3301_3600;
import java.util.*;
public class LC3540_MinimumTimetoVisitAllHouses {
    /**
     * You are given two integer arrays forward and backward, both of size n. You are also given another integer array
     * queries.
     *
     * There are n houses arranged in a circle. The houses are connected via roads in a special arrangement:
     *
     * For all 0 <= i <= n - 2, house i is connected to house i + 1 via a road with length forward[i] meters.
     * Additionally, house n - 1 is connected back to house 0 via a road with length forward[n - 1] meters, completing the circle.
     * For all 1 <= i <= n - 1, house i is connected to house i - 1 via a road with length backward[i] meters.
     * Additionally, house 0 is connected back to house n - 1 via a road with length backward[0] meters, completing the
     * circle.
     * You can walk at a pace of one meter per second. Starting from house 0, find the minimum time taken to visit each
     * house in the order specified by queries.
     *
     * Return the minimum total time taken to visit the houses.
     *
     * Input: forward = [1,4,4], backward = [4,1,2], queries = [1,2,0,2]
     * Output: 12
     *
     * Input: forward = [1,1,1,1], backward = [2,2,2,2], queries = [1,2,3,0]
     * Output: 4
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * n == forward.length == backward.length
     * 1 <= forward[i], backward[i] <= 10^5
     * 1 <= queries.length <= 10^5
     * 0 <= queries[i] < n
     * queries[i] != queries[i + 1]
     * queries[0] is not 0.
     * @param forward
     * @param backward
     * @param queries
     * @return
     */
    // time = O(n), space = O(n)
    public long minTotalTime(int[] forward, int[] backward, int[] queries) {
        int n = forward.length;
        long[] s1 = new long[n + 1], s2 = new long[n];
        for (int i = 1; i <= n; i++) s1[i] = s1[i - 1] + forward[i - 1];
        s2[0] = backward[0];
        for (int i = 1; i < n; i++) s2[i] = s2[i - 1] + backward[i];

        long res = 0;
        int cur = 0;
        for (int q : queries) {
            if (cur != q) {
                long t1 = 0, t2 = 0;
                if (cur < q) {
                    t1 = s1[q] - s1[cur];
                    t2 = s2[n - 1] - s2[q] + s2[cur];
                } else {
                    t1 = s1[n] - s1[cur] + s1[q];
                    t2 = s2[cur] - s2[q];
                }
                res += Math.min(t1, t2);
            }
            cur = q;
        }
        return res;
    }
}