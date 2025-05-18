package LC3001_3300;

public class LC3192_MinimumOperationstoMakeBinaryArrayElementsEqualtoOneII {
    /**
     * You are given a binary array nums.
     *
     * You can do the following operation on the array any number of times (possibly zero):
     *
     * Choose any index i from the array and flip all the elements from index i to the end of the array.
     * Flipping an element means changing its value from 0 to 1, and from 1 to 0.
     *
     * Return the minimum number of operations required to make all elements in nums equal to 1.
     *
     * Input: nums = [0,1,1,0,1]
     * Output: 4
     *
     * Input: nums = [1,0,0,0]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 1
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int minOperations(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 0, s = 0; i < n; i++) {
            int v = (nums[i] + s) % 2;
            if (v == 0) {
                s++;
                res++;
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int minOperations2(int[] nums) {
        int res = 0;
        for (int x : nums) {
            if (x == res % 2) res++;
        }
        return res;
    }
}