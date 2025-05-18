package LC1501_1800;

public class LC1742_MaximumNumberofBallsinaBox {
    /**
     * You are working in a ball factory where you have n balls numbered from lowLimit up to highLimit inclusive (i.e.,
     * n == highLimit - lowLimit + 1), and an infinite number of boxes numbered from 1 to infinity.
     *
     * Your job at this factory is to put each ball in the box with a number equal to the sum of digits of the ball's
     * number. For example, the ball number 321 will be put in the box number 3 + 2 + 1 = 6 and the ball number 10 will
     * be put in the box number 1 + 0 = 1.
     *
     * Given two integers lowLimit and highLimit, return the number of balls in the box with the most balls.
     *
     * Input: lowLimit = 1, highLimit = 10
     * Output: 2
     *
     * Input: lowLimit = 5, highLimit = 15
     * Output: 2
     *
     * Input: lowLimit = 19, highLimit = 28
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= lowLimit <= highLimit <= 10^5
     */
    // time = O(nlogn), space = O(1)
    public int countBalls(int lowLimit, int highLimit) {
        int[] cnt = new int[50];
        int res = 0;
        for (int i = lowLimit; i <= highLimit; i++) {
            int t = i, v = 0;
            while (t > 0) {
                v += t % 10;
                t /= 10;
            }
            cnt[v]++;
            res = Math.max(res, cnt[v]);
        }
        return res;
    }
}
