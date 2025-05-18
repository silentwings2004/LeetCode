package LC2401_2700;

public class LC2475_NumberofUnequalTripletsinArray {
    /**
     * You are given a 0-indexed array of positive integers nums. Find the number of triplets (i, j, k) that meet the
     * following conditions:
     *
     * 0 <= i < j < k < nums.length
     * nums[i], nums[j], and nums[k] are pairwise distinct.
     * In other words, nums[i] != nums[j], nums[i] != nums[k], and nums[j] != nums[k].
     * Return the number of triplets that meet the conditions.
     *
     * Input: nums = [4,4,2,4,3]
     * Output: 3
     *
     * Input: nums = [1,1,1,1,1]
     * Output: 0
     *
     * Constraints:
     *
     * 3 <= nums.length <= 100
     * 1 <= nums[i] <= 1000
     * @param nums
     * @return
     */
    // time = O(n^3), space = O(1)
    public int unequalTriplets(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] != nums[j] && nums[j] != nums[k] && nums[k] != nums[i]) res++;
                }
            }
        }
        return res;
    }
}
