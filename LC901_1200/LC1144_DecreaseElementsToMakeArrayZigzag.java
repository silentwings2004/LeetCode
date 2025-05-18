package LC901_1200;

public class LC1144_DecreaseElementsToMakeArrayZigzag {
    /**
     * Given an array nums of integers, a move consists of choosing any element and decreasing it by 1.
     *
     * An array A is a zigzag array if either:
     *
     * Every even-indexed element is greater than adjacent elements, ie. A[0] > A[1] < A[2] > A[3] < A[4] > ...
     * OR, every odd-indexed element is greater than adjacent elements, ie. A[0] < A[1] > A[2] < A[3] > A[4] < ...
     * Return the minimum number of moves to transform the given array nums into a zigzag array.
     *
     * Input: nums = [1,2,3]
     * Output: 2
     *
     * Input: nums = [9,6,1,6,2]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 1000
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int movesToMakeZigzag(int[] nums) {
        return Math.min(work(nums, 0), work(nums, 1));
    }

    private int work(int[] nums, int start) {
        int res = 0, n = nums.length;
        for (int i = start; i < n; i += 2) {
            int t = nums[i];
            if (i - 1 >= 0) t = Math.min(t, nums[i - 1] - 1);
            if (i + 1 < n) t = Math.min(t, nums[i + 1] - 1);
            res += nums[i] - t;
        }
        return res;
    }
}
/**
 * 如果最优解是波峰的话，我们最优解一定不会减奇数位置的数
 * 每个偶数位置减到两个奇数的最小值-1
 */