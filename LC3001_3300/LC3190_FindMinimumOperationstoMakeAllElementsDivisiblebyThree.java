package LC3001_3300;

public class LC3190_FindMinimumOperationstoMakeAllElementsDivisiblebyThree {
    /**
     * You are given an integer array nums. In one operation, you can add or subtract 1 from any element of nums.
     *
     * Return the minimum number of operations to make all elements of nums divisible by 3.
     *
     * Input: nums = [1,2,3,4]
     * Output: 3
     *
     * Input: nums = [3,6,9]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 50
     * 1 <= nums[i] <= 50
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int minimumOperations(int[] nums) {
        int res = 0;
        for (int x : nums) {
            if (x % 3 != 0) res++;
        }
        return res;
    }
}