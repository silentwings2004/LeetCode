package LC3301_3600;

public class LC3443_MaximumManhattanDistanceAfterKChanges {
    /**
     * You are given a string s consisting of the characters 'N', 'S', 'E', and 'W', where s[i] indicates movements in
     * an infinite grid:
     *
     * 'N' : Move north by 1 unit.
     * 'S' : Move south by 1 unit.
     * 'E' : Move east by 1 unit.
     * 'W' : Move west by 1 unit.
     * Initially, you are at the origin (0, 0). You can change at most k characters to any of the four directions.
     *
     * Find the maximum Manhattan distance from the origin that can be achieved at any time while performing the
     * movements in order.
     *
     * The Manhattan Distance between two cells (xi, yi) and (xj, yj) is |xi - xj| + |yi - yj|.
     *
     * Input: s = "NWSE", k = 1
     * Output: 3
     *
     * Input: s = "NSWWEW", k = 3
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * 0 <= k <= s.length
     * s consists of only 'N', 'S', 'E', and 'W'.
     * @param s
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    String dir = "NSEW";
    public int maxDistance(String s, int k) {
        int res = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 2; j < 4; j++) {
                res = Math.max(res, get(s, k, i, j));
            }
        }
        return res;
    }

    private int get(String s, int k, int a, int b) {
        int n = s.length(), res = 0;
        for (int i = 0, v = 0; i < n; i++) {
            char c = s.charAt(i);
            int x = dir.indexOf(c);
            if (x == a || x == b) v++;
            else {
                if (k == 0) v--;
                else {
                    k--;
                    v++;
                }
            }
            res = Math.max(res, v);
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int maxDistance2(String s, int k) {
        int[] cnt = new int[4];
        int n = s.length(), res = 0;
        String dir = "NSEW";
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int u = dir.indexOf(c);
            cnt[u]++;
            int t = Math.abs(cnt[0] - cnt[1]) + Math.abs(cnt[2] - cnt[3]);
            int d = t + Math.min(2 * k, i + 1 - t);
            res = Math.max(res, d);
        }
        return res;
    }
}