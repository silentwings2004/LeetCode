package LC301_600;
import java.util.*;
public class LC526_BeautifulArrangement {
    /**
     * Suppose you have n integers from 1 to n. We define a beautiful arrangement as an array that is constructed by
     * these n numbers successfully if one of the following is true for the ith position (1 <= i <= n) in this array:
     *
     * The number at the ith position is divisible by i.
     * i is divisible by the number at the ith position.
     *
     * Given an integer n, return the number of the beautiful arrangements that you can construct.
     *
     * Input: n = 2
     * Output: 2
     * Explanation:
     * The first beautiful arrangement is [1, 2]:
     * Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
     * Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
     * The second beautiful arrangement is [2, 1]:
     * Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
     * Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
     *
     * Constraints:
     *
     * 1 <= n <= 15
     *
     *
     * @param n
     * @return
     */
    // S1: DFS
    // time = O(n * n!), space = O(n)
    int n, res;
    boolean[] st;
    public int countArrangement(int n) {
        this.n = n;
        res = 0;
        st = new boolean[n + 1];
        dfs(0);
        return res;
    }

    private void dfs(int u) {
        if (u == n) {
            res++;
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (st[i]) continue;
            if (i % (u + 1) == 0 || (u + 1) % i == 0) {
                st[i] = true;
                dfs(u + 1);
                st[i] = false;
            }
        }
    }

    // S2: 状压DP
    // time = O(n * n!), space = O(n)
    public int countArrangement2(int n) {
        int[] f = new int[1 << n];
        f[0] = 1;
        for (int i = 0; i < 1 << n; i++) {
            int k = 0;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) k++;
            }
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 0) {
                    if ((j + 1) % (k + 1) == 0 || (k + 1) % (j + 1) == 0) {
                        f[i | (1 << j)] += f[i];
                    }
                }
            }
        }
        return f[(1 << n) - 1];
    }
}

/**
 * f(i):表示前面有没有用过i这个数
 */
