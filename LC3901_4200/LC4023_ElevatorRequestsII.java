package LC3901_4200;
import java.util.*;
public class LC4023_ElevatorRequestsII {
    /**
     * You are given an integer n denoting the number of floors in a building, where the floors are numbered from 0 to
     * n - 1.
     *
     * You are also given an integer start, representing the floor where the elevator begins, and an integer array
     * requests, where requests[i] is a floor that the elevator is requested to reach. All floors in requests are distinct.
     *
     * At time 0, the elevator is on floor start, and all requests are made simultaneously.
     *
     * During each second before all requests are fulfilled, the elevator moves exactly one floor, either up or down. A
     * request is fulfilled instantly when the elevator reaches its requested floor. If start appears in requests, that
     * request is fulfilled at time 0.
     *
     * For each second that a request remains unfulfilled, you receive 1 penalty. Equivalently, a request fulfilled at
     * time t contributes t to the total penalty.
     *
     * Return the minimum total penalty required to fulfill all requests.
     *
     * Input: n = 6, start = 4, requests = [1,5]
     * Output: 6
     *
     * Input: n = 8, start = 3, requests = [3,7,1]
     * Output: 10
     *
     * Input: n = 10, start = 5, requests = [0,2,9]
     * Output: 22
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     * 1 <= requests.length <= 1500
     * 0 <= start, requests[i] <= n - 1
     * All values in requests are distinct.
     * @param n
     * @param start
     * @param requests
     * @return
     */
    // S1
    // time = O(n^2), space = O(n^2)
    public long elevatorRequests(int n, int start, int[] requests) {
        Arrays.sort(requests);
        List<Integer> q = new ArrayList<>();
        for (int x : requests) {
            if (x != start) q.add(x);
        }
        int m = q.size();
        if (m == 0) return 0;

        long inf = (long)1E18;
        long[][][] f = new long[m][m][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(f[i][j], inf);
            }
        }

        for (int i = 0; i < m; i++) {
            long d = Math.abs(q.get(i) - start);
            long v = d * m;
            f[i][i][0] = f[i][i][1] = v;
        }

        for (int len = 1; len <= m; len++) {
            for (int i = 0; i + len - 1 < m; i++) {
                int j = i + len - 1;
                long rem = m - len;
                if (rem == 0) continue;
                if (f[i][j][0] < inf) {
                    long pos = q.get(i);
                    if (i > 0) {
                        long nd = f[i][j][0] + Math.abs(pos - q.get(i - 1)) * rem;
                        f[i - 1][j][0] = Math.min(f[i - 1][j][0], nd);
                    }
                    if (j + 1 < m) {
                        long nd = f[i][j][0] + Math.abs(pos - q.get(j + 1)) * rem;
                        f[i][j + 1][1] = Math.min(f[i][j + 1][1], nd);
                    }
                }
                if (f[i][j][1] < inf) {
                    long pos = q.get(j);
                    if (i > 0) {
                        long nd = f[i][j][1] + Math.abs(pos - q.get(i - 1)) * rem;
                        f[i - 1][j][0] = Math.min(f[i - 1][j][0], nd);
                    }
                    if (j + 1 < m) {
                        long nd = f[i][j][1] + Math.abs(pos - q.get(j + 1)) * rem;
                        f[i][j + 1][1] = Math.min(f[i][j + 1][1], nd);
                    }
                }
            }
        }
        return Math.min(f[0][m - 1][0], f[0][m - 1][1]);
    }

    // S2
    // time = O(m^2), space = O(m^2)
    final long inf = (long)1E18;
    int[] a;
    long[][][] f;
    public long elevatorRequests2(int n, int start, int[] requests) {
        int m = requests.length + 3;
        a = Arrays.copyOf(requests, m);
        a[m - 3] = start;
        a[m - 2] = -1;
        a[m - 1] = n;
        Arrays.sort(a);

        f = new long[m - 1][m - 1][2];
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < m - 1; j++) {
                Arrays.fill(f[i][j], -1);
            }
        }

        int i = Arrays.binarySearch(a, start);
        return dfs(i, i, 0);
    }

    private long dfs(int i, int j, int isRight) {
        int m = a.length;
        if (i == 0 || j == m - 1) return inf;
        if (i == 1 && j == m - 2) return 0;

        if (f[i][j][isRight] != -1) return f[i][j][isRight];

        int x = a[isRight > 0 ? j : i];
        int remain = m - 2 - (j - i + 1);
        long res = Math.min(dfs(i - 1, j, 0) + 1L * (x - a[i - 1]) * remain,
                dfs(i, j + 1, 1) + 1L * (a[j + 1] - x) * remain);
        return f[i][j][isRight] = res;
    }
}
/**
 * 3 -> 9
 * 3 -> 5 -> 9
 * 一步步走，枚举离得最近的楼层
 * [a,   b]
 * [i,j] k = 0,1
 */