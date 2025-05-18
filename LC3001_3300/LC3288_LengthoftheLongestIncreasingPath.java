package LC3001_3300;
import java.util.*;
public class LC3288_LengthoftheLongestIncreasingPath {
    /**
     * You are given a 2D array of integers coordinates of length n and an integer k, where 0 <= k < n.
     *
     * coordinates[i] = [xi, yi] indicates the point (xi, yi) in a 2D plane.
     *
     * An increasing path of length m is defined as a list of points (x1, y1), (x2, y2), (x3, y3), ..., (xm, ym) such
     * that:
     *
     * xi < xi + 1 and yi < yi + 1 for all i where 1 <= i < m.
     * (xi, yi) is in the given coordinates for all i where 1 <= i <= m.
     * Return the maximum length of an increasing path that contains coordinates[k].
     *
     * Input: coordinates = [[3,1],[2,2],[4,1],[0,0],[5,3]], k = 1
     *
     * Output: 3
     *
     * Input: coordinates = [[2,1],[7,0],[5,6]], k = 2
     *
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n == coordinates.length <= 10^5
     * coordinates[i].length == 2
     * 0 <= coordinates[i][0], coordinates[i][1] <= 10^9
     * All elements in coordinates are distinct.
     * 0 <= k <= n - 1
     * @param coordinates
     * @param k
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int maxPathLength(int[][] coordinates, int k) {
        int kx = coordinates[k][0], ky = coordinates[k][1];
        Arrays.sort(coordinates, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);

        List<Integer> q = new ArrayList<>();
        for (int[] c : coordinates) {
            int x = c[0], y = c[1];
            if (x < kx && y < ky || x > kx && y > ky) {
                int p = find(q, y);
                if (p == q.size()) q.add(y);
                else q.set(p, y);
            }
        }
        return q.size() + 1;
    }

    private int find(List<Integer> q, int x) {
        if (q.size() == 0) return 0;
        int l = 0, r = q.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (q.get(mid) >= x) r = mid;
            else l = mid + 1;
        }
        return q.get(r) >= x ? r : r + 1;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public int maxPathLength2(int[][] coordinates, int k) {
        int[] t = coordinates[k];
        int n = coordinates.length, idx = 0;
        Arrays.sort(coordinates, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);
        for (int i = 0; i < n; i++) {
            if (coordinates[i].equals(t)) {
                idx = i;
                break;
            }
        }

        return work(coordinates, idx, idx - 1, 0) + work2(coordinates, idx, idx + 1, n - 1) - 1;
    }

    private int work(int[][] c, int idx, int start, int end) {
        List<Integer> q = new ArrayList<>();
        q.add(c[idx][1]);
        for (int i = start; i >= end; i--) {
            int l = 0, r = q.size() - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (q.get(mid) <= c[i][1]) r = mid;
                else l = mid + 1;

            }
            int pos = q.get(r) <= c[i][1] ? r : r + 1;
            if (pos == 0) continue;
            if (pos == q.size()) q.add(c[i][1]);
            q.set(pos, c[i][1]);
        }
        return q.size();
    }

    private int work2(int[][] c, int idx, int start, int end) {
        List<Integer> q = new ArrayList<>();
        q.add(c[idx][1]);
        for (int i = start; i <= end; i++) {
            int l = 0, r = q.size() - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (q.get(mid) >= c[i][1]) r = mid;
                else l = mid + 1;

            }
            int pos = q.get(r) >= c[i][1] ? r : r + 1;
            if (pos == 0) continue;
            if (pos == q.size()) q.add(c[i][1]);
            q.set(pos, c[i][1]);
        }
        return q.size();
    }
}