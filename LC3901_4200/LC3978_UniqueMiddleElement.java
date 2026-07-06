package LC3901_4200;

public class LC3978_UniqueMiddleElement {
    /**
     * You are given an integer array nums of odd length n.
     *
     * Return true if the middle element of nums appears exactly once in the array. Otherwise return false.
     *
     * Input: nums = [1,2,3]
     * Output: true
     *
     * Input: nums = [1,2,2]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 100
     * n is odd.
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public boolean isMiddleElementUnique(int[] nums) {
        int n = nums.length, cnt = 0;
        for (int x : nums) {
            if (x == nums[n / 2]) cnt++;
        }
        return cnt == 1;
    }
}