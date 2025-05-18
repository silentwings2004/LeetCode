package LC901_1200;

public class LC1060_MissingElementinSortedArray {
    /**
     * Given an integer array nums which is sorted in ascending order and all of its elements are unique and given also
     * an integer k, return the kth missing number starting from the leftmost number of the array.
     *
     * Input: nums = [4,7,9,10], k = 1
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5 * 10^4
     * 1 <= nums[i] <= 10^7
     * nums is sorted in ascending order, and all the elements are unique.
     * 1 <= k <= 10^8
     * @param nums
     * @param k
     * @return
     */
    // time = O(logn), space = O(1)
    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            // [0, mid] 里缺失的数 < k 个，注意，这里不能取 = 否则缺失的数达到k个的话，证明第k个数在mid的左边!
            if (nums[mid] - nums[0] + 1 - (mid + 1) < k) l = mid;
            else r = mid - 1;
        }
        return nums[0] + k + r;
    }
}