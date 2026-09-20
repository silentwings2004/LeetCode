package LC3901_4200;

public class LC4044_CountGoodCyclicRotations {
    /**
     * You are given an integer array nums of even length n.
     *
     * A cyclic rotation of nums is obtained by choosing a prefix of nums whose length is between 0 and n - 1
     * (inclusive), and moving it to the end of the array while preserving the order of all elements.
     *
     * A cyclic rotation is good if the sum of its first n / 2 elements is strictly greater than the sum of its last
     * n / 2 elements.
     *
     * Return the number of cyclic rotations of nums that are good.
     *
     * A prefix of an array is a subarray that starts from the beginning of the array and extends to any point within it.
     *
     * A subarray is a contiguous sequence of elements within an array, which may be empty.
     *
     * Input: nums = [1,2,3,4,5,6]
     * Output: 3
     *
     * Input: nums = [1,2,1,2]
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= n == nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * n is even.
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int countGoodRotations(int[] nums) {
        int n = nums.length, m = n / 2;
        long a = 0, b = 0;
        for (int i = 0; i < m; i++) a += nums[i];
        for (int i = m ; i < n; i++) b += nums[i];

        int res = a > b ? 1 : 0;
        for (int i = 0; i < n - 1; i++) {
            int x = nums[(i + m) % n], y = nums[i];
            a = a - y + x;
            b = b + y - x;
            if (a > b) res++;
        }
        return res;
    }
}