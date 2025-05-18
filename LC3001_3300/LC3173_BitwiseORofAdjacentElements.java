package LC3001_3300;

public class LC3173_BitwiseORofAdjacentElements {
    /**
     * Given an array nums of length n, return an array answer of length n - 1 such that answer[i] = nums[i] | nums[i + 1]
     * where | is the bitwise OR operation.
     *
     * Input: nums = [1,3,7,15]
     * Output: [3,7,15]
     *
     * Input: nums = [8,4,2]
     * Output: [12,6]
     *
     * Input: nums = [5,4,9,11]
     * Output: [5,13,11]
     *
     * Constraints:
     *
     * 2 <= nums.length <= 100
     * 0 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int[] orArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n - 1];
        for (int i = 0; i + 1 < n; i++) res[i] = nums[i] | nums[i + 1];
        return res;
    }
}