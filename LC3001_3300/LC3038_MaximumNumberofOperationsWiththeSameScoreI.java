package LC3001_3300;

public class LC3038_MaximumNumberofOperationsWiththeSameScoreI {
    /**
     * Given an array of integers called nums, you can perform the following operation while nums contains at least 2
     * elements:
     *
     * Choose the first two elements of nums and delete them.
     * The score of the operation is the sum of the deleted elements.
     *
     * Your task is to find the maximum number of operations that can be performed, such that all operations have the
     * same score.
     *
     * Return the maximum number of operations possible that satisfy the condition mentioned above.
     *
     * Input: nums = [3,2,1,4,5]
     * Output: 2
     *
     * Input: nums = [3,2,6,1,4]
     * Output: 1
     *
     * Constraints:
     *
     * 2 <= nums.length <= 100
     * 1 <= nums[i] <= 1000
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int maxOperations(int[] nums) {
        int n = nums.length;
        int t = nums[0] + nums[1], res = 0;
        for (int i = 0; i + 1 < n; i += 2) {
            if (nums[i] + nums[i + 1] == t) res++;
            else break;
        }
        return res;
    }
}