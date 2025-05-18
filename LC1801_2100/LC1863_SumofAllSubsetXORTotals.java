package LC1801_2100;
import java.util.*;
public class LC1863_SumofAllSubsetXORTotals {
    /**
     * The XOR total of an array is defined as the bitwise XOR of all its elements, or 0 if the array is empty.
     *
     * For example, the XOR total of the array [2,5,6] is 2 XOR 5 XOR 6 = 1.
     * Given an array nums, return the sum of all XOR totals for every subset of nums.
     *
     * Note: Subsets with the same elements should be counted multiple times.
     *
     * An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b.
     *
     * Input: nums = [1,3]
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= nums.length <= 12
     * 1 <= nums[i] <= 20
     * @param nums
     * @return
     */
    // S1: dfs
    // time = O(n * 2^n), space = O(n);
    int res;
    public int subsetXORSum(int[] nums) {
        dfs(nums, 0, 0);
        return res;
    }

    private void dfs(int[] nums, int u, int t) {
        if (u == nums.length) return;
        for (int i = u; i < nums.length; i++) {
            res += t ^ nums[i];
            dfs(nums, i + 1, t ^ nums[i]);
        }
    }

    // S2
    // time = O(2 ^n * n), space = O(1)
    public int subsetXORSum2(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 1; i < 1 << n; i++) {
            int t = 0;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) t ^= nums[j];
            }
            res += t;
        }
        return res;
    }
}