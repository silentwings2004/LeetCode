package LC3001_3300;
import java.util.*;
public class LC3202_FindtheMaximumLengthofValidSubsequenceII {
    /**
     * You are given an integer array nums and a positive integer k.
     * A subsequence sub of nums with length x is called valid if it satisfies:
     *
     * (sub[0] + sub[1]) % k == (sub[1] + sub[2]) % k == ... == (sub[x - 2] + sub[x - 1]) % k.
     * Return the length of the longest valid subsequence of nums.
     *
     * Input: nums = [1,2,3,4,5], k = 2
     * Output: 5
     *
     * Input: nums = [1,4,2,3,1,4], k = 3
     * Output: 4
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^3
     * 1 <= nums[i] <= 10^7
     * 1 <= k <= 10^3
     * @param nums
     * @param k
     * @return
     */
    // time = O(k * n), space = O(n)
    int[] nums;
    int k, n;
    public int maximumLength(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
        n = nums.length;
        int[] f = new int[n];

        int res = 0;
        for (int i = 0; i < k; i++) {
            res = Math.max(res, helper(i));
        }
        return res;
    }

    private int helper(int val) {
        int[] pos = new int[k];
        Arrays.fill(pos, -1);
        int v = 0;
        int[] f = new int[n];
        for (int i = 0; i < n; i++) {
            int r = nums[i] % k;
            int d = (val - r + k) % k;
            f[i] = pos[d] == -1 ? 1 : f[pos[d]] + 1;
            pos[r] = i;
            v = Math.max(v, f[i]);
        }
        return v;
    }
}
/**
 * 子序列的奇数项模 k 是一样的
 * 子序列的偶数项模 k 是一样的
 *
 * 知道子序列的前2个数，就知道整个子序列了
 * 知道子序列的后2个数，就知道整个子序列了
 *
 * 我需要知道什么信息？
 * f[y][x] 表示子序列最后两个数模 k 为 y 和 x 的子序列的长度
 */