package LC3001_3300;
import java.util.*;
public class LC3117_MinimumSumofValuesbyDividingArray {
    /**
     * You are given two arrays nums and andValues of length n and m respectively.
     *
     * The value of an array is equal to the last element of that array.
     *
     * You have to divide nums into m disjoint contiguous
     * subarrays
     *  such that for the ith subarray [li, ri], the bitwise AND of the subarray elements is equal to andValues[i], in
     *  other words, nums[li] & nums[li + 1] & ... & nums[ri] == andValues[i] for all 1 <= i <= m, where & represents
     *  the bitwise AND operator.
     *
     * Return the minimum possible sum of the values of the m subarrays nums is divided into. If it is not possible to
     * divide nums into m subarrays satisfying these conditions, return -1.
     *
     * Input: nums = [1,4,3,3,2], andValues = [0,3,3,2]
     * Output: 12
     *
     * Input: nums = [2,3,5,7,7,7,5], andValues = [0,7,5]
     * Output: 17
     *
     * Input: nums = [1,2,3,4], andValues = [2]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^4
     * 1 <= m == andValues.length <= min(n, 10)
     * 1 <= nums[i] < 10^5
     * 0 <= andValues[j] < 10^5
     * @param nums
     * @param andValues
     * @return
     */
    // time = O(nlogU), space = O(m * n * logU)
    final int inf = 0x3f3f3f3f;
    HashMap<Long, Integer> map;
    public int minimumValueSum(int[] nums, int[] andValues) {
        map = new HashMap<>();
        int res = dfs(nums, andValues, 0, 0, -1);
        return res == inf ? -1 : res;
    }

    private int dfs(int[] a, int[] b, int i, int j, int t) {
        if (i == a.length) return j == b.length ? 0 : inf;
        if (j == b.length) return inf;
        t &= a[i];
        if (t < b[j]) return inf;
        long k = t + j * (1L << 20) + i * (1L << 25);
        if (map.containsKey(k)) return map.get(k);

        int res = dfs(a, b, i + 1, j, t);
        if (t == b[j]) res = Math.min(res, dfs(a, b, i + 1, j + 1, -1) + a[i]);
        map.put(k, res);
        return res;
    }
}
/**
 * 定理：所有子数组 O(n^2)
 * 只有 O(nlogU) U = max(nums) 个不同的 AND 结果
 * 1 4 3 3 2
 * 约束划分个数 dp
 * dfs(i,j)
 * i 表示从 nums[i] 开始往后的后缀
 * j 表示已经划分的子数组个数
 * and 表示当前这一段的已经遍历过的元素的 AND
 * dfs(i,j,and)
 *
 * 不划分：dfs(i+1,j,and)
 * 划分：dfs(i+1,j+1,-1) + nums[i]
 * 划分的前提：and == andValues[j]
 * 两种情况取 min, 即为 dfs(i,j,and)
 */