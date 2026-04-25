package LC3901_4200;

public class LC3909_CompareSumsofBitonicParts {
    /**
     * You are given a bitonic array nums of length n.
     *
     * Split the array into two parts:
     *
     * Ascending part: from index 0 to the peak element (inclusive).
     * Descending part: from the peak element to index n - 1 (inclusive).
     * The peak element belongs to both parts.
     *
     * Return:
     *
     * 0 if the sum of the ascending part is greater.
     * 1 if the sum of the descending part is greater.
     * -1 if both sums are equal.
     * Notes:
     *
     * A bitonic array is an array that is strictly increasing up to a single peak element and then strictly decreasing.
     * An array is said to be strictly increasing if each element is strictly greater than its previous one (if exists).
     * An array is said to be strictly decreasing if each element is strictly smaller than its previous one (if exists).
     *
     * Input: nums = [1,3,2,1]
     * Output: 1
     *
     * Input: nums = [2,4,5,2]
     * Output: 0
     *
     * Input: nums = [1,2,4,3]
     * Output: -1
     *
     * Constraints:
     *
     * 3 <= n == nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * nums is a bitonic array.
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int compareBitonicSums(int[] nums) {
        int n = nums.length, i = 1;
        long s1 = nums[0];
        while (i < n && nums[i] > nums[i - 1]) s1 += nums[i++];
        long s2 = nums[i - 1];
        while (i < n && nums[i] < nums[i - 1]) s2 += nums[i++];
        return s1 > s2 ? 0 : (s1 < s2 ? 1 : -1);
    }
}