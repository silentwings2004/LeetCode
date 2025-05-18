package LC2401_2700;
import java.util.*;
public class LC2421_NumberofGoodPaths {
    /**
     * There is a tree (i.e. a connected, undirected graph with no cycles) consisting of n nodes numbered from 0 to
     * n - 1 and exactly n - 1 edges.
     *
     * You are given a 0-indexed integer array vals of length n where vals[i] denotes the value of the ith node. You are
     * also given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge
     * connecting nodes ai and bi.
     *
     * A good path is a simple path that satisfies the following conditions:
     *
     * The starting node and the ending node have the same value.
     * All nodes between the starting node and the ending node have values less than or equal to the starting node
     * (i.e. the starting node's value should be the maximum value along the path).
     * Return the number of distinct good paths.
     *
     * Note that a path and its reverse are counted as the same path. For example, 0 -> 1 is considered to be the same
     * as 1 -> 0. A single node is also considered as a valid path.
     *
     * Input: vals = [1,3,2,1,3], edges = [[0,1],[0,2],[2,3],[2,4]]
     * Output: 6
     *
     * Input: vals = [1,1,2,2,3], edges = [[0,1],[1,2],[2,3],[2,4]]
     * Output: 7
     *
     * Input: vals = [1], edges = []
     * Output: 1
     *
     * Constraints:
     *
     * n == vals.length
     * 1 <= n <= 3 * 10^4
     * 0 <= vals[i] <= 10^5
     * edges.length == n - 1
     * edges[i].length == 2
     * 0 <= ai, bi < n
     * ai != bi
     * edges represents a valid tree.
     * @param vals
     * @param edges
     * @return
     */
    // time = O(nlogn), space = O(n)
    int[] p;
    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        p = new int[n];
        int[][] q = new int[n][2];
        for (int i = 0; i < n; i++) {
            p[i] = i;
            q[i] = new int[]{vals[i], i};
        }
        Arrays.sort(q, (o1, o2) -> o1[0] - o2[0]);

        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] x :  edges) {
            int a = x[0], b = x[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        int res = 0;
        for (int i = 0; i < n; i++) { // 按照权值从小到大枚举，能连通的部分一定满足路径上的权值限制
            int j = i + 1;
            while (j < n && vals[q[i][1]] == vals[q[j][1]]) j++;
            for (int k = i; k < j; k++) {
                int x = q[k][1];
                for (int y : graph[x]) {
                    if (vals[x] >= vals[y]) p[find(y)] = find(x); // 满足条件的合并成一个集合
                }
            }

            HashMap<Integer, Integer> map = new HashMap<>();
            for (int k = i; k < j; k++) {
                int fa = find(q[k][1]);
                map.put(fa, map.getOrDefault(fa, 0) + 1); // 将集合分类，能走到且连通的一定在一个集合里
            }

            for (int v : map.values()) res += v * (v + 1) / 2; // 每个集合分别计算所有的可能路径数目
            i = j - 1;
        }
        return res;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}
/**
 * 树分治？
 * 将所有点按权值从小到大排序
 * 所有值相同的点必然在连续的一段
 * 先把左边的点放到树里
 * 当我们将前面权值<= x的点加到树里后，看有多少个点连通 => 连通性问题
 * 有多少对点在一个连通块里 => 并查集
 * 反过来求，先去看每个x在哪个集合里，再将x按照集合去分类 C(k,2) + k => C(k+1,2)
 * time = O(nlogn)
 * Kruskal, AC346 走廊泼水节，逐步将连通块合并到一块
 * 每次合并只能合并权值 <= x的点
 */