package LC3301_3600;
import java.util.*;
public class LC3336_FindtheNumberofSubsequencesWithEqualGCD {
    /**
     * You are given an integer array nums.
     *
     * Your task is to find the number of pairs of non-empty subsequences (seq1, seq2) of nums that satisfy the
     * following conditions:
     *
     * The subsequences seq1 and seq2 are disjoint, meaning no index of nums is common between them.
     * The GCD of the elements of seq1 is equal to the GCD of the elements of seq2.
     * Return the total number of such pairs.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * The term gcd(a, b) denotes the greatest common divisor of a and b.
     *
     * A subsequence is an array that can be derived from another array by deleting some or no elements without changing
     * the order of the remaining elements.
     *
     * Input: nums = [1,2,3,4]
     * Output: 10
     *
     * Input: nums = [10,20,30]
     * Output: 2
     *
     * Input: nums = [1,1,1,1]
     * Output: 50
     *
     * Constraints:
     *
     * 1 <= nums.length <= 200
     * 1 <= nums[i] <= 200
     * @param nums
     * @return
     */
    // time = O(nU^2*logU), space = O(nU^2), U = max(nums)
    final int N = 210;
    long mod = (long)(1e9 + 7);
    int[] nums;
    long[][][] f;
    int n;
    public int subsequencePairCount(int[] nums) {
        this.nums = nums;
        n = nums.length;
        f = new long[n][N][N];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(f[i][j], -1);
            }
        }
        return (int)dfs(0, 0, 0);
    }

    private long dfs(int u, int x, int y) {
        if (u == n) return x == 0 || y == 0 || x != y ? 0 : 1;
        if (f[u][x][y] != -1) return f[u][x][y];

        long res = dfs(u + 1, x, y);
        int g = x == 0 ? nums[u] : gcd(x, nums[u]);
        res = (res + dfs(u + 1, g, y)) % mod;
        g = y == 0 ? nums[u] : gcd(y, nums[u]);
        res = (res + dfs(u + 1, x, g)) % mod;
        f[u][x][y] = res;
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
/**
 * 200 => O(n^3)
 * 子序列求个数 => dp
 * 4 X => () ()
 * 4 选
 * [4] []
 * [] [4]
 */