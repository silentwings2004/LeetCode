package LC2700_3000;
import java.util.*;
public class LC2940_FindBuildingWhereAliceandBobCanMeet {
    /**
     * You are given a 0-indexed array heights of positive integers, where heights[i] represents the height of the ith
     * building.
     *
     * If a person is in building i, they can move to any other building j if and only if i < j and heights[i] < heights[j].
     *
     * You are also given another array queries where queries[i] = [ai, bi]. On the ith query, Alice is in building ai
     * while Bob is in building bi.
     *
     * Return an array ans where ans[i] is the index of the leftmost building where Alice and Bob can meet on the ith
     * query. If Alice and Bob cannot move to a common building on query i, set ans[i] to -1.
     *
     * Input: heights = [6,4,8,5,2,7], queries = [[0,1],[0,3],[2,4],[3,4],[2,2]]
     * Output: [2,5,-1,5,2]
     *
     * Input: heights = [5,3,8,2,6,1,4,6], queries = [[0,7],[3,5],[5,2],[3,0],[1,6]]
     * Output: [7,6,-1,4,6]
     *
     * Constraints:
     *
     * 1 <= heights.length <= 5 * 10^4
     * 1 <= heights[i] <= 10^9
     * 1 <= queries.length <= 5 * 10^4
     * queries[i] = [ai, bi]
     * 0 <= ai, bi <= heights.length - 1
     * @param heights
     * @param queries
     * @return
     */
    // S1: TreeMap
    // time = O(nlogn + mlogm), space = O(n + m)
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = heights.length, m = queries.length;
        int[] res = new int[m];
        Arrays.fill(res, -1);
        int[][] q = new int[m][2];
        for (int i = 0; i < m; i++) q[i] = new int[]{Math.max(queries[i][0], queries[i][1]), i};
        Arrays.sort(q, (o1, o2) -> o2[0] - o1[0]);

        TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();
        for (int i = n - 1, j = 0; i >= 0 && j < m; i--) {
            int v = heights[i];
            map.putIfAbsent(v, new TreeSet<>());
            map.get(v).add(i);
            while (map.lowerKey(v) != null) map.remove(map.lowerKey(v));
            while (j < m && i == q[j][0]) {
                int idx = q[j][1], px = queries[idx][0], py = queries[idx][1];
                if (px == py) res[idx] = px;
                else {
                    Integer hk = null;
                    int a = heights[px], b = heights[py];
                    if (a == b) hk = map.higherKey(a);
                    else {
                        if (px < py) {
                            if (a < b) hk = b;
                            else hk = map.higherKey(a);
                        } else {
                            if (a > b) hk = a;
                            else hk = map.higherKey(b);
                        }
                    }
                    if (hk != null) res[idx] = map.get(hk).first();
                }
                j++;
            }
        }
        return res;
    }

    // S2: offline + minHeap
    // time = O(n + mlogm), space O(n + m)
    public int[] leftmostBuildingQueries2(int[] heights, int[][] queries) {
        int n = heights.length, m = queries.length;
        int[] res = new int[m];
        Arrays.fill(res, -1);
        List<int[]>[] l = new List[n];
        for (int i = 0; i < n; i++) l[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int a = queries[i][0], b = queries[i][1];
            if (a > b) {
                int t = a;
                a = b;
                b = t;
            }
            if (a == b || heights[a] < heights[b]) res[i] = b;
            else l[b].add(new int[]{heights[a], i});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        for (int i = 0; i < n; i++) {
            int h = heights[i];
            while (!pq.isEmpty() && pq.peek()[0] < h) {
                res[pq.poll()[1]] = i;
            }
            for (int[] x : l[i]) pq.offer(x);
        }
        return res;
    }
}
/**
 * 1. 离线做法  把询问分组，不按照输入的顺序一个个回答，按照我们自己定义的某种顺序回答
 * - 最小堆
 * - 单调栈
 * 2. 在线做法   按照输入的顺序，一个个回答
 * 基于 RMQ
 * - ST 表 二分
 * - 树状数组
 * - 线段树二分 LC2286
 */
