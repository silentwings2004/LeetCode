package LC901_1200;

public class LC1176_DietPlanPerformance {
    /**
     * A dieter consumes calories[i] calories on the i-th day.
     *
     * Given an integer k, for every consecutive sequence of k days (calories[i], calories[i+1], ..., calories[i+k-1]
     * for all 0 <= i <= n-k), they look at T, the total calories consumed during that sequence of k days
     * (calories[i] + calories[i+1] + ... + calories[i+k-1]):
     *
     * If T < lower, they performed poorly on their diet and lose 1 point;
     * If T > upper, they performed well on their diet and gain 1 point;
     * Otherwise, they performed normally and there is no change in points.
     * Initially, the dieter has zero points. Return the total number of points the dieter has after dieting for
     * calories.length days.
     *
     * Note that the total points can be negative.
     *
     * Input: calories = [1,2,3,4,5], k = 1, lower = 3, upper = 3
     * Output: 0
     *
     * Input: calories = [3,2], k = 2, lower = 0, upper = 1
     * Output: 1
     *
     * Input: calories = [6,5,0,0], k = 2, lower = 1, upper = 5
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= k <= calories.length <= 10^5
     * 0 <= calories[i] <= 20000
     * 0 <= lower <= upper
     * @param calories
     * @param k
     * @param lower
     * @param upper
     * @return
     */
    // time = O(n), space = O(n)
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int n = calories.length, res = 0;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + calories[i - 1];
        for (int i = 1; i + k - 1 <= n; i++) {
            int t = s[i + k - 1] - s[i - 1];
            if (t < lower) res--;
            else if (t > upper) res++;
        }
        return res;
    }
}
