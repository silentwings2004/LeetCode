package LC3301_3600;

public class LC3432_CountPartitionswithEvenSumDifference {
    /**
     * You are given an integer array nums of length n.
     *
     * A partition is defined as an index i where 0 <= i < n - 1, splitting the array into two non-empty subarrays such
     * that:
     *
     * Left subarray contains indices [0, i].
     * Right subarray contains indices [i + 1, n - 1].
     * Return the number of partitions where the difference between the sum of the left and right subarrays is even.
     *
     * Input: nums = [10,10,3,7,6]
     * Output: 4
     *
     * Input: nums = [1,2,2]
     * Output: 0
     *
     * Input: nums = [2,4,6,8]
     * Output: 3
     *
     * Constraints:
     *
     * 2 <= n == nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int countPartitions(int[] nums) {
        int n = nums.length, res = 0;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];
        for (int i = 1; i < n; i++) {
            int d = s[i] - (s[n] - s[i]);
            if (d % 2 == 0) res++;
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int countPartitions2(int[] nums) {
        int n = nums.length, s = 0;
        for (int x : nums) s += x;
        return s % 2 == 1 ? 0 : n - 1;
    }
}