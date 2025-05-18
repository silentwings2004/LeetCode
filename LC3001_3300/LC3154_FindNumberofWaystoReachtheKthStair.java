package LC3001_3300;
import java.util.*;
public class LC3154_FindNumberofWaystoReachtheKthStair {
    /**
     * You are given a non-negative integer k. There exists a staircase with an infinite number of stairs, with the
     * lowest stair numbered 0.
     *
     * Alice has an integer jump, with an initial value of 0. She starts on stair 1 and wants to reach stair k using
     * any number of operations. If she is on stair i, in one operation she can:
     *
     * Go down to stair i - 1. This operation cannot be used consecutively or on stair 0.
     * Go up to stair i + 2jump. And then, jump becomes jump + 1.
     * Return the total number of ways Alice can reach stair k.
     *
     * Note that it is possible that Alice reaches the stair k, and performs some operations to reach the stair k again.
     *
     * Input: k = 0
     *
     * Output: 2
     *
     * Input: k = 1
     *
     * Output: 4
     *
     * Constraints:
     *
     * 0 <= k <= 10^9
     * @param k
     * @return
     */
    // S1: 记忆化搜索
    // time = O((logk)^2), space = O((logk)^2)
    HashMap<Long, Integer> map;
    int k;
    public int waysToReachStair(int k) {
        this.k = k;
        map = new HashMap<>();
        return dfs(1L, 0, 0);
    }

    private int dfs(long u, int jump, int last) {
        if (u > k + 1) return 0;
        long key = (u << 32) + (jump << 4) + (last << 2);
        if (map.containsKey(key)) return map.get(key);
        int t = 0;
        if (u == k) t++;

        // op1
        if (u > 0 && last == 0) t += dfs(u - 1, jump, 1);

        // op2
        t += dfs(u + (1L << jump), jump + 1, 0);
        map.put(key, t);
        return t;
    }

    // S2: 组合数学
    // time = O(1), space = O(1)
    public int waysToReachStair2(int k) {
        int[][] C = new int[31][31];
        for (int i = 0; i <= 30; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) C[i][j] = 1;
                else C[i][j] = C[i - 1][j] + C[i - 1][j - 1];
            }
        }

        int res = 0;
        for (int i = 0; i < 30; i++) {
            int m = (1 << i) - k;
            if (m >= 0 && m <= i + 1) {
                res += C[i + 1][m];
            }
        }
        return res;
    }

    // S2.2: 组合数学
    // time = O(1), space = O(1)
    public int waysToReachStair3(int k) {
        int[][] C = new int[31][31];
        for (int i = 0; i <= 30; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) C[i][j] = 1;
                else C[i][j] = C[i - 1][j] + C[i - 1][j - 1];
            }
        }

        int res = 0;
        for (int i = 32 - Integer.numberOfLeadingZeros(Math.max(k - 1, 0)); (1 << i) - k <= i + 1; i++) {
            res += C[i + 1][(1 << i) - k];
        }
        return res;
    }
}
/**
 * i = 2^j - m  其中 m 是操作一的执行次数  => m = 2^j - k 最终 i = k
 * 0 <= m <= j + 1
 * i <= k + 1
 * 2^j - m <= k + 1
 * 2^j ~ k
 * j ~ logk
 * => time = O((logk)^2)
 * 2^j >= k
 * j >= len2(k - 1)
 */