package LC001_300;
import java.util.*;
public class LC75_SortColors {
    /**
     * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same
     * color are adjacent, with the colors in the order red, white, and blue.
     *
     * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
     *
     * Input: nums = [2,0,2,1,1,0]
     * Output: [0,0,1,1,2,2]
     *
     * Constraints:
     *
     * n == nums.length
     * 1 <= n <= 300
     * nums[i] is 0, 1, or 2.
     *
     *
     * Follow up:
     *
     * Could you solve this problem without using the library's sort function?
     * Could you come up with a one-pass algorithm using only O(1) constant space?
     */
    // time = O(n), space = O(1)
    public void sortColors(int[] nums) {
        int n = nums.length;
        int i = 0, j = n - 1, t = i;
        while (t <= j) {
            if (nums[t] == 0) swap(nums, t++, i++);
            else if (nums[t] == 2) swap(nums, t, j--);
            else t++;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    // S2: quick sort
    // time = O(nlogn), space = O(1)
    public void sortColors2(int[] nums) {
        quick_sort(nums, 0, nums.length - 1);
    }

    private void quick_sort(int[] q, int l, int r) {
        if (l >= r) return;

        int x = q[l + r >> 1], i = l - 1, j = r + 1;
        while (i < j) {
            while (q[++i] < x);
            while (q[--j] > x);
            if (i < j) {
                int t = q[i];
                q[i] = q[j];
                q[j] = t;
            }
        }
        quick_sort(q, l, j);
        quick_sort(q, j + 1, r);
    }
}