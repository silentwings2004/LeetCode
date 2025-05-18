package LC901_1200;

public class LC912_SortanArray {
    /**
     * Given an array of integers nums, sort the array in ascending order and return it.
     *
     * You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the
     * smallest space complexity possible.
     *
     * Input: nums = [5,2,3,1]
     * Output: [1,2,3,5]
     *
     * Input: nums = [5,1,1,2,0,0]
     * Output: [0,0,1,1,2,5]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5 * 10^4
     * -5 * 10^4 <= nums[i] <= 5 * 10^4
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int[] sortArray(int[] nums) {
        quick_sort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quick_sort(int[] q, int l, int r) {
        if (l >= r) return;
        int i = l - 1, j = r + 1, x = q[l + r >> 1];
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