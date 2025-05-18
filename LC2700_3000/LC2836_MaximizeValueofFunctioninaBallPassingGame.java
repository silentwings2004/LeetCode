package LC2700_3000;
import java.util.*;
public class LC2836_MaximizeValueofFunctioninaBallPassingGame {
    /**
     * You are given a 0-indexed integer array receiver of length n and an integer k.
     *
     * There are n players having a unique id in the range [0, n - 1] who will play a ball passing game, and receiver[i]
     * is the id of the player who receives passes from the player with id i. Players can pass to themselves, i.e.
     * receiver[i] may be equal to i.
     *
     * You must choose one of the n players as the starting player for the game, and the ball will be passed exactly k
     * times starting from the chosen player.
     *
     * For a chosen starting player having id x, we define a function f(x) that denotes the sum of x and the ids of all
     * players who receive the ball during the k passes, including repetitions. In other words, f(x) = x + receiver[x]
     * + receiver[receiver[x]] + ... + receiver(k)[x].
     *
     * Your task is to choose a starting player having id x that maximizes the value of f(x).
     *
     * Return an integer denoting the maximum value of the function.
     *
     * Note: receiver may contain duplicates.
     *
     * Input: receiver = [2,0,1], k = 4
     * Output: 6
     *
     * Input: receiver = [1,1,1,2,3], k = 3
     * Output: 10
     *
     * Constraints:
     *
     * 1 <= receiver.length == n <= 10^5
     * 0 <= receiver[i] <= n - 1
     * 1 <= k <= 10^10
     * @param receiver
     * @param k
     * @return
     */
    // time = O(nlogk), space = O(nlogk)
    public long getMaxFunctionValue(List<Integer> receiver, long k) {
        int n = receiver.size();
        int[][] fa = new int[n][34];
        long[][] s = new long[n][34];
        for (int i = 0; i < n; i++) {
            fa[i][0] = receiver.get(i);
            s[i][0] = i;
        }

        for (int j = 1; j < 34; j++) { // 必须先循环j,因为要先算出j - 1才能算出j
            for (int i = 0; i < n; i++) {
                fa[i][j] = fa[fa[i][j - 1]][j - 1];
                s[i][j] = s[i][j - 1] + s[fa[i][j - 1]][j - 1];
            }
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            long t = 0;
            int pos = i;
            for (int j = 0; j < 34; j++) {
                if ((k >> j & 1) == 1) {
                    t += s[pos][j];
                    pos = fa[pos][j];
                }
            }
            res = Math.max(res, t + pos);
        }
        return res;
    }
}
/**
 * 如果题目给的是一棵树，要如何解决 => 树上倍增 LC1483
 * 只要保证每个点只有一条边连向另一个点，就可以用树上倍增来做
 * 1. 每个点，顺着receiver 走 2^i步之后的节点
 * 2. 从 x 的父节点，到走 2^i后的节点，这条路径上的节点编号之和
 */