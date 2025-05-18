package LC3001_3300;
import java.util.*;
public class LC3275_KthNearestObstacleQueries {
    /**
     * There is an infinite 2D plane.
     *
     * You are given a positive integer k. You are also given a 2D array queries, which contains the following queries:
     *
     * queries[i] = [x, y]: Build an obstacle at coordinate (x, y) in the plane. It is guaranteed that there is no
     * obstacle at this coordinate when this query is made.
     * After each query, you need to find the distance of the kth nearest obstacle from the origin.
     *
     * Return an integer array results where results[i] denotes the kth nearest obstacle after query i, or
     * results[i] == -1 if there are less than k obstacles.
     *
     * Note that initially there are no obstacles anywhere.
     *
     * The distance of an obstacle at coordinate (x, y) from the origin is given by |x| + |y|.
     *
     * Input: queries = [[1,2],[3,4],[2,3],[-3,0]], k = 2
     *
     * Output: [-1,7,5,3]
     *
     * Input: queries = [[5,5],[4,4],[3,3]], k = 1
     *
     * Output: [10,8,6]
     *
     * Constraints:
     *
     * 1 <= queries.length <= 2 * 10^5
     * All queries[i] are unique.
     * -109 <= queries[i][0], queries[i][1] <= 10^9
     * 1 <= k <= 10^5
     * @param queries
     * @param k
     * @return
     */
    // time = O(nlogk), space = O(k)
    public int[] resultsArray(int[][] queries, int k) {
        int n = queries.length;
        int[] res = new int[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < n; i++) {
            int x = queries[i][0], y = queries[i][1];
            int d = Math.abs(x) + Math.abs(y);
            pq.offer(d);
            if (pq.size() > k) pq.poll();
            if (pq.size() < k) res[i] = -1;
            else res[i] = pq.peek();
        }
        return res;
    }
}
/**
 * 需要一个数据结构维护前 k 小的数
 * 支持：插入数据
 * 找到所有数据中最大的数
 * 删除最大的数
 * => 最大堆
 */