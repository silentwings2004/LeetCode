package LC901_1200;

public class LC1128_NumberofEquivalentDominoPairs {
    /**
     * Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d] if and only if either
     * (a == c and b == d), or (a == d and b == c) - that is, one domino can be rotated to be equal to another domino.
     *
     * Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is equivalent to
     * dominoes[j].
     *
     * Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
     * Output: 1
     *
     * Input: dominoes = [[1,2],[1,2],[1,1],[1,2],[2,2]]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= dominoes.length <= 4 * 10^4
     * dominoes[i].length == 2
     * 1 <= dominoes[i][j] <= 9
     * @param dominoes
     * @return
     */
    // time = O(n), space = O(1)
    public int numEquivDominoPairs(int[][] dominoes) {
        int res = 0;
        int[][] cnt = new int[10][10];
        for (int[] x : dominoes) {
            int a = Math.min(x[0], x[1]);
            int b = Math.max(x[0], x[1]);
            res += cnt[a][b]++;
        }
        return res;
    }
}