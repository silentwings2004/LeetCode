package LC3601_3900;
import java.util.*;
public class LC3667_SortArrayByAbsoluteValue {
    /**
     * You are given an integer array nums.
     *
     * Rearrange elements of nums in non-decreasing order of their absolute value.
     *
     * Return any rearranged array that satisfies this condition.
     *
     * Note: The absolute value of an integer x is defined as:
     *
     * x if x >= 0
     * -x if x < 0
     *
     * Input: nums = [3,-1,-4,1,5]
     * Output: [-1,1,3,-4,5]
     *
     * Input: nums = [-100,100]
     * Output: [-100,100]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * -100 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int[] sortByAbsoluteValue(int[] nums) {
        int n = nums.length;
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) a[i] = nums[i];
        Arrays.sort(a, (o1, o2) -> Math.abs(o1) - Math.abs(o2));
        for (int i = 0; i < n; i++) nums[i] = a[i];
        return nums;
    }
}
