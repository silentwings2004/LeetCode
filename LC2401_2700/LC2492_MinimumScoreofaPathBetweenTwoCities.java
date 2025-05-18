package LC2401_2700;
import java.util.*;
public class LC2492_MinimumScoreofaPathBetweenTwoCities {
    /**
     * You are given a positive integer n representing n cities numbered from 1 to n. You are also given a 2D array
     * roads where roads[i] = [ai, bi, distancei] indicates that there is a bidirectional road between cities ai and bi
     * with a distance equal to distancei. The cities graph is not necessarily connected.
     *
     * The score of a path between two cities is defined as the minimum distance of a road in this path.
     *
     * Return the minimum possible score of a path between cities 1 and n.
     *
     * Note:
     *
     * A path is a sequence of roads between two cities.
     * It is allowed for a path to contain the same road multiple times, and you can visit cities 1 and n multiple times
     * along the path.
     * The test cases are generated such that there is at least one path between 1 and n.
     *
     * Input: n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
     * Output: 5
     *
     * Input: n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * 1 <= roads.length <= 10^5
     * roads[i].length == 3
     * 1 <= ai, bi <= n
     * ai != bi
     * 1 <= distancei <= 10^4
     * There are no repeated edges.
     * There is at least one path between 1 and n.
     * @param n
     * @param roads
     * @return
     */
    // time = O(nlogn), space = O(n)
    int[] p;
    public int minScore(int n, int[][] roads) {
        p = new int[n];
        int[] w = new int[n];
        Arrays.fill(w, (int) 1e5);
        for (int i = 0; i < n; i++) p[i] = i;
        for (int[] x : roads) {
            int a = find(x[0] - 1), b = find(x[1] - 1), c = x[2];
            if (a != b) p[a] = b;
            w[b] = Math.min(Math.min(w[a], w[b]), c);
        }
        return w[find(0)];
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}
