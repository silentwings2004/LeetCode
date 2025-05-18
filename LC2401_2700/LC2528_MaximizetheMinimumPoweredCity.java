package LC2401_2700;

public class LC2528_MaximizetheMinimumPoweredCity {
    /**
     * You are given a 0-indexed integer array stations of length n, where stations[i] represents the number of power
     * stations in the ith city.
     *
     * Each power station can provide power to every city in a fixed range. In other words, if the range is denoted by
     * r, then a power station at city i can provide power to all cities j such that |i - j| <= r and 0 <= i, j <= n - 1.
     *
     * Note that |x| denotes absolute value. For example, |7 - 5| = 2 and |3 - 10| = 7.
     * The power of a city is the total number of power stations it is being provided power from.
     *
     * The government has sanctioned building k more power stations, each of which can be built in any city, and have
     * the same range as the pre-existing ones.
     *
     * Given the two integers r and k, return the maximum possible minimum power of a city, if the additional power
     * stations are built optimally.
     *
     * Note that you can build the k power stations in multiple cities.
     *
     * Input: stations = [1,2,4,5,0], r = 1, k = 2
     * Output: 5
     *
     * Input: stations = [4,4,4,4], r = 0, k = 3
     * Output: 4
     *
     * Constraints:
     *
     * n == stations.length
     * 1 <= n <= 10^5
     * 0 <= stations[i] <= 10^5
     * 0 <= r <= n - 1
     * 0 <= k <= 10^9
     * @param stations
     * @param r
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + stations[i - 1];

        long left = 0, right = Long.MAX_VALUE;
        while (left < right) {
            long mid = left + right + 1 >> 1;
            if (check(s, r, k, mid)) left = mid;
            else right = mid - 1;
        }
        return right;
    }

    private boolean check(long[] s, int r, int k, long mid) {
        int n = s.length - 1;
        long[] b = new long[n + 2];
        long d = 0, cnt = 0;
        for (int i = 1; i <= n; i++) {
            d += b[i];
            long sum = s[Math.min(n, i + r)] - s[Math.max(0, i - r - 1)];
            long t = mid - sum - d;
            if (t > 0) {
                cnt += t;
                if (cnt > k) return false;
                d += t;
                b[Math.min(n + 1, i + r * 2 + 1)] -= t;
            }
        }
        return true;
    }
}