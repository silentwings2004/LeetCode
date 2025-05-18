package LC2700_3000;

public class LC2903_FindIndicesWithIndexandValueDifferenceI {
    /**
     * You are given a 0-indexed integer array nums having length n, an integer indexDifference, and an integer
     * valueDifference.
     *
     * Your task is to find two indices i and j, both in the range [0, n - 1], that satisfy the following conditions:
     *
     * abs(i - j) >= indexDifference, and
     * abs(nums[i] - nums[j]) >= valueDifference
     * Return an integer array answer, where answer = [i, j] if there are two such indices, and answer = [-1, -1]
     * otherwise. If there are multiple choices for the two indices, return any of them.
     *
     * Note: i and j may be equal.
     *
     * Input: nums = [5,1,4,1], indexDifference = 2, valueDifference = 4
     * Output: [0,3]
     *
     * Input: nums = [2,1], indexDifference = 0, valueDifference = 0
     * Output: [0,0]
     *
     * Input: nums = [1,2,3], indexDifference = 2, valueDifference = 4
     * Output: [-1,-1]
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 100
     * 0 <= nums[i] <= 50
     * 0 <= indexDifference <= 100
     * 0 <= valueDifference <= 50
     * @param nums
     * @param indexDifference
     * @param valueDifference
     * @return
     */
    // time = O(n^2), space = O(1)
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (Math.abs(i - j) >= indexDifference && Math.abs(nums[i] - nums[j]) >= valueDifference) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }
}