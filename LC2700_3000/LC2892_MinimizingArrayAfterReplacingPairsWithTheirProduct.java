package LC2700_3000;

public class LC2892_MinimizingArrayAfterReplacingPairsWithTheirProduct {
    /**
     * Given an integer array nums and an integer k, you can perform the following operation on the array any number of
     * times:
     *
     * Select two adjacent elements of the array like x and y, such that x * y <= k, and replace both of them with a
     * single element with value x * y (e.g. in one operation the array [1, 2, 2, 3] with k = 5 can become [1, 4, 3] or
     * [2, 2, 3], but can't become [1, 2, 6]).
     * Return the minimum possible length of nums after any number of operations.
     *
     * Input: nums = [2,3,3,7,3,5], k = 20
     * Output: 3
     *
     * Input: nums = [3,3,3,3], k = 6
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     * 1 <= k <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int minArrayLength(int[] nums, int k) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            long s = 1;
            while (j < n && s * nums[j] <= k) s *= nums[j++];
            if (s == 0) return 1;
            res++;
            i = Math.max(i, j - 1);
        }
        return res;
    }
}