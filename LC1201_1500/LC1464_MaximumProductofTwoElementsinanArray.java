package LC1201_1500;

public class LC1464_MaximumProductofTwoElementsinanArray {
    /**
     * Given the array of integers nums, you will choose two different indices i and j of that array. Return the
     * maximum value of (nums[i]-1)*(nums[j]-1).
     *
     * Input: nums = [3,4,5,2]
     * Output: 12
     *
     * Constraints:
     *
     * 2 <= nums.length <= 500
     * 1 <= nums[i] <= 10^3
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int maxProduct(int[] nums) {
        int a = nums[0], b = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= a) {
                b = a;
                a = nums[i];
            } else if (nums[i] > b) b = nums[i];
        }
        return (a - 1) * (b - 1);
    }
}