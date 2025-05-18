package LC3301_3600;

public class LC3452_SumofGoodNumbers {
    /**
     * Given an array of integers nums and an integer k, an element nums[i] is considered good if it is strictly greater
     * than the elements at indices i - k and i + k (if those indices exist). If neither of these indices exists,
     * nums[i] is still considered good.
     *
     * Return the sum of all the good elements in the array.
     *
     * Input: nums = [1,3,2,1,5,4], k = 2
     * Output: 12
     *
     * Input: nums = [2,1], k = 1
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= nums.length <= 100
     * 1 <= nums[i] <= 1000
     * 1 <= k <= floor(nums.length / 2)
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int sumOfGoodNumbers(int[] nums, int k) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            if (i - k >= 0 && nums[i] <= nums[i - k]) continue;
            if (i + k < n && nums[i] <= nums[i + k]) continue;
            res += nums[i];
        }
        return res;
    }
}