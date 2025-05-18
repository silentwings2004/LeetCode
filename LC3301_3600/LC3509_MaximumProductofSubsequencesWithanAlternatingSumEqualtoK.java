package LC3301_3600;
import java.util.*;
public class LC3509_MaximumProductofSubsequencesWithanAlternatingSumEqualtoK {
    /**
     * You are given an integer array nums and two integers, k and limit. Your task is to find a non-empty subsequence
     * of nums that:
     *
     * Has an alternating sum equal to k.
     * Maximizes the product of all its numbers without the product exceeding limit.
     * Return the product of the numbers in such a subsequence. If no subsequence satisfies the requirements, return -1.
     *
     * The alternating sum of a 0-indexed array is defined as the sum of the elements at even indices minus the sum of
     * the elements at odd indices.
     *
     * Input: nums = [1,2,3], k = 2, limit = 10
     * Output: 6
     *
     * Input: nums = [0,2,3], k = -5, limit = 12
     * Output: -1
     *
     * Input: nums = [2,2,3,3], k = 0, limit = 9
     * Output: 9
     *
     * Constraints:
     *
     * 1 <= nums.length <= 150
     * 0 <= nums[i] <= 12
     * -10^5 <= k <= 10^5
     * 1 <= limit <= 5000
     * @param nums
     * @param k
     * @param limit
     * @return
     */
    // time = O(n*(n * U + M * log(limit))), space = O(n*(n * U + M * log(limit)))
    int[] nums;
    int k, limit, n, res;
    HashSet<String> set;
    public int maxProduct(int[] nums, int k, int limit) {
        this.nums = nums;
        this.k = k;
        this.limit = limit;
        set = new HashSet<>();
        int s = 0;
        for (int x : nums) s += x;
        if (s < Math.abs(k)) return -1;
        n = nums.length;
        res = -1;
        dfs(0, 0, 1, 1, 1);
        return res;
    }

    private void dfs(int u, int sum, int prod, int odd, int empty) {
        String h = u + "#" + sum + "#" + prod + "#" + odd + "#" + empty;
        if (!set.add(h) || res == limit) return;
        if (u == n) {
            if (empty == 0 && sum == k && prod <= limit) {
                res = Math.max(res, prod);
            }
            return;
        }
        // not pick
        dfs(u + 1, sum, prod, odd, empty);
        // pick
        dfs(u + 1, sum + nums[u] * odd, Math.min(prod * nums[u], limit + 1), odd * -1, 0);
    }
}