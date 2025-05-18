package LC1201_1500;

public class LC1467_ProbabilityofaTwoBoxesHavingTheSameNumberofDistinctBalls {
    /**
     * Given 2n balls of k distinct colors. You will be given an integer array balls of size k where balls[i] is the
     * number of balls of color i.
     *
     * All the balls will be shuffled uniformly at random, then we will distribute the first n balls to the first box
     * and the remaining n balls to the other box (Please read the explanation of the second example carefully).
     *
     * Please note that the two boxes are considered different. For example, if we have two balls of colors a and b,
     * and two boxes [] and (), then the distribution [a] (b) is considered different than the distribution [b] (a)
     * (Please read the explanation of the first example carefully).
     *
     * Return the probability that the two boxes have the same number of distinct balls. Answers within 10-5 of the
     * actual value will be accepted as correct.
     *
     * Input: balls = [1,1]
     * Output: 1.00000
     *
     * Input: balls = [2,1,1]
     * Output: 0.66667
     *
     * Input: balls = [1,2,1,2]
     * Output: 0.60000
     *
     * Constraints:
     *
     * 1 <= balls.length <= 8
     * 1 <= balls[i] <= 6
     * sum(balls) is even.
     * @param balls
     * @return
     */
    private final int N = 10;
    private int[] b, b1, b2;
    private long[][] C;
    private int n;
    private long totalValid, totalGood;
    public double getProbability(int[] balls) {
        b = balls;
        n = b.length;
        b1 = new int[n];
        b2 = new int[n];

        C = new long[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) C[i][j] = 1;
                else C[i][j] = C[i - 1][j] + C[i - 1][j - 1];
            }
        }

        dfs(0);
        return totalGood * 1.0 / totalValid;
    }

    private void dfs(int u) {
        if (u == n) {
            int tot1 = 0, tot2 = 0, cnt1 = 0, cnt2 = 0;
            for (int x : b1) {
                tot1 += x;
                if (x != 0) cnt1++;
            }
            for (int x : b2) {
                tot2 += x;
                if (x != 0) cnt2++;
            }

            if (tot1 != tot2) return;

            long valid = 1;
            for (int i = 0; i < n; i++) {
                valid *= C[b[i]][b1[i]];
            }

            totalValid += valid;
            if (cnt1 == cnt2) totalGood += valid;
            return;
        }

        for (int i = 0; i <= b[u]; i++) {
            b1[u] += i;
            b2[u] += b[u] - i;
            dfs(u + 1);
            b2[u] -= b[u] - i;
            b1[u] -= i;
        }
    }
}
/**
 * 判断放在第一个盒子里红球有多少个，第二个有多少个
 * 0 + 6, 1 + 5, 2 + 4, 3 + 3, 4 + 2, 5 + 1, 6 + 0 => 最多6个分支 => 6^8 = 10^6
 * 本质上就是dfs暴力枚举
 * 在所有合法事件个数
 * Good: the number of distributions s.t. # colors in box1 = # colors in box2
 * Valid: the number of distributions s.t (such that) # balls in box1 = # bolls in box2
 * 3 + 3 => C(6, 3) = 6 * 5 * 4 / 3! = 20
 */