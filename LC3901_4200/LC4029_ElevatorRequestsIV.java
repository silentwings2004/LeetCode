package LC3901_4200;
import java.util.*;
public class LC4029_ElevatorRequestsIV {
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
     * 1 <= requests.length <= 500
     * requests[i] == [arrivali, floori]
     * 0 <= arrivali <= 10^9
     * 0 <= start, floori <= n - 1
     * @param n
     * @param start
     * @param requests
     * @return
     */
    // time = O(m^2), space = O(m)
    public long elevatorRequests(int n, int start, int[][] requests) {
        final long inf = (long)1E18;
        int m = requests.length + 2;
        int[][] a = new int[m][2];
        for (int i = 0; i < requests.length; i++) {
            a[i][0] = requests[i][0];
            a[i][1] = requests[i][1];
        }
        a[m - 2] = new int[]{0, -1};
        a[m - 1] = new int[]{0, n};
        Arrays.sort(a, (o1, o2) -> o1[1] - o2[1]);

        long[][] f = new long[m][2];
        for (int i = 0; i < m; i++) Arrays.fill(f[i], inf);

        for (int i = 1; i < m - 1; i++) {
            int t = a[i][0], x = a[i][1];
            for (int j = m - 2; j >= i; j--) {
                int t2 = a[j][0], y = a[j][1];
                if (i == 1 && j == m - 2) {
                    f[j][0] = Math.max(Math.abs(x - start), t);
                    f[j][1] = Math.max(Math.abs(y - start), t2);
                } else {
                    long v1 = f[j][0] + y - a[i - 1][1];
                    long v2 = f[j + 1][1] + a[j + 1][1] - y;
                    long v3 = f[j][0] + x - a[i - 1][1];
                    long v4 = f[j + 1][1] + a[j + 1][1] - x;
                    f[j][1] = Math.min(Math.max(v1, t2), Math.max(v2, t2));
                    f[j][0] = Math.min(Math.max(v3, t), Math.max(v4, t));
                }
            }
        }

        long res = inf;
        for (int i = 1; i < m - 1; i++) res = Math.min(res, f[i][0]);
        return res;
    }
}