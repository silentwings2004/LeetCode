package LC1501_1800;
import java.util.*;
public class LC1764_FormArraybyConcatenatingSubarraysofAnotherArray {
    /**
     * You are given a 2D integer array groups of length n. You are also given an integer array nums.
     *
     * You are asked if you can choose n disjoint subarrays from the array nums such that the ith subarray is equal
     * to groups[i] (0-indexed), and if i > 0, the (i-1)th subarray appears before the ith subarray in nums (i.e. the
     * ubarrays must be in the same order as groups).
     *
     * Return true if you can do this task, and false otherwise.
     *
     * Note that the subarrays are disjoint if and only if there is no index k such that nums[k] belongs to more than
     * one subarray. A subarray is a contiguous sequence of elements within an array.
     *
     * Input: groups = [[1,-1,-1],[3,-2,0]], nums = [1,-1,0,1,-1,-1,3,-2,0]
     * Output: true
     *
     * Constraints:
     *
     * groups.length == n
     * 1 <= n <= 10^3
     * 1 <= groups[i].length, sum(groups[i].length) <= 10^3
     * 1 <= nums.length <= 10^3
     * -10^7 <= groups[i][j], nums[k] <= 10^7
     *
     * @param groups
     * @param nums
     * @return
     */
    // time = O(m * n), space = O(1)
    public boolean canChoose(int[][] groups, int[] nums) {
        int m = groups.length, n = nums.length;
        for (int i = 0, k = 0; i < n; i++) {
            if (check(groups[k], nums, i)) {
                i += groups[k].length - 1;
                k++;
                if (k == m) return true;
            }
        }
        return false;
    }

    private boolean check(int[] g, int [] nums, int i) {
        int m = g.length, n = nums.length, k = 0;
        for (int j = i; k < m && j < n; j++, k++) {
            if (g[k] != nums[j]) return false;
        }
        return k == m;
    }
}
