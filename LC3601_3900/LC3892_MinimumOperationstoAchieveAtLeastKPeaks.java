package LC3601_3900;
import java.util.*;
public class LC3892_MinimumOperationstoAchieveAtLeastKPeaks {
    /**
     * You are given a circular integer array nums of length n.
     *
     * An index i is a peak if its value is strictly greater than its neighbors:
     *
     * The previous neighbor of i is nums[i - 1] if i > 0, otherwise nums[n - 1].
     * The next neighbor of i is nums[i + 1] if i < n - 1, otherwise nums[0].
     * You are allowed to perform the following operation any number of times:
     *
     * Choose any index i and increase nums[i] by 1.
     * Return an integer denoting the minimum number of operations required to make the array contain at least k peaks.
     * If it is impossible, return -1.
     *
     * Input: nums = [2,1,2], k = 1
     * Output: 1
     *
     * Input: nums = [3,7,3], k = 2
     * Output: -1
     *
     * Constraints:
     *
     * 2 <= n == nums.length <= 5000
     * -10^5 <= nums[i] <= 10^5
     * 0 <= k <= n
     * @param nums
     * @param k
     * @return
     */
    // time = O(n^2), space = O(n)
    final int inf = 0x3f3f3f3f;
    int[] w;
    public int minOperations(int[] nums, int k) {
        int n = nums.length;
        if (k == 0) return 0;
        if (k > n / 2) return -1; // 环上最多只能选 floor(n / 2) 个互不相邻的位置

        w = new int[n];
        for (int i = 0; i < n; i++) {
            int l = nums[(i - 1 + n) % n];
            int r = nums[(i + 1) % n];
            int v = Math.max(l, r) + 1 - nums[i];
            w[i] = Math.max(0, v);
        }

        int res = inf;
        int v1 = helper(1, n - 1, k); // not choose 0
        int v2 = helper(2, n - 2, k - 1) + w[0]; // choose 0
        res = Math.min(res, Math.min(v1, v2));
        return res == inf ? -1 : res;
    }

    private int helper(int l, int r, int k) {
        if (k == 0) return 0;
        if (l > r) return inf;

        int m = r - l + 1;
        if (k > (m + 1) / 2) return -1; // 在线性区间 [l..r] 中，最多只能选 (m + 1) / 2 个

        int[] f = new int[k + 1];
        int[] g = new int[k + 1];
        Arrays.fill(f, inf); // not choose
        Arrays.fill(g, inf); // choose
        f[0] = 0;

        for (int i = l; i <= r; i++) {
            int[] nf = new int[k + 1];
            int[] ng = new int[k + 1];
            Arrays.fill(nf, inf);
            Arrays.fill(ng, inf);
            for (int j = 0; j <= k; j++) {
                nf[j] = Math.min(f[j], g[j]);
                if (j > 0 && f[j - 1] != inf) {
                    ng[j] = f[j - 1] + w[i];
                }
            }
            f = nf;
            g = ng;
        }
        return Math.min(f[k], g[k]);
    }
}