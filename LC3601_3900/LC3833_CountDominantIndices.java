package LC3601_3900;

public class LC3833_CountDominantIndices {
    /**
     * You are given an integer array nums of length n.
     *
     * An element at index i is called dominant if: nums[i] > average(nums[i + 1], nums[i + 2], ..., nums[n - 1])
     *
     * Your task is to count the number of indices i that are dominant.
     *
     * The average of a set of numbers is the value obtained by adding all the numbers together and dividing the sum by
     * the total number of numbers.
     *
     * Note: The rightmost element of any array is not dominant.
     *
     * Input: nums = [5,4,3]
     * Output: 2
     *
     * Input: nums = [4,1,2]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int dominantIndices(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = n - 2, s = nums[n - 1]; i >= 0; i--) {
            if (nums[i] > s / (n - 1 - i)) res++;
            s += nums[i];
        }
        return res;
    }
}