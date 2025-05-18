package LC1201_1500;

public class LC1230_TossStrangeCoins {
    /**
     * You have some coins.  The i-th coin has a probability prob[i] of facing heads when tossed.
     *
     * Return the probability that the number of coins facing heads equals target if you toss every coin exactly once.
     *
     * Input: prob = [0.4], target = 1
     * Output: 0.40000
     *
     * Input: prob = [0.5,0.5,0.5,0.5,0.5], target = 0
     * Output: 0.03125
     *
     * Constraints:
     *
     * 1 <= prob.length <= 1000
     * 0 <= prob[i] <= 1
     * 0 <= target <= prob.length
     * Answers will be accepted as correct if they are within 10^-5 of the correct answer.
     * @param prob
     * @param target
     * @return
     */
    // S1
    // time = O(n^2), space = O(n^2)
    public double probabilityOfHeads(double[] prob, int target) {
        int n = prob.length;
        double[][] f = new double[n][target + 1];
        f[0][0] = 1 - prob[0];
        if (target > 0) f[0][1] = prob[0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= Math.min(i + 1, target); j++) {
                if (j > 0) f[i][j] = f[i - 1][j - 1] * prob[i];
                f[i][j] += f[i - 1][j] * (1 - prob[i]);
            }
        }
        return f[n - 1][target];
    }

    // S1.2: dp
    // time = O(n^2), space = O(n^2)
    public double probabilityOfHeads2(double[] prob, int target) {
        int n = prob.length;
        double[][] f = new double[n + 1][target + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, target); j++) {
                if (j > 0) f[i][j] = f[i - 1][j - 1] * prob[i - 1];
                if (j <= i - 1) f[i][j] += f[i - 1][j] * (1 - prob[i - 1]);
            }
        }
        return f[n][target];
    }

    // S3
    // time = O(n^2), space = O(n)
    public double probabilityOfHeads3(double[] prob, int target) {
        double[] f = new double[target + 1];
        f[0] = 1.0;
        int n = prob.length;
        for (int i = 0; i < n; i++) {
            for (int j = Math.min(i + 1, target); j >= 0; j--) {
                f[j] = (j > 0 ? f[j - 1] : 0) * prob[i] + f[j] * (1 - prob[i]);
            }
        }
        return f[target];
    }
}