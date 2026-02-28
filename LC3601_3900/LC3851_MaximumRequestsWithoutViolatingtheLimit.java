package LC3601_3900;
import java.util.*;
public class LC3851_MaximumRequestsWithoutViolatingtheLimit {
    /**
     * You are given a 2D integer array requests, where requests[i] = [useri, timei] indicates that useri made a request
     * at timei.
     *
     * You are also given two integers k and window.
     *
     * A user violates the limit if there exists an integer t such that the user makes strictly more than k requests in
     * the inclusive interval [t, t + window].
     *
     * You may drop any number of requests.
     *
     * Return an integer denoting the maximum number of requests that can remain such that
     * no user violates the limit.
     *
     * Input: requests = [[1,1],[2,1],[1,7],[2,8]], k = 1, window = 4
     * Output: 4
     *
     * Input: requests = [[1,2],[1,5],[1,2],[1,6]], k = 2, window = 5
     * Output: 2
     *
     * Input: requests = [[1,1],[2,5],[1,2],[3,9]], k = 1, window = 1
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= requests.length <= 10^5
     * requests[i] = [useri, timei]
     * 1 <= k <= requests.length
     * 1 <= useri, timei, window <= 10^5
     * @param requests
     * @param k
     * @param window
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int maxRequests(int[][] requests, int k, int window) {
        int n = requests.length, res = 0;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int[] r : requests) {
            int id = r[0], t = r[1];
            map.putIfAbsent(id, new ArrayList<>());
            map.get(id).add(t);
        }
        for (List<Integer> v : map.values()) {
            Collections.sort(v);
            res += get(v, k, window);
        }
        return res;
    }

    private int get(List<Integer> q, int k, int window) {
        int n = q.size(), res = n;
        boolean[] st = new boolean[n];
        for (int i = 0, j = 0, t = 0; i < n; i++) {
            t++;
            st[i] = true;
            while (q.get(i) - q.get(j) > window) {
                if (st[j]) t--;
                j++;
            }
            if (t > k) {
                st[i] = false;
                t--;
                res--;
            }
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public int maxRequests2(int[][] requests, int k, int window) {
        int n = requests.length, res = n;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int[] r : requests) {
            int id = r[0], t = r[1];
            map.putIfAbsent(id, new ArrayList<>());
            map.get(id).add(t);
        }
        for (List<Integer> v : map.values()) {
            Collections.sort(v);
            int m = v.size();
            int[] q = new int[m + 1];
            int hh = 0, tt = -1;
            for (int x : v) {
                q[++tt] = x;
                if (tt - hh + 1 > k) {
                    if (q[tt] - q[hh] <= window) {
                        tt--;
                        res--;
                    } else hh++;
                }
            }
        }
        return res;
    }
}