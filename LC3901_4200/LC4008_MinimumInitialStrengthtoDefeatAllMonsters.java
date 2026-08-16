package LC3901_4200;

public class LC4008_MinimumInitialStrengthtoDefeatAllMonsters {
    /**
     * You are given an integer array monsters, where monsters[i] represents the strength of the ith monster.
     *
     * You are also given a 2D integer array boosts, where boosts[i] = [li, ri, vi] indicates that vi is added to your
     * temporary bonus while fighting any monster whose index lies in [li, ri]. Boost ranges may overlap, and the values
     * of all applicable boosts are added together.
     *
     * You start with a non-negative initial strength and fight the monsters from left to right.
     *
     * For each monster at index i:
     *
     * Let bonus be the sum of the values of all boosts that apply to monster i.
     * You can defeat the monster only if your current strength plus bonus is at least monsters[i].
     * After defeating the monster, only your current strength decreases by monsters[i]. If it becomes negative, it is
     * set to 0.
     * Return the minimum initial strength required to defeat all monsters.
     *
     * Note: The temporary bonus is used only to determine whether the current monster can be defeated. It does not
     * otherwise change your current strength.
     *
     * Input: monsters = [5,10,15], boosts = [[1,1,10]]
     * Output: 30
     *
     * Input: monsters = [5,10,15], boosts = [[1,2,10],[1,2,5]]
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= monsters.length <= 5 * 10^4
     * 1 <= monsters[i] <= 10^9
     * 0 <= boosts.length <= 5 * 10^4
     * boosts[i] == [li, ri, vi]
     * 0 <= li <= ri < monsters.length
     * 1 <= vi <= 10^9
     * @param monsters
     * @param boosts
     * @return
     */
    // S1: diff + binary search
    // time = O(n + logk), space = O(n)
    public long minInitialStrength(int[] monsters, int[][] boosts) {
        int n = monsters.length;
        long[] b = new long[n + 1];
        for (int[] x : boosts) {
            int l = x[0], r = x[1], v = x[2];
            b[l] += v;
            b[r + 1] -= v;
        }

        long t = 0;
        for (int i = 0; i <= n; i++) {
            t += b[i];
            b[i] = t;
        }

        long l = 0, r = (long)1E14;
        while (l < r) {
            long mid = l + r >> 1;
            if (check(monsters, b, mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check(int[] monsters, long[] b, long mid) {
        int n = monsters.length;
        for (int i = 0; i < n; i++) {
            if (mid + b[i] - monsters[i] < 0) return false;
            mid -= monsters[i];
            if (mid < 0) mid = 0;
        }
        return true;
    }

    // S2: diff + dp
    // time = O(n), space = O(n)
    public long minInitialStrength2(int[] monsters, int[][] boosts) {
        int n = monsters.length;
        long[] b = new long[n + 1];
        for (int[] x : boosts) {
            int l = x[0], r = x[1], v = x[2];
            b[l] += v;
            b[r + 1] -= v;
        }

       for (int i = 0; i < n; i++) b[i + 1] += b[i];
        long res = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (res > 0) res += monsters[i];
            else res = Math.max(monsters[i] - b[i], 0);
        }
        return res;
    }
}