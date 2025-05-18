package LC1501_1800;
import java.util.*;
public class LC1595_MinimumCosttoConnectTwoGroupsofPoints {
    /**
     * You are given two groups of points where the first group has size1 points, the second group has size2 points,
     * and size1 >= size2.
     *
     * The cost of the connection between any two points are given in an size1 x size2 matrix where cost[i][j] is the
     * cost of connecting point i of the first group and point j of the second group. The groups are connected if each
     * point in both groups is connected to one or more points in the opposite group. In other words, each point in the
     * first group must be connected to at least one point in the second group, and each point in the second group must
     * be connected to at least one point in the first group.
     *
     * Return the minimum cost it takes to connect the two groups.
     *
     * Input: cost = [[15, 96], [36, 2]]
     * Output: 17
     *
     * Input: cost = [[1, 3, 5], [4, 1, 1], [1, 5, 3]]
     * Output: 4
     *
     * Input: cost = [[2, 5, 1], [3, 4, 7], [8, 1, 2], [6, 2, 4], [3, 8, 8]]
     * Output: 10
     *
     * Constraints:
     *
     * size1 == cost.length
     * size2 == cost[i].length
     * 1 <= size1, size2 <= 12
     * size1 >= size2
     * 0 <= cost[i][j] <= 100
     * @param cost
     * @return
     */
    // time = O(m * n * 2^n), space = O(m * 2^n)
    final int N = 15, INF = 0x3f3f3f3f;
    public int connectTwoGroups(List<List<Integer>> cost) {
        int m = cost.size(), n = cost.get(0).size();
        int[][] f = new int[N][1 << N];
        for (int i = 0; i < N; i++) Arrays.fill(f[i], INF);
        f[0][0] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < 1 << n; j++) {
                for (int k = 0; k < n; k++) {
                    if ((j >> k & 1) == 1) {
                        f[i][j] = Math.min(f[i][j], f[i][j - (1 << k)] + cost.get(i - 1).get(k));
                        f[i][j] = Math.min(f[i][j], f[i - 1][j - (1 << k)] + cost.get(i - 1).get(k));
                        f[i][j] = Math.min(f[i][j], f[i - 1][j] + cost.get(i - 1).get(k));
                    }
                }
            }
        }
        return f[m][(1 << n) - 1];
    }
}