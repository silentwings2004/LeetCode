package LC2700_3000;
import java.util.*;
public class LC2959_NumberofPossibleSetsofClosingBranches {
    /**
     * There is a company with n branches across the country, some of which are connected by roads. Initially, all
     * branches are reachable from each other by traveling some roads.
     *
     * The company has realized that they are spending an excessive amount of time traveling between their branches. As
     * a result, they have decided to close down some of these branches (possibly none). However, they want to ensure
     * that the remaining branches have a distance of at most maxDistance from each other.
     *
     * The distance between two branches is the minimum total traveled length needed to reach one branch from another.
     *
     * You are given integers n, maxDistance, and a 0-indexed 2D array roads, where roads[i] = [ui, vi, wi] represents
     * the undirected road between branches ui and vi with length wi.
     *
     * Return the number of possible sets of closing branches, so that any branch has a distance of at most maxDistance
     * from any other.
     *
     * Note that, after closing a branch, the company will no longer have access to any roads connected to it.
     *
     * Note that, multiple roads are allowed.
     *
     * Input: n = 3, maxDistance = 5, roads = [[0,1,2],[1,2,10],[0,2,10]]
     * Output: 5
     *
     * Input: n = 3, maxDistance = 5, roads = [[0,1,20],[0,1,10],[1,2,2],[0,2,2]]
     * Output: 7
     *
     * Input: n = 1, maxDistance = 10, roads = []
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n <= 10
     * 1 <= maxDistance <= 10^5
     * 0 <= roads.length <= 1000
     * roads[i].length == 3
     * 0 <= ui, vi <= n - 1
     * ui != vi
     * 1 <= wi <= 1000
     * All branches are reachable from each other by traveling some roads.
     * @param n
     * @param maxDistance
     * @param roads
     * @return
     */
    // time = O(2^n * n^3), space = O(n^2)
    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        final int inf = 0x3f3f3f3f;
        int res = 0;
        for (int state = 0; state < 1 << n; state++) {
            int[][] dist = new int[n][n];
            for (int i = 0; i < n; i++) Arrays.fill(dist[i], inf);
            for (int i = 0; i < n; i++) dist[i][i] = 0;
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if ((state >> i & 1) == 0) set.add(i);
            }
            for (int[] r : roads) {
                int a = r[0], b = r[1], c = r[2];
                if (!set.contains(a) && !set.contains(b)) {
                    dist[a][b] = dist[b][a] = Math.min(dist[a][b], c);
                }
            }
            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
            int md = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!set.contains(i) && !set.contains(j)) {
                        md = Math.max(md, dist[i][j]);
                    }
                }
            }
            if (md <= maxDistance) res++;
        }
        return res;
    }
}