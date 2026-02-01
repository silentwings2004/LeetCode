package LC3001_3300;

public class LC3010_DivideanArrayIntoSubarraysWithMinimumCostI {
    /**
     * You are given an array of integers nums of length n.
     *
     * The cost of an array is the value of its first element. For example, the cost of [1,2,3] is 1 while the cost of
     * [3,4,1] is 3.
     *
     * You need to divide nums into 3 disjoint contiguous
     * subarrays.
     *
     * Return the minimum possible sum of the cost of these subarrays.
     *
     * Input: nums = [1,2,3,12]
     * Output: 6
     *
     * Input: nums = [5,4,3]
     * Output: 12
     *
     * Input: nums = [10,3,1,1]
     * Output: 12
     *
     * Constraints:
     *
     * 3 <= n <= 50
     * 1 <= nums[i] <= 50
     * @param nums
     * @return
     */
    // S1
    // time = O(n^2), space = O(1)
    public int minimumCost(int[] nums) {
        int n = nums.length, res = 150;
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                res = Math.min(res, nums[0] + nums[i] + nums[j]);
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int minimumCost2(int[] nums) {
        int n = nums.length, a = Integer.MAX_VALUE, b = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int x = nums[i];
            if (x < a) {
                b = a;
                a = x;
            } else if (x < b) b = x;
        }
        return nums[0] + a + b;
    }
}