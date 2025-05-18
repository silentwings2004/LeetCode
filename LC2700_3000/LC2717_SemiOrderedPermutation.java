package LC2700_3000;

public class LC2717_SemiOrderedPermutation {
    /**
     * You are given a 0-indexed permutation of n integers nums.
     *
     * A permutation is called semi-ordered if the first number equals 1 and the last number equals n. You can perform
     * the below operation as many times as you want until you make nums a semi-ordered permutation:
     *
     * Pick two adjacent elements in nums, then swap them.
     * Return the minimum number of operations to make nums a semi-ordered permutation.
     *
     * A permutation is a sequence of integers from 1 to n of length n containing each number exactly once.
     *
     * Input: nums = [2,1,4,3]
     * Output: 2
     *
     * Input: nums = [2,4,1,3]
     * Output: 3
     *
     * Input: nums = [1,3,4,2,5]
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= nums.length == n <= 50
     * 1 <= nums[i] <= 50
     * nums is a permutation.
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int semiOrderedPermutation(int[] nums) {
        int n = nums.length, a = -1, b = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) a = i;
            else if (nums[i] == n) b = i;
        }
        int res = a + (n - 1 - b);
        if (a > b) res--;
        return res;
    }
}