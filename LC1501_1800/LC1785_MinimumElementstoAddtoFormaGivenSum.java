package LC1501_1800;

public class LC1785_MinimumElementstoAddtoFormaGivenSum {
    /**
     * You are given an integer array nums and two integers limit and goal. The array nums has an interesting property
     * that abs(nums[i]) <= limit.
     *
     * Return the minimum number of elements you need to add to make the sum of the array equal to goal. The array must
     * maintain its property that abs(nums[i]) <= limit.
     *
     * Note that abs(x) equals x if x >= 0, and -x otherwise.
     *
     * Input: nums = [1,-1,1], limit = 3, goal = -4
     * Output: 2
     *
     * Input: nums = [1,-10,9,1], limit = 100, goal = 0
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= limit <= 10^6
     * -limit <= nums[i] <= limit
     * -10^9 <= goal <= 10^9
     * @param nums
     * @param limit
     * @param goal
     * @return
     */
    // time = O(n), space = O(1)
    public int minElements(int[] nums, int limit, int goal) {
        long sum = 0;
        for (int x : nums) sum += x;
        return (int)((Math.abs(sum - goal) + limit - 1) / limit);
    }
}