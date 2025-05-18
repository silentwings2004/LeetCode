package LC1501_1800;

public class LC1512_NumberofGoodPairs {
    /**
     * Given an array of integers nums, return the number of good pairs.
     *
     * A pair (i, j) is called good if nums[i] == nums[j] and i < j.
     *
     * Input: nums = [1,2,3,1,1,3]
     * Output: 4
     *
     * Input: nums = [1,1,1,1]
     * Output: 6
     *
     * Input: nums = [1,2,3]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int numIdenticalPairs(int[] nums) {
        int[] cnt = new int[110];
        for (int x : nums) cnt[x]++;
        int res = 0;
        for (int i = 1; i <= 100; i++) {
            if (cnt[i] > 1) res += cnt[i] * (cnt[i] - 1) / 2;
        }
        return res;
    }
}