package LC901_1200;

public class LC1049_LastStoneWeightII {
    /**
     * You are given an array of integers stones where stones[i] is the weight of the ith stone.
     *
     * We are playing a game with the stones. On each turn, we choose any two stones and smash them together. Suppose
     * the stones have weights x and y with x <= y. The result of this smash is:
     *
     * If x == y, both stones are destroyed, and
     * If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
     * At the end of the game, there is at most one stone left.
     *
     * Return the smallest possible weight of the left stone. If there are no stones left, return 0.
     *
     * Input: stones = [2,7,4,1,8,1]
     * Output: 1
     *
     * Input: stones = [31,26,33,21,40]
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= stones.length <= 30
     * 1 <= stones[i] <= 100
     * @param stones
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 1510;
    public int lastStoneWeightII(int[] stones) {
        int[] f = new int[N];
        int n = stones.length, sum = 0;
        for (int x : stones) sum += x;
        int m = sum / 2;
        for (int i = 0; i < n; i++) {
            for (int j = m; j >= stones[i]; j--) {
                f[j] = Math.max(f[j], f[j - stones[i]] + stones[i]);
            }
        }
        return sum - f[m] * 2;
    }
}