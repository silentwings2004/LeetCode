package LC3301_3600;
import java.util.*;
public class LC3469_FindMinimumCosttoRemoveArrayElements {
    /**
     * You are given an integer array nums. Your task is to remove all elements from the array by performing one of the
     * following operations at each step until nums is empty:
     *
     * Choose any two elements from the first three elements of nums and remove them. The cost of this operation is the
     * maximum of the two elements removed.
     * If fewer than three elements remain in nums, remove all the remaining elements in a single operation. The cost of
     * this operation is the maximum of the remaining elements.
     * Return the minimum cost required to remove all the elements.
     *
     * Input: nums = [6,2,8,4]
     * Output: 12
     *
     * Input: nums = [2,1,3,3]
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // time = O(n^2), space = O(n^2)
    int[] nums;
    HashMap<Integer, Integer> f;
    int n;
    public int minCost(int[] nums) {
        this.nums = nums;
        n = nums.length;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        f = new HashMap<>();
        return dfs(0, -1);
    }

    private int dfs(int u, int last) {
        if (u >= n) return last;
        if (u == n - 1) return Math.max(nums[u], last);
        int h = u << 20 | last;
        if (f.containsKey(h)) return f.get(h);

        int res = 0;
        if (last == -1) {
            int v1 = Math.max(nums[u], nums[u + 1]) + dfs(u + 3, nums[u + 2]);
            int v2 = Math.max(nums[u], nums[u + 2]) + dfs(u + 3, nums[u + 1]);
            int v3 = Math.max(nums[u + 1], nums[u + 2]) + dfs(u + 3, nums[u]);
            res = Math.min(v1, Math.min(v2, v3));
        } else {
            int v1 = Math.max(last, nums[u]) + dfs(u + 2, nums[u + 1]);
            int v2 = Math.max(last, nums[u + 1]) + dfs(u + 2, nums[u]);
            int v3 = Math.max(nums[u], nums[u + 1]) + dfs(u + 2, last);
            res = Math.min(v1, Math.min(v2, v3));
        }
        f.put(h, res);
        return res;
    }
}