package LC301_600;

public class LC485_MaxConsecutiveOnes {
    /**
     * Given a binary array nums, return the maximum number of consecutive 1's in the array.
     *
     * Input: nums = [1,1,0,1,1,1]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * nums[i] is either 0 or 1.
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                int j = i + 1;
                while (j < n && nums[j] == 1) j++;
                res = Math.max(res, j - i);
                i = j; // 此时j一定指向0
            }
        }
        return res;
    }
}