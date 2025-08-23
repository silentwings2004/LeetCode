package LC3601_3900;

public class LC3647_MaximumWeightinTwoBags {
    /**
     * You are given an integer array weights and two integers w1 and w2 representing the maximum capacities of two bags.
     *
     * Each item may be placed in at most one bag such that:
     *
     * Bag 1 holds at most w1 total weight.
     * Bag 2 holds at most w2 total weight.
     * Return the maximum total weight that can be packed into the two bags.
     *
     * Input: weights = [1,4,3,2], w1 = 5, w2 = 4
     * Output: 9
     *
     * Input: weights = [3,6,4,8], w1 = 9, w2 = 7
     * Output: 15
     *
     * Input: weights = [5,7], w1 = 2, w2 = 3
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= weights.length <= 100
     * 1 <= weights[i] <= 100
     * 1 <= w1, w2 <= 300
     * @param weights
     * @param w1
     * @param w2
     * @return
     */
    public int maxWeight(int[] weights, int w1, int w2) {
        int n = weights.length;
        boolean[][] f = new boolean[w1 + 1][w2 + 1];
        boolean[][] g = new boolean[w1 + 1][w2 + 1];
        f[0][0] = true;

        int res = 0;
        for (int x : weights) {
            for (int i = 0; i <= w1; i++) g[i] = f[i].clone();
            for (int i = x; i <= w1; i++) {
                for (int j = 0; j <= w2; j++) {
                    if (g[i - x][j]) {
                        res = Math.max(res, i + j);
                        f[i][j] = true;
                    }
                }
            }
            for (int j = x; j <= w2; j++) {
                for (int i = 0; i <= w1; i++) {
                    if (g[i][j - x]) {
                        res = Math.max(res, i + j);
                        f[i][j] = true;
                    }
                }
            }
        }
        return res;
    }
}