package LC3601_3900;

public class LC3828_FinalElementAfterSubarrayDeletions {
    /**
     * You are given an integer array nums.
     *
     * Two players, Alice and Bob, play a game in turns, with Alice playing first.
     *
     * In each turn, the current player chooses any subarray nums[l..r] such that r - l + 1 < m, where m is the current
     * length of the array.
     * The selected subarray is removed, and the remaining elements are concatenated to form the new array.
     * The game continues until only one element remains.
     * Alice aims to maximize the final element, while Bob aims to minimize it. Assuming both play optimally, return
     * the value of the final remaining element.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,5,2]
     * Output: 2
     *
     * Input: nums = [3,7]
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(1), space = O(1)
    public int finalElement(int[] nums) {
        return Math.max(nums[0], nums[nums.length - 1]);
    }
}