package LC3601_3900;

public class LC3637_TrionicArrayI {
    /**
     * You are given an integer array nums of length n.
     *
     * An array is trionic if there exist indices 0 < p < q < n − 1 such that:
     *
     * nums[0...p] is strictly increasing,
     * nums[p...q] is strictly decreasing,
     * nums[q...n − 1] is strictly increasing.
     * Return true if nums is trionic, otherwise return false.
     *
     * Input: nums = [1,3,5,4,2,6]
     * Output: true
     *
     * Input: nums = [2,1,3]
     * Output: false
     *
     * Constraints:
     *
     * 3 <= n <= 100
     * -1000 <= nums[i] <= 1000
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public boolean isTrionic(int[] nums) {
        int n = nums.length, i = 0, j = i + 1;
        while (j < n && nums[j] > nums[j - 1]) j++;
        if (j - i == 1) return false;
        i = j - 1;
        while (j < n && nums[j] < nums[j - 1]) j++;
        if (j - i == 1) return false;
        i = j - 1;
        while (j < n && nums[j] > nums[j - 1]) j++;
        if (j - i == 1) return false;
        return j == n;
    }
}