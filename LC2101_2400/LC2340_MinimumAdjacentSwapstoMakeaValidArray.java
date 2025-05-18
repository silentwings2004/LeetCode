package LC2101_2400;

public class LC2340_MinimumAdjacentSwapstoMakeaValidArray {
    /**
     * You are given a 0-indexed integer array nums.
     *
     * Swaps of adjacent elements are able to be performed on nums.
     *
     * A valid array meets the following conditions:
     *
     * The largest element (any of the largest elements if there are multiple) is at the rightmost position in the array.
     * The smallest element (any of the smallest elements if there are multiple) is at the leftmost position in the array.
     * Return the minimum swaps required to make nums a valid array.
     *
     * Input: nums = [3,4,5,5,3,1]
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int minimumSwaps(int[] nums) {
        int n = nums.length;
        int min = nums[0], max = nums[0], minIdx = 0, maxIdx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < min) {
                min = nums[i];
                minIdx = i;
            } else if (nums[i] >= max) {
                max = nums[i];
                maxIdx = i;
            }
        }

        if (minIdx == maxIdx) return 0;
        int res = minIdx + (n - 1 - maxIdx);
        return minIdx > maxIdx ? res - 1 : res;
    }
}
