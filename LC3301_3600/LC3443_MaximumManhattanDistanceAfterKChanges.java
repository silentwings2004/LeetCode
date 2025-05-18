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
    // time = O(n), space = O(1)
    String d = "NESW", s;
    int k;
    public int maxDistance(String s, int k) {
        this.s = s;
        this.k = k;
        int res = 0;
        res = Math.max(res, work(0, 1));
        res = Math.max(res, work(0, 3));
        res = Math.max(res, work(2, 1));
        res = Math.max(res, work(2, 3));
        return res;
    }

    private int work(int a, int b) {
        int n = s.length(), t = 0, res = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int idx = d.indexOf(c);
            if (idx == a || idx == b) t++;
            else {
                if (cnt < k) {
                    cnt++;
                    t++;
                } else t--;
            }
            res = Math.max(res, t);
        }
        return res;
    }
}