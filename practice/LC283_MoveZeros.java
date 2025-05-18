package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: MoveZeros
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 283. Move Zeroes
 */
public class LC283_MoveZeros {
    /**
     * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of
     * the non-zero elements.
     *
     * Example:
     *
     * Input: [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     * Note:
     *
     * You must do this in-place without making a copy of the array.
     * Minimize the total number of operations.
     */
    // S1: 补0
    // time = O(n), space = O(1)
    public void moveZeroes(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) return;

        int index = 0; // 用一个index来指向所有值=0的元素准备替换
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }
        for (int i = index; i < nums.length; i++) { // 将最后剩余的数组位置全部补0
            nums[i] = 0;
    }
    }

    // S2: Swap 1 --> 将S1的补0用swap代替
    // time = O(n), space = O(1)
    public void moveZeroes2(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                swap(nums, slow++, fast);  //同样是运用S1当中的算法，只是将原本前移补0改变成swap的方式
            }
        }
    }

    private void swap(int[] nums, int slow, int fast) {
        int temp = nums[slow];
        nums[slow] = nums[fast];
        nums[fast] = temp;
    }

    // S3: Swap 2 --> slow / fast pointers [two pointers]
    // time = O(n), space = O(1)
    public void moveZeroes3(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) return;

        int slow = 0;
        while (slow < nums.length && nums[slow] != 0) slow++; // 首先将slow移动到第一个非0的位置，ready for swap
        int fast = slow; // 将fast指针放在与slow相同的起点上

        while (fast < nums.length) {
            if (nums[fast] == 0) fast++; // 固定slow不动，fast向后移动直到到达第一个值=0的元素准备swap
            // 注意这里不能把if替换成while (nums[fast] == 0) fast++，因为可能存在fast移动出界的情况，
            // 每移动一次fast，还是要check一次是否出界
            else if (nums[slow] == 0) swap(nums, slow, fast);
            else slow++; // slow总是在fast之前，不可能超越fast，所以slow在这里不用check是否会出界
        }
    }
}
