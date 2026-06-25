package LC3901_4200;

public class LC3964_MinimumLightstoIlluminateaRoad {
    /**
     * You are given an integer array lights of length n, representing positions 0 through n - 1 on a road.
     *
     * For each position i:
     *
     * If lights[i] = v, where v > 0, there is a working bulb at position i that illuminates every position from
     * max(0, i - v) to min(n - 1, i + v), inclusive.
     * If lights[i] = 0, there is no working bulb at position i.
     * A position is visible if it is illuminated by at least one working bulb.
     *
     * You may install additional bulbs at any positions. Each additional bulb installed at position j illuminates
     * positions from max(0, j - 1) to min(n - 1, j + 1), inclusive.
     *
     * Return the minimum number of additional bulbs required to make every position on the road visible.
     *
     * Input: lights = [0,0,0,0]
     * Output: 2
     *
     * Input: lights = [0,0,0,2,0]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= n == lights.length <= 10^5
     * 0 <= lights[i] <= n
     * @param lights
     * @return
     */
    // time = O(n), space = O(n)
    public int minLights(int[] lights) {
        int n = lights.length;
        int[] b = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int v = lights[i];
            if (v > 0) {
                int l = Math.max(0, i - v), r = Math.min(n - 1, i + v);
                b[l]++;
                b[r + 1]--;
            }
        }
        int res = 0;
        for (int i = 0, t = 0; i < n; i++) {
            t += b[i];
            if (t == 0) {
                res++;
                if (i + 1 < n && t + b[i + 1] == 0) {
                    t++;
                    b[Math.min(n, i + 3)]--;
                } else {
                    b[Math.min(n, i + 2)]--;
                    t++;
                }
            }
        }
        return res;
    }
}