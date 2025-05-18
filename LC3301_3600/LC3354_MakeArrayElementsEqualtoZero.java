package LC3301_3600;

public class LC3354_MakeArrayElementsEqualtoZero {
    /**
     * You are given an integer array nums.
     *
     * Start by selecting a starting position curr such that nums[curr] == 0, and choose a movement direction of either
     * left or right.
     *
     * After that, you repeat the following process:
     *
     * If curr is out of the range [0, n - 1], this process ends.
     * If nums[curr] == 0, move in the current direction by incrementing curr if you are moving right, or decrementing
     * curr if you are moving left.
     * Else if nums[curr] > 0:
     * Decrement nums[curr] by 1.
     * Reverse your movement direction (left becomes right and vice versa).
     * Take a step in your new direction.
     * A selection of the initial position curr and movement direction is considered valid if every element in nums
     * becomes 0 by the end of the process.
     *
     * Return the number of possible valid selections.
     *
     * Input: nums = [1,0,2,0,3]
     * Output: 2
     *
     * Input: nums = [2,3,4,0,4,1,0]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 100
     * There is at least one element i where nums[i] == 0.
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int countValidSelections(int[] nums) {
        int n = nums.length, res = 0;
        int s = 0;
        for (int x : nums) s += x;
        int ls = 0;
        for (int i = 0; i < n; i++) {
            ls += nums[i];
            if (nums[i] == 0) {
                int rs = s - ls;
                if (ls == rs) res += 2;
                else if (Math.abs(ls - rs) == 1) res++;
            }
        }
        return res;
    }
}