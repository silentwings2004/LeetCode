package LC301_600;
import java.util.*;
public class LC581_ShortestUnsortedContinuousSubarray {
    /**
     * Given an integer array nums, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order.
     *
     * Return the shortest such subarray and output its length.
     *
     * Input: nums = [2,6,4,8,10,9,15]
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^4
     * -10^5 <= nums[i] <= 10^5
     *
     *
     * Follow up: Can you solve it in O(n) time complexity?
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space - O(1)
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int max = nums[0], r = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < max) r = i;
            max = Math.max(max, nums[i]);
        }

        int min = nums[n - 1], l = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] > min) l = i;
            min = Math.min(min, nums[i]);
        }
        return r > l ? r - l + 1 : 0;
    }

    // S2
    // time = O(n), space - O(1)
    public int findUnsortedSubarray2(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l + 1 < n && nums[l] <= nums[l + 1]) l++;
        if (l == r) return 0;
        while (r - 1 >= 0 && nums[r - 1] <= nums[r]) r--;
        for (int i = l + 1; i < n; i++) {
            while (l >= 0 && nums[l] > nums[i]) l--;
        }
        for (int i = r - 1; i >= 0; i--) {
            while (r < n && nums[r] < nums[i]) r++;
        }
        return r - l - 1;
    }
}
/**
 * 1. 从小到大排序
 * 2. 左边最后一个元素 <= 右侧最小值
 *
 * 找出这样一个right_bound：位于该数左边的所有数的最大值，要比这个数大，这说明这个数在排序后需要变换位置。
 * 怎么找呢？将数组从左到右过一遍，实时保存最大值即可，不断刷新这个right_bound。
 * 同理，找到这样一个left_bound：位于该数右边的所有数的最小值，要比这个数小，这说明这个数在排序后需要变换位置。
 * 也是讲数组从右往左过一遍，实时保存最小值。
 * 最后当right_bound>left_bound时，结果就是 right_bound-left_bound+1
 *
 * 左右两边是完全独立的
 * 左边区间满足2个性质：
 * 1.从小到大排序
 * 2 左边最后一个元素 <= 右侧最小值
 * 在左侧找到一个最大的小于等于右侧最小值的一个位置
 */