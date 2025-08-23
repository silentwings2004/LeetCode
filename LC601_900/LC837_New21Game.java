package LC601_900;

public class LC837_New21Game {
    /**
     * Alice plays the following game, loosely based on the card game "21".
     *
     * Alice starts with 0 points and draws numbers while she has less than k points. During each draw, she gains an
     * integer number of points randomly from the range [1, maxPts], where maxPts is an integer. Each draw is
     * independent and the outcomes have equal probabilities.
     *
     * Alice stops drawing numbers when she gets k or more points.
     *
     * Return the probability that Alice has n or fewer points.
     *
     * Answers within 10^-5 of the actual answer are considered accepted.
     *
     * Input: n = 10, k = 1, maxPts = 10
     * Output: 1.00000
     *
     * Input: n = 6, k = 1, maxPts = 10
     * Output: 0.60000
     *
     * Input: n = 21, k = 17, maxPts = 10
     * Output: 0.73278
     *
     * Constraints:
     *
     * 0 <= k <= n <= 10^4
     * 1 <= maxPts <= 10^4
     * @param n
     * @param k
     * @param maxPts
     * @return
     */
    // time = O(n), space = O(n)
    public double new21Game(int n, int k, int maxPts) {
        if (k == 0) return 1;
        double[] f = new double[n + maxPts];
        for (int i = k; i <= n; i++) f[i] = 1;
        for (int i = 1; i <= maxPts; i++) f[k - 1] += f[k + i - 1] / maxPts;
        for (int i = k - 2; i >= 0; i--) f[i] = f[i + 1] + (f[i + 1] - f[i + maxPts + 1]) / maxPts;
        return f[0];
    }
}
/**
 * k~N: 1
 * > N: 0
 * f(i) -> f(i) = 1/w * (f(i+1)+f(i+2)+...+f(i+w))
 * 从后往前递推 => f(0)
 * f(i+1) = 1/w * (f(i+2)+...+f(i+w)+f(i+w+1))   0 <= i + 1 < k => -1 <= i < k - 1
 * f(i)=f(i+1)+1/w*(f(i+1)-f(i+w+1))  when 0 <= i < k - 1
 */