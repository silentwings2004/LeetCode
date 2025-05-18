package LC1201_1500;
import java.util.*;
public class LC1402_ReducingDishes {
    /**
     * A chef has collected data on the satisfaction level of his n dishes. Chef can cook any dish in 1 unit of time.
     *
     * Like-time coefficient of a dish is defined as the time taken to cook that dish including previous dishes
     * multiplied by its satisfaction level i.e. time[i] * satisfaction[i].
     *
     * Return the maximum sum of like-time coefficient that the chef can obtain after dishes preparation.
     *
     * Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.
     *
     * Input: satisfaction = [-1,-8,0,5,-9]
     * Output: 14
     *
     * Input: satisfaction = [4,3,2]
     * Output: 20
     *
     * Input: satisfaction = [-1,-4,-5]
     * Output: 0
     *
     * Constraints:
     *
     * n == satisfaction.length
     * 1 <= n <= 500
     * -1000 <= satisfaction[i] <= 1000
     * @param satisfaction
     * @return
     */
    // S1: DP
    // time = O(n^2), space = O(n^2)
    final int N = 510, INF = (int) 1e9;
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int n = satisfaction.length;
        int[][] f = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(f[i], -INF);
        for (int i = 0; i < N; i++) f[i][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                f[i][j] = f[i - 1][j];
                f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + satisfaction[i - 1] * j);
            }
        }
        int res = 0;
        for (int i = 0; i <= n; i++) res = Math.max(res, f[n][i]);
        return res;
    }

    // S2: Greedy (optimal solution!)
    // time = O(nlogn), space = O(logn)
    public int maxSatisfaction2(int[] satisfaction) {
        int n = satisfaction.length;
        Arrays.sort(satisfaction);
        int tot = 0, sum = 0, res = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            sum += satisfaction[i];
            tot += sum;
            res = Math.max(res, tot);
        }
        return Math.max(res, 0);
    }
}