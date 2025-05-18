package LC2401_2700;
import java.util.*;
public class LC2477_MinimumFuelCosttoReporttotheCapital {
    /**
     * There is a tree (i.e., a connected, undirected graph with no cycles) structure country network consisting of n
     * cities numbered from 0 to n - 1 and exactly n - 1 roads. The capital city is city 0. You are given a 2D integer
     * array roads where roads[i] = [ai, bi] denotes that there exists a bidirectional road connecting cities ai and bi.
     *
     * There is a meeting for the representatives of each city. The meeting is in the capital city.
     *
     * There is a car in each city. You are given an integer seats that indicates the number of seats in each car.
     *
     * A representative can use the car in their city to travel or change the car and ride with another representative.
     * The cost of traveling between two cities is one liter of fuel.
     *
     * Return the minimum number of liters of fuel to reach the capital city.
     *
     * Input: roads = [[0,1],[0,2],[0,3]], seats = 5
     * Output: 3
     *
     * Input: roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2
     * Output: 7
     *
     * Input: roads = [], seats = 1
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * roads.length == n - 1
     * roads[i].length == 2
     * 0 <= ai, bi < n
     * ai != bi
     * roads represents a valid tree.
     * 1 <= seats <= 10^5
     * @param roads
     * @param seats
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 100010, M = N * 2;
    int[] h, e, ne;
    int idx, seats;
    long res;
    public long minimumFuelCost(int[][] roads, int seats) {
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        Arrays.fill(h, -1);
        idx = 0;
        this.seats = seats;

        for (int[] r : roads) {
            int a = r[0], b = r[1];
            add(a, b);
            add(b, a);
        }
        dfs(0, -1);
        return res;
    }

    private int dfs(int u, int fa) {
        int p = u == 0 ? 0 : 1;
        for (int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i];
            if (j == fa) continue;
            p += dfs(j, u);
        }
        if (u > 0) res += (p + seats - 1) / seats;
        return p;
    }

    private void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}