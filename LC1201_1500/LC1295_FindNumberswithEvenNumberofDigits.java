package LC1201_1500;

public class LC1295_FindNumberswithEvenNumberofDigits {
    /**
     * Given an array nums of integers, return how many of them contain an even number of digits.
     *
     * Input: nums = [12,345,2,6,7896]
     * Output: 2
     *
     * Input: nums = [555,901,482,1771]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 500
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(nlogk), space = O(1)
    public int findNumbers(int[] nums) {
        int res = 0;
        for (int x : nums) {
            int cnt = 0;
            while (x > 0) {
                int t = x % 10;
                x /= 10;
                cnt++;
            }
            if (cnt % 2 == 0) res++;
        }
        return res;
    }
}