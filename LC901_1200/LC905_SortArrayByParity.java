package LC901_1200;
import java.util.*;
public class LC905_SortArrayByParity {
    /**
     * Given an array nums of non-negative integers, return an array consisting of all the even elements of nums,
     * followed by all the odd elements of nums.
     *
     * You may return any answer array that satisfies this condition.
     *
     * Input: nums = [3,1,2,4]
     * Output: [2,4,3,1]
     *
     * Note:
     *
     * 1 <= nums.length <= 5000
     * 0 <= nums[i] <= 5000
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int[] sortArrayByParity(int[] nums) {
        int n = nums.length;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            while (i < j && nums[i] % 2 == 0) i++;
            while (i < j && nums[j] % 2 == 1) j--;
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
        return nums;
    }
}