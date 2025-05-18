package LC2700_3000;
import java.util.*;
public class LC2779_MaximumBeautyofanArrayAfterApplyingOperation {
    /**
     * You are given a 0-indexed array nums and a non-negative integer k.
     *
     * In one operation, you can do the following:
     *
     * Choose an index i that hasn't been chosen before from the range [0, nums.length - 1].
     * Replace nums[i] with any integer from the range [nums[i] - k, nums[i] + k].
     * The beauty of the array is the length of the longest subsequence consisting of equal elements.
     *
     * Return the maximum possible beauty of the array nums after applying the operation any number of times.
     *
     * Note that you can apply the operation to each index only once.
     *
     * A subsequence of an array is a new array generated from the original array by deleting some elements (possibly
     * none) without changing the order of the remaining elements.
     *
     * Input: nums = [4,6,1,2], k = 2
     * Output: 3
     *
     * Input: nums = [1,1,1,1], k = 10
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i], k <= 10^5
     * @param nums
     * @param k
     * @return
     */
    // S1: diff array
    // time = O(n), space = O(n)
    public int maximumBeauty(int[] nums, int k) {
        int[] b = new int[300010];
        int offset = 100000;
        for (int x : nums) {
            b[x - k + offset]++;
            b[x + k + 1 + offset]--;
        }
        int res = 0, t = 0;
        for (int i = 0; i < 300010; i++) {
            t += b[i];
            res = Math.max(res, t);
        }
        return res;
    }

    // S2: two pointers
    // time = O(nlogn), space = O(logn)
    public int maximumBeauty2(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            while (j < n && nums[j] - nums[i] <= k * 2) j++;
            res = Math.max(res, j - i);
        }
        return res;
    }
}