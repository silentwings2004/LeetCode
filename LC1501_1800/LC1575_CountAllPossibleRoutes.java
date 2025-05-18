package LC1501_1800;
import java.util.*;
public class LC1575_CountAllPossibleRoutes {
    /**
     * You are given an array of distinct positive integers locations where locations[i] represents the position of
     * city i. You are also given integers start, finish and fuel representing the starting city, ending city, and the
     * initial amount of fuel you have, respectively.
     *
     * At each step, if you are at city i, you can pick any city j such that j != i and 0 <= j < locations.length and
     * move to city j. Moving from city i to city j reduces the amount of fuel you have by |locations[i] - locations[j]|.
     * Please notice that |x| denotes the absolute value of x.
     *
     * Notice that fuel cannot become negative at any point in time, and that you are allowed to visit any city more
     * than once (including start and finish).
     *
     * Return the count of all possible routes from start to finish. Since the answer may be too large, return it
     * modulo 10^9 + 7.
     *
     * Input: locations = [2,3,6,8,4], start = 1, finish = 3, fuel = 5
     * Output: 4
     *
     * Input: locations = [4,3,1], start = 1, finish = 0, fuel = 6
     * Output: 5
     *
     * Input: locations = [5,2,1], start = 0, finish = 2, fuel = 3
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= locations.length <= 100
     * 1 <= locations[i] <= 10^9
     * All integers in locations are distinct.
     * 0 <= start, finish < locations.length
     * 1 <= fuel <= 200
     * @param locations
     * @param start
     * @param finish
     * @param fuel
     * @return
     */
    // time = O(n^2 * m), space = O(n * m)
    final int N = 110, M = 210, mod = (int)1e9 + 7;
    int[][] f;
    int[] w;
    int start, finish, n;
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        w = locations;
        this.start = start;
        this.finish = finish;
        n = w.length;
        f = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(f[i],  -1);
        return dp(start, start, fuel);
    }

    private int dp(int u, int v, int tot) {
        if (f[v][tot] != -1) return f[v][tot];

        int res = 0;
        if (v == finish) res++;
        for (int i = 0; i < n; i++) {
            if (v == i) continue;
            int t = Math.abs(w[i] - w[v]);
            if (t <= tot) res = (res + dp(v, i, tot - t)) % mod;
        }
        f[v][tot] = res;
        return res;
    }

    // S2
    // time = O(n^2 * m), space = O(n * m)
    public int countRoutes2(int[] locations, int start, int finish, int fuel) {
        int n = locations.length;
        int[][] f = new int[n][fuel + 1];
        Arrays.fill(f[finish], 1);

        int mod = (int)1e9 + 7;
        for (int j = 0; j <= fuel; j++) {
            for (int i = 0; i < n; i++) {
                for (int k = 0; k < n; k++) {
                    if (i == k) continue;
                    int t = Math.abs(locations[k] - locations[i]);
                    if (t <= j) f[i][j] = (f[i][j] + f[k][j - t]) % mod;
                }
            }
        }
        return f[start][fuel];
    }
}