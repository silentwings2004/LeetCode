package LC2401_2700;

public class LC2585_NumberofWaystoEarnPoints {
    /**
     * There is a test that has n types of questions. You are given an integer target and a 0-indexed 2D integer array
     * types where types[i] = [counti, marksi] indicates that there are counti questions of the ith type, and each one
     * of them is worth marksi points.
     *
     * Return the number of ways you can earn exactly target points in the exam. Since the answer may be too large,
     * return it modulo 109 + 7.
     *
     * Note that questions of the same type are indistinguishable.
     *
     * For example, if there are 3 questions of the same type, then solving the 1st and 2nd questions is the same as
     * solving the 1st and 3rd questions, or the 2nd and 3rd questions.
     *
     * Input: target = 6, types = [[6,1],[3,2],[2,3]]
     * Output: 7
     *
     * Input: target = 5, types = [[50,1],[50,2],[50,5]]
     * Output: 4
     *
     * Input: target = 18, types = [[6,1],[3,2],[2,3]]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= target <= 1000
     * n == types.length
     * 1 <= n <= 50
     * types[i].length == 2
     * 1 <= counti, marksi <= 50
     * @param target
     * @param types
     * @return
     */
    // time = O(n * t * k), space = o(n * t)
    public int waysToReachTarget(int target, int[][] types) {
        int n = types.length;
        long[][] f = new long[n + 1][target + 1];
        f[0][0] = 1;

        long mod = (long)(1e9 + 7);
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                for (int k = 0; k <= types[i - 1][0] && k * types[i - 1][1] <= j; k++) {
                    f[i][j] = (f[i][j] + f[i - 1][j - k * types[i - 1][1]]) % mod;
                }
            }
        }
        return (int) f[n][target];
    }
}