package LC601_900;

public class LC747_LargestNumberAtLeastTwiceofOthers {
    /**
     * You are given an integer array nums where the largest integer is unique.
     *
     * Determine whether the largest element in the array is at least twice as much as every other number in the array.
     * If it is, return the index of the largest element, or return -1 otherwise.
     *
     * Input: nums = [3,6,1,0]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 50
     * 0 <= nums[i] <= 100
     * The largest element in nums is unique.
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int dominantIndex(int[] nums) {
        int n = nums.length, max = 0, second = 0, res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                second = max;
                max = nums[i];
                res = i;
            } else if (nums[i] > second) second = nums[i];
        }
        return max >= second * 2 ? res : -1;
    }

    // S2
    // time = O(n), space = O(1)
    public int dominantIndex2(int[] nums) {
        int n = nums.length, k = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > nums[k]) k = i;
        }

        for (int i = 0; i < n; i++) {
            if (i != k && nums[k] < nums[i] * 2) return -1;
        }
        return k;
    }
}
