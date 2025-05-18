package LC3301_3600;

public class LC3524_FindXValueofArrayI {
    /**
     * You are given an array of positive integers nums, and a positive integer k.
     *
     * You are allowed to perform an operation once on nums, where in each operation you can remove any non-overlapping
     * prefix and suffix from nums such that nums remains non-empty.
     *
     * You need to find the x-value of nums, which is the number of ways to perform this operation so that the product
     * of the remaining elements leaves a remainder of x when divided by k.
     *
     * Return an array result of size k where result[x] is the x-value of nums for 0 <= x <= k - 1.
     *
     * A prefix of an array is a subarray that starts from the beginning of the array and extends to any point within it.
     *
     * A suffix of an array is a subarray that starts at any point within the array and extends to the end of the array.
     *
     * A subarray is a contiguous sequence of elements within an array.
     *
     * Note that the prefix and suffix to be chosen for the operation can be empty.
     *
     * Input: nums = [1,2,3,4,5], k = 3
     * Output: [9,2,4]
     *
     * Input: nums = [1,2,4,8,16,32], k = 4
     * Output: [18,1,2,0]
     *
     * Input: nums = [1,1,2,1,1], k = 2
     * Output: [9,6]
     *
     * Constraints:
     *
     * 1 <= nums[i] <= 10^9
     * 1 <= nums.length <= 10^5
     * 1 <= k <= 5
     * @param nums
     * @param k
     * @return
     */
    // time = O(n * k), space = O(k)
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        long[] res = new long[k];
        long[] f = new long[k];
        for (int i = 0; i < n; i++) {
            int r = nums[i] % k;
            long[] g = new long[k];
            for (int j = 0; j < k; j++) {
                int x = r * j % k;
                g[x] += f[j];
            }
            g[r]++;
            for (int j = 0; j < k; j++) res[j] += g[j];
            f = g;
        }
        return res;
    }
}
/**
 * dfs(i) -> List[int]: 返回一个长为 k 的数组，表示以 i-1 为右端点的子数组，模 k 余 0,1,...k-1 的个数
 */