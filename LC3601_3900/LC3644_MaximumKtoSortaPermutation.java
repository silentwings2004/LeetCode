package LC3601_3900;

public class LC3644_MaximumKtoSortaPermutation {
    /**
     * You are given an integer array nums of length n, where nums is a permutation of the numbers in the range
     * [0..n - 1].
     *
     * You may swap elements at indices i and j only if nums[i] AND nums[j] == k, where AND denotes the bitwise AND
     * operation and k is a non-negative integer.
     *
     * Return the maximum value of k such that the array can be sorted in non-decreasing order using any number of such
     * swaps. If nums is already sorted, return 0.
     *
     * A permutation is a rearrangement of all the elements of an array.
     *
     * Input: nums = [0,3,2,1]
     * Output: 1
     *
     * Input: nums = [0,1,3,2]
     * Output: 2
     *
     * Input: nums = [3,2,1,0]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * 0 <= nums[i] <= n - 1
     * nums is a permutation of integers from 0 to n - 1.
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int sortPermutation(int[] nums) {
        if (nums[0] != 0) return 0;
        int n = nums.length, res = -1;
        for (int i = 0; i < n; i++) {
            if (i != nums[i]) res &= nums[i];
        }
        return res == -1 ? 0 : res;
    }
}
/**
 * 借助中介 0 完成任意次数的交换实现排序
 * 0 3 1
 * 3 0 1
 * 3 1 0
 * 0 1 3
 * 0 的位置在参与交换之前和之后没有发生变化
 * 把所有需要交换的数字 AND 起来得到 v,
 * 那么就可以按照这种方式类似上述 0 的操作，把 v 作为中介最终实现所有需要调整的元素实现排序
 */