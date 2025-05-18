package LC3301_3600;

public class LC3379_TransformedArray {
    /**
     * You are given an integer array nums that represents a circular array. Your task is to create a new array result
     * of the same size, following these rules:
     *
     * For each index i (where 0 <= i < nums.length), perform the following independent actions:
     * If nums[i] > 0: Start at index i and move nums[i] steps to the right in the circular array. Set result[i] to the
     * value of the index where you land.
     * If nums[i] < 0: Start at index i and move abs(nums[i]) steps to the left in the circular array. Set result[i] to
     * the value of the index where you land.
     * If nums[i] == 0: Set result[i] to nums[i].
     * Return the new array result.
     *
     * Note: Since nums is circular, moving past the last element wraps around to the beginning, and moving before the
     * first element wraps back to the end.
     *
     * Input: nums = [3,-2,1,1]
     * Output: [1,1,1,3]
     *
     * Input: nums = [-1,4,-1]
     * Output: [-1,-1,4]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * -100 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) res[i] = nums[(i + nums[i]) % n];
            else if (nums[i] < 0) res[i] = nums[((i - Math.abs(nums[i])) % n + n) % n];
            else res[i] = nums[i];
        }
        return res;
    }
}