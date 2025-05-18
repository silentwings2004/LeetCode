package LC3001_3300;

public class LC3191_MinimumOperationstoMakeBinaryArrayElementsEqualtoOneI {
    /**
     * You are given a binary array nums.
     *
     * You can do the following operation on the array any number of times (possibly zero):
     *
     * Choose any 3 consecutive elements from the array and flip all of them.
     * Flipping an element means changing its value from 0 to 1, and from 1 to 0.
     *
     * Return the minimum number of operations required to make all elements in nums equal to 1. If it is impossible,
     * return -1.
     *
     * Input: nums = [0,1,1,1,0,0]
     *
     * Output: 3
     *
     * Input: nums = [0,1,1,1]
     *
     * Output: -1
     *
     * Constraints:
     *
     * 3 <= nums.length <= 10^5
     * 0 <= nums[i] <= 1
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int minOperations(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                res++;
                if (i + 2 < n) {
                    nums[i] ^= 1;
                    nums[i + 1] ^= 1;
                    nums[i + 2] ^= 1;
                } else return -1;
            }
        }
        return res;
    }
}