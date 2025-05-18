package LC3001_3300;

public class LC3264_FinalArrayStateAfterKMultiplicationOperationsI {
    /**
     * You are given an integer array nums, an integer k, and an integer multiplier.
     *
     * You need to perform k operations on nums. In each operation:
     *
     * Find the minimum value x in nums. If there are multiple occurrences of the minimum value, select the one that
     * appears first.
     * Replace the selected minimum value x with x * multiplier.
     * Return an integer array denoting the final state of nums after performing all k operations.
     *
     * Input: nums = [2,1,3,5,6], k = 5, multiplier = 2
     * Output: [8,4,6,5,6]
     *
     * Input: nums = [1,2], k = 3, multiplier = 4
     * Output: [16,8]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * 1 <= k <= 10
     * 1 <= multiplier <= 5
     * @param nums
     * @param k
     * @param multiplier
     * @return
     */
    // time = O(n * k), space = O(1)
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        int n = nums.length;
        for (int i = 0; i < k; i++) {
            int idx = 0;
            for (int j = 0; j < n; j++) {
                if (nums[j] < nums[idx]) idx = j;
            }
            nums[idx] *= multiplier;
        }
        return nums;
    }
}