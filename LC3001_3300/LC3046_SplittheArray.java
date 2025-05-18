package LC3001_3300;

public class LC3046_SplittheArray {
    /**
     * You are given an integer array nums of even length. You have to split the array into two parts nums1 and nums2
     * such that:
     *
     * nums1.length == nums2.length == nums.length / 2.
     * nums1 should contain distinct elements.
     * nums2 should also contain distinct elements.
     * Return true if it is possible to split the array, and false otherwise.
     *
     * Input: nums = [1,1,2,2,3,4]
     * Output: true
     *
     * Input: nums = [1,1,1,1]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * nums.length % 2 == 0
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public boolean isPossibleToSplit(int[] nums) {
        int[] cnt = new int[101];
        for (int x : nums) {
            cnt[x]++;
            if (cnt[x] > 2) return false;
        }
        return true;
    }
}