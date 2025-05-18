package LC3001_3300;
import java.util.*;
public class LC3244_ShortestDistanceAfterRoadAdditionQueriesII {
    /**
     * You are given an integer n and a 2D integer array queries.
     *
     * There are n cities numbered from 0 to n - 1. Initially, there is a unidirectional road from city i to city i + 1
     * for all 0 <= i < n - 1.
     *
     * queries[i] = [ui, vi] represents the addition of a new unidirectional road from city ui to city vi. After each
     * query, you need to find the length of the shortest path from city 0 to city n - 1.
     *
     * There are no two queries such that queries[i][0] < queries[j][0] < queries[i][1] < queries[j][1].
     *
     * Return an array answer where for each i in the range [0, queries.length - 1], answer[i] is the length of the
     * shortest path from city 0 to city n - 1 after processing the first i + 1 queries.
     *
     * Input: n = 5, queries = [[2,4],[0,2],[0,4]]
     *
     * Output: [3,2,1]
     *
     * Input: n = 4, queries = [[0,3],[0,2]]
     *
     * Output: [1,1]
     *
     * Constraints:
     *
     * 3 <= n <= 105
     * 1 <= queries.length <= 10^5
     * queries[i].length == 2
     * 0 <= queries[i][0] < queries[i][1] < n
     * 1 < queries[i][1] - queries[i][0]
     * There are no repeated roads among the queries.
     * @param n
     * @param queries
     * @return
     */
    // S1: TreeSet
    // time = O(mlogn), space = O(m + n)
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int m = queries.length;
        int[] res = new int[m];
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) set.add(i);
        for (int i = 0; i < m; i++) {
            int x = queries[i][0], y = queries[i][1];
            Integer ck = set.ceiling(x + 1);
            while (ck != null && ck < y) {
                set.remove(ck);
                ck = set.ceiling(ck);
            }
            res[i] = set.size() - 1;
        }
        return res;
    }

    // S2: Union Find
    // time = O(n + q), space = O(n)
    int[] p;
    public int[] shortestDistanceAfterQueries2(int n, int[][] queries) {
        p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
        int m = queries.length, cnt = n - 1;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int l = queries[i][0], r = queries[i][1] - 1;
            for (int j = find(l); j < r; j = find(j + 1)) {
                p[find(j)] = find(r);
                cnt--;
            }
            res[i] = cnt;
        }
        return res;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}
/**
 * 连一条从 L 到 R 的边，相当于把并查集中的节点 L,L+1,L+2⋯,R−2 合并到并查集中的节点 R−1 上。
 */