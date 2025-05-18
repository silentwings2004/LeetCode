package LC3001_3300;

public class LC3287_FindtheMaximumSequenceValueofArray {
    /**
     * You are given an integer array nums and a positive integer k.
     *
     * The value of a sequence seq of size 2 * x is defined as:
     *
     * (seq[0] OR seq[1] OR ... OR seq[x - 1]) XOR (seq[x] OR seq[x + 1] OR ... OR seq[2 * x - 1]).
     *
     * Return the maximum value of any subsequence of nums having size 2 * k.
     *
     * Input: nums = [2,6,7], k = 1
     * Output: 5
     *
     * Input: nums = [4,2,5,6,7], k = 2
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= nums.length <= 400
     * 1 <= nums[i] < 27
     * 1 <= k <= nums.length / 2
     * @param nums
     * @param k
     * @return
     */
    // time = O(n * max(k, 2^7) * 2^7), space = O(n * k * 2^7)
    public int maxValue(int[] nums, int k) {
        int n = nums.length;
        boolean[][][] f = new boolean[n][k + 1][1 << 7];
        boolean[][][] g = new boolean[n][k + 1][1 << 7];

        f[0][0][0] = true;
        f[0][1][nums[0]] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                for (int t = 0; t < 1 << 7; t++) {
                    f[i][j][t] |= f[i - 1][j][t];
                    if (j < k) f[i][j + 1][t | nums[i]] |= f[i - 1][j][t];
                }
            }
        }

        g[n - 1][0][0] = true;
        g[n - 1][1][nums[n - 1]] = true;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= k; j++) {
                for (int t = 0; t < 1 << 7; t++) {
                    g[i][j][t] |= g[i + 1][j][t];
                    if (j < k) g[i][j + 1][t | nums[i]] |= g[i + 1][j][t];
                }
            }
        }

        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int a = 0; a < 1 << 7; a++) {
                for (int b = 0; b < 1 << 7; b++) {
                    if (f[i][k][a] && g[i + 1][k][b]) {
                        res = Math.max(res, a ^ b);
                    }
                }
            }
        }
        return res;
    }
}
/**
 * 查表法
 * f[i][j] = f[i-1][j] or f[i-1][j-v]
 *
 * 刷表法：已知当前状态，更新能够被我转移到的状态(一对多)
 * f[i][j][x] 表示能否从后 i 个数，且 OR 恰好等于 x
 * nums[i] 选或不选
 * 不选 suf[i][j][x] = suf[i+1][j][x]
 * 选   suf[i][j][x|v] <- suf[i+1][j-1][x]
 *
 * => suf[i][j][x|v] = suf[i+1][j][x|v] or suf[i+1][j-1][x]
 */