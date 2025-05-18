package LC2700_3000;
import java.util.*;
public class LC2741_SpecialPermutations {
    /**
     * You are given a 0-indexed integer array nums containing n distinct positive integers. A permutation of nums is
     * called special if:
     *
     * For all indexes 0 <= i < n - 1, either nums[i] % nums[i+1] == 0 or nums[i+1] % nums[i] == 0.
     * Return the total number of special permutations. As the answer could be large, return it modulo 10^9 + 7.
     *
     * Input: nums = [2,3,6]
     * Output: 2
     *
     * Input: nums = [1,4,3]
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= nums.length <= 14
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // S1: dfs + memoization
    // time = O(n^2 * 2^n), space = O(n * 2^n)
    final int N = 15, M = N * N * 2, mod = (int)1e9 + 7;
    int[] w;
    int[] h, e, ne;
    int n, idx;
    long[][] f;
    public int specialPerm(int[] nums) {
        w = nums;
        n = w.length;
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        f = new long[n][1 << n];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], -1);
        Arrays.fill(h, -1);
        idx = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int a = nums[i], b = nums[j];
                if (a % b == 0 || b % a == 0) {
                    add(i, j);
                    add(j, i);
                }
            }
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            int state = 1 << i;
            res = (res + dfs(state, i)) % mod;
        }
        return (int)res;
    }

    private long dfs(int state, int u) {
        if (state == (1 << n) - 1) return 1;
        if (f[u][state] != -1) return f[u][state];

        long res = 0;
        for (int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i];
            if ((state >> j & 1) == 1) continue;
            int newState = state | (1 << j);
            res = (res + dfs(newState, j)) % mod;
        }
        f[u][state] = res;
        return res;
    }

    private void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    // S2: 状态压缩DP
    // time = O(n^2*2^n), space = O(n*2^n)
    public int specialPerm2(int[] nums) {
        int n = nums.length;
        long mod = (long)(1e9 + 7);
        long[][] f = new long[1 << n][n];
        for (int i = 0; i < n; i++) f[1 << i][i] = 1;
        for (int i = 0; i < 1 << n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    for (int k = 0; k < n; k++) {
                        if ((i >> k & 1) == 0) {
                            if (nums[j] % nums[k] == 0 || nums[k] % nums[j] == 0) {
                                f[i | (1 << k)][k] = (f[i | (1 << k)][k] + f[i][j]) % mod;
                            }
                        }
                    }
                }
            }
        }

        long res = 0;
        for (int i = 0; i < n; i++) res = (res + f[(1 << n) - 1][i]) % mod;
        return (int)res;
    }
}