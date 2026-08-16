package LC3901_4200;
import java.util.*;
public class LC4027_ElevatorRequestsIII {
    /**
     * You are given an integer n denoting the number of floors in a building, where the floors are numbered from 0 to
     * n - 1.
     *
     * You are also given an integer start and a 2D integer array requests, where requests[i] = [arrivali, floori]
     * indicates that a request for floori is made at time arrivali.
     *
     * At time 0, the elevator is at floor start.
     *
     * At each second, the elevator may move up by 1 floor, move down by 1 floor, or remain on its current floor.
     *
     * A request can be fulfilled only at or after its arrival time; it is fulfilled instantly when the elevator is on
     * its requested floor at any time from its arrival time onward.
     *
     * Return the minimum time needed to fulfill all requests.
     *
     * Input: n = 9, start = 0, requests = [[0,8],[6,5]]
     * Output: 9
     *
     * Input: n = 8, start = 5, requests = [[1,7],[7,3]]
     * Output: 7
     *
     * Input: n = 7, start = 3, requests = [[0,5],[0,1],[6,3]]
     * Output: 8
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     * 1 <= requests.length <= 16
     * requests[i] == [arrivali, floori]
     * 0 <= arrivali <= 10^9
     * 0 <= start, floori <= n - 1
     * @param n
     * @param start
     * @param requests
     * @return
     */
    // time = O(2^m * m^2), space = O(2^m * m)
    public long elevatorRequests(int n, int start, int[][] requests) {
        final long inf = (long)1E18;
        int m = requests.length;
        long[][] f = new long[1 << m][m];
        for (int i = 0; i < 1 << m; i++) Arrays.fill(f[i], inf);
        for (int i = 0; i < m; i++) {
            int arrival = requests[i][0], floor = requests[i][1];
            int dist = Math.abs(floor - start);
            f[1 << i][i] = Math.max(arrival, dist);
        }
        for (int i = 1; i < 1 << m; i++) {
            for (int j = 0; j < m; j++) {
                if ((i >> j & 1) == 0 || f[i][j] == inf) continue;
                for (int k = 0; k < m; k++) {
                    if ((i >> k & 1) != 0) continue;
                    int arrival = requests[k][0];
                    int dist = Math.abs(requests[k][1] - requests[j][1]);
                    int u = i | 1 << k;
                    f[u][k] = Math.min(f[u][k], Math.max(arrival, f[i][j] + dist));
                }
            }
        }
        long res = inf;
        for (int i = 0; i < m; i++) res = Math.min(res, f[(1 << m) - 1][i]);
        return res;
    }
}