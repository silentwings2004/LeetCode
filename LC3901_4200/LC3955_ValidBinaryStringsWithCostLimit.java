package LC3901_4200;
import java.util.*;
public class LC3955_ValidBinaryStringsWithCostLimit {
    /**
     * You are given two integers n and k.
     *
     * The cost of a binary string s is defined as the sum of all indices i (0 - based) such that s[i] == '1'.
     *
     * A binary string is considered valid if:
     *
     * It does not contain two consecutive '1' characters.
     * Its cost is less than or equal to k.
     * Return a list of all valid binary strings of length n in any order.
     *
     * Input: n = 3, k = 1
     * Output: ["000","010","100"]
     *
     * Input: n = 1, k = 0
     * Output: ["0","1"]
     *
     * Constraints:
     *
     * 1 <= n <= 12
     * 0 <= k <= n * (n - 1) / 2
     * @param n
     * @param k
     * @return
     */
    // time = O(2^n), space = O(n)
    List<String> res;
    int n, k;
    char[] path;
    public List<String> generateValidStrings(int n, int k) {
        this.n = n;
        this.k = k;
        path = new char[n];
        res = new ArrayList<>();
        dfs(0, 0, false);
        return res;
    }

    private void dfs(int u, int cost, boolean f) {
        if (cost > k) return;
        if (u == n) {
            res.add(String.valueOf(path));
            return;
        }

        path[u] = '0';
        dfs(u + 1, cost, false);

        if (!f) {
            path[u] = '1';
            dfs(u + 1, cost + u, true);
        }
    }

    // S2
    // time = O(n * 2^n), space = O(n)
    int[] cost = new int[1 << 12];
    boolean initialized = false;
    public LC3955_ValidBinaryStringsWithCostLimit() {
        if (initialized) return;
        initialized = true;
        for (int i = 1; i < cost.length; i++) {
            if ((i & (i >> 1)) > 0) cost[i] = Integer.MAX_VALUE; // 有两个连续的 1
            // 去掉 x 中的一个比特位（最低位还是最高位都可以），计算 DP
            else cost[i] = cost[i & (i - 1)] + Integer.numberOfTrailingZeros(i);
        }
    }
    public List<String> generateValidStrings2(int n, int k) {
        List<String> res = new ArrayList<>();
        char[] s = new char[n];
        for (int i = 0; i < 1 << n; i++) {
            if (cost[i] > k) continue;
            int y = i;
            for (int j = 0; j < n; j++) { // 注意左边是低位，右边是高位
                s[j] = (char)('0' + (y & 1));
                y >>= 1;
            }
            res.add(String.valueOf(s));
        }
        return res;
    }
}
/**
 * 对于 x 和 x >> 1 这两个二进制数，同一个比特位上的数字，对应着 x 中一对相邻比特位上的数字。
 * 所以计算 x & (x >> 1) 等价于计算 x 的所有相邻比特位的 &。所有相邻的 11 变成 1，其余变成 0。
 */