package LC3001_3300;
import java.util.*;
public class LC3040_MaximumNumberofOperationsWiththeSameScoreII {
    /**
     * Given an array of integers called nums, you can perform any of the following operation while nums contains at
     * least 2 elements:
     *
     * Choose the first two elements of nums and delete them.
     * Choose the last two elements of nums and delete them.
     * Choose the first and the last elements of nums and delete them.
     * The score of the operation is the sum of the deleted elements.
     *
     * Your task is to find the maximum number of operations that can be performed, such that all operations have the
     * same score.
     *
     * Return the maximum number of operations possible that satisfy the condition mentioned above.
     *
     * Input: nums = [3,2,1,2,3,4]
     * Output: 3
     *
     * Input: nums = [3,2,6,1,4]
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= nums.length <= 2000
     * 1 <= nums[i] <= 1000
     * @param nums
     * @return
     */
    // time = O(n^2), space = O(n^2)
    int[][] f;
    public int maxOperations(int[] nums) {
        int n = nums.length;
        f = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], -1);
        return dfs(nums, 0, n - 1, -1);
    }

    private int dfs(int[] nums, int l, int r, int t) {
        if (l >= r) return 0;
        if (f[l][r] != -1) return f[l][r];

        int res = 0;
        if (t == -1 || nums[l] + nums[l + 1] == t) res = Math.max(res, 1 + dfs(nums, l + 2, r, nums[l] + nums[l + 1]));
        if (t == -1 || nums[r] + nums[r - 1] == t) res = Math.max(res, 1 + dfs(nums, l, r - 2, nums[r] + nums[r - 1]));
        if (t == -1 || nums[l] + nums[r] == t) res = Math.max(res, 1 + dfs(nums, l + 1, r - 1, nums[l] + nums[r]));
        f[l][r] = res;
        return res;
    }
}
/**
 * 区间dp:要解决的问题都是一段连续子数组的最多操作次数
 * 子问题是[从两侧向内缩小的] => 区间dp
 * dfs(i,j) = 操作 a[i] ... a[j] 这一段子数组(闭区间[i,j])的最多可以进行的操作次数
 */