package LC301_600;
import java.util.*;
public class LC600_NonnegativeIntegerswithoutConsecutiveOnes {
    /**
     * Given a positive integer n, return the number of the integers in the range [0, n] whose binary representations
     * do not contain consecutive ones.
     *
     * Input: n = 5
     * Output: 5
     * Explanation:
     * Here are the non-negative integers <= 5 with their corresponding binary representations:
     * 0 : 0
     * 1 : 1
     * 2 : 10
     * 3 : 11
     * 4 : 100
     * 5 : 101
     * Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule.
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     * @param n
     * @return
     */
    // S1
    // time = O(logn), space = O(logn)
    public int findIntegers(int n) {
        int[] dp = new int[33];
        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i <= 32; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        int[] digits = new int[33];
        for (int i = 1; i <= 32; i++) {
            digits[i] = n % 2;
            n /= 2;
        }

        int i = 32, res = 0;
        while (i >= 1) {
            if (digits[i] == 0) i--;
            else {
                // plan A: we set digits[i] = 0
                res += dp[i - 1];

                // plan B: we set digits[i] = 1
                if (i >= 2 && digits[i - 1] == 1) { // digits[0]没有意义
                    res += dp[i - 2];
                    return res;
                }
                else i -= 2;
            }
        }
        res += 1; // i被递归到0，前面一路前缀打平相同，一直打到最后退出来了，所以最后一定要补上一个, 比如10000000 vs 00000000,也算1个
        return res;
    }

    // S2: 数位dp
    // time = O(logn), space = O(logn)
    public int findIntegers2(int n) {
        List<Integer> nums = new ArrayList<>();
        while (n > 0) {
            nums.add(n % 2);
            n >>= 1;
        }
        int m = nums.size();
        int[][] f = new int[m + 1][2];
        f[1][0] = f[1][1] = 1;
        for (int i = 2; i <= m; i++) {
            f[i][0] = f[i - 1][0] + f[i - 1][1];
            f[i][1] = f[i - 1][0];
        }

        int res = 0;
        for (int i = m, last = 0; i > 0; i--) {
            int x = nums.get(i - 1);
            if (x == 1) {
                res += f[i][0];
                if (last == 1) return res;
            }
            last = x;
        }
        return res + 1;
    }

    // S3
    // time = O(logn), space = O(logn)
    char[] s;
    int[][] f;
    public int findIntegers3(int n) {
        s = Integer.toBinaryString(n).toCharArray();
        int m = s.length;
        f = new int[m][2];
        for (int i = 0; i < m; i++) Arrays.fill(f[i], -1);
        return dfs(0, 0, true);
    }

    private int dfs(int u, int pre, boolean isLimit) {
        if (u == s.length) return 1;
        if (!isLimit && f[u][pre] != -1) return f[u][pre];

        int res = 0;
        int up = isLimit ? s[u] - '0' : 1;
        for (int i = 0; i <= up; i++) {
            if (i + pre <= 1) res += dfs(u + 1, i, isLimit && i == up);
        }
        if (!isLimit) f[u][pre] = res;
        return res;
    }
}
/**
 * dp[m]: 有多少个m位的01序列，里面不包含相连的1
 * ref: house robber
 * xxx x   xxxxxxx
 * dp[i] = dp[i - 2] + dp[i - 1]
 * dp[2] = dp[1] + dp[2] = 3
 * 00
 * 01
 * 10
 * 11
 * 突破口：不是0就是1，最多32位
 * (A)
 * 1xxxxxxxxx0
 * 0?????????0   result += dp[31]
 *
 * (B)
 * 1xxxxxxxxxx
 * 1??????????
 *
 *      (B1)
 *      11xxxxxxxxx
 *      10?????????   result += dp[30]
 *
 *      11xxxxxxxxx
 *      11?????????  X => 不可能出现连续的2个1
 *
 *      (B2)
 *      10xxxxxxxxx
 *      10?????????   result += dfs(30)
 *
 * (C)
 * 0xxxxxxxxxx
 * 0??????????    result -> dfs(31)
 *
 * 数位dp
 * 1. 预处理
 * f(i,0): 最高位为0，一共i位 => f(i,0) = f(i-1,0) + f(i-1,1)
 * f(i,1): 最高位为1，一共i位 => f(i,1) = f(i-1,0)
 * 2. 按位做：
 */
