package LC3001_3300;

public class LC3279_MaximumTotalAreaOccupiedbyPistons {
    /**
     * There are several pistons in an old car engine, and we want to calculate the maximum possible area under the
     * pistons.
     *
     * You are given:
     *
     * An integer height, representing the maximum height a piston can reach.
     * An integer array positions, where positions[i] is the current position of piston i, which is equal to the
     * current area under it.
     * A string directions, where directions[i] is the current moving direction of piston i, 'U' for up, and 'D' for
     * down.
     * Each second:
     *
     * Every piston moves in its current direction 1 unit. e.g., if the direction is up, positions[i] is incremented by
     * 1.
     * If a piston has reached one of the ends, i.e., positions[i] == 0 or positions[i] == height, its direction will
     * change.
     * Return the maximum possible area under all the pistons.
     *
     * Input: height = 5, positions = [2,5], directions = "UD"
     * Output: 7
     *
     * Input: height = 6, positions = [0,0,6,3], directions = "UUDU"
     * Output: 15
     *
     * Constraints:
     *
     * 1 <= height <= 10^6
     * 1 <= positions.length == directions.length <= 10^5
     * 0 <= positions[i] <= height
     * directions[i] is either 'U' or 'D'.
     * @param height
     * @param positions
     * @param directions
     * @return
     */
    // time = O(n), space = O(n)
    public long maxArea(int height, int[] positions, String directions) {
        int n = positions.length, h = height;
        long[] t = new long[2 * height + 2], s = new long[2 * height + 2];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            int x = positions[i];
            sum += x;
            if (directions.charAt(i) == 'U') {
                t[1]++;
                t[h - x + 1] -= 2;
                t[h - x + 1 + h] += 2;
            } else {
                t[1]--;
                t[x + 1] += 2;
                t[x + 1 + h] -= 2;
            }
        }
        long res = 0;
        for (int i = 1; i < t.length; i++) {
            t[i] += t[i - 1];
            s[i] = s[i - 1] + t[i];
            res = Math.max(res, sum + s[i]);
        }
        return res;
    }
}