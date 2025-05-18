package LC2401_2700;
import java.util.*;
public class LC2662_MinimumCostofaPathWithSpecialRoads {
    /**
     * You are given an array start where start = [startX, startY] represents your initial position (startX, startY) in
     * a 2D space. You are also given the array target where target = [targetX, targetY] represents your target position
     * (targetX, targetY).
     *
     * The cost of going from a position (x1, y1) to any other position in the space (x2, y2) is |x2 - x1| + |y2 - y1|.
     *
     * There are also some special roads. You are given a 2D array specialRoads where specialRoads[i] = [x1i, y1i, x2i,
     * y2i, costi] indicates that the ith special road can take you from (x1i, y1i) to (x2i, y2i) with a cost equal to
     * costi. You can use each special road any number of times.
     *
     * Return the minimum cost required to go from (startX, startY) to (targetX, targetY).
     *
     * Input: start = [1,1], target = [4,5], specialRoads = [[1,2,3,3,2],[3,4,4,5,1]]
     * Output: 5
     *
     * Input: start = [3,2], target = [5,7], specialRoads = [[3,2,3,4,4],[3,3,5,5,5],[3,4,5,6,6]]
     * Output: 7
     *
     * Constraints:
     *
     * start.length == target.length == 2
     * 1 <= startX <= targetX <= 10^5
     * 1 <= startY <= targetY <= 10^5
     * 1 <= specialRoads.length <= 200
     * specialRoads[i].length == 5
     * startX <= x1i, x2i <= targetX
     * startY <= y1i, y2i <= targetY
     * 1 <= costi <= 10^5
     * @param start
     * @param target
     * @param specialRoads
     * @return
     */
    // time = O(nlogn), space = O(n)
    final int N = 100010, inf = 0x3f3f3f3f;
    int n;
    int[][] g;
    int[] dist;
    boolean[] st;
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        List<int[]> p = new ArrayList<>();
        p.add(start);
        p.add(target);
        for (int[] x : specialRoads) {
            p.add(new int[]{x[0], x[1]});
            p.add(new int[]{x[2], x[3]});
        }

        HashMap<Long, Integer> map = new HashMap<>();
        int idx = 0;
        for (int[] x : p) {
            long key = get(x[0], x[1]);
            if (map.containsKey(key)) continue;
            map.put(key, idx++);
        }

        n = map.size();
        g = new int[n][n];
        dist = new int[n];
        st = new boolean[n];

        for (int i = 0; i < n; i++) Arrays.fill(g[i], inf);
        for (int i = 0; i < p.size(); i++) {
            for (int j = i; j < p.size(); j++) {
                int a = map.get(get(p.get(i)[0], p.get(i)[1]));
                int b = map.get(get(p.get(j)[0], p.get(j)[1]));
                g[a][b] = g[b][a] = cal(p.get(i)[0], p.get(i)[1], p.get(j)[0], p.get(j)[1]);
            }
        }

        for (int[] x : specialRoads) {
            int a = map.get(get(x[0], x[1])), b = map.get(get(x[2], x[3])), c = x[4];
            g[a][b] = Math.min(g[a][b], c);
        }

        dijkstra(map.get(get(start[0], start[1])));
        int res = dist[map.get(get(target[0], target[1]))];
        return res == inf ? -1 : res;
    }

    private void dijkstra(int start) {
        Arrays.fill(dist, inf);
        dist[start] = 0;

        for (int i = 0; i < n; i++) {
            int t = -1;
            for (int j = 0; j < n; j++) {
                if (!st[j] && (t == -1 || dist[t] > dist[j])) t = j;
            }
            st[t] = true;
            for (int j = 0; j < n; j++) {
                dist[j] = Math.min(dist[j], dist[t] + g[t][j]);
            }
        }
    }

    private long get(int x, int y) {
        return 1L * x * N + y;
    }

    private int cal(int x1, int y1, int x2, int y2) {
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
    }
}