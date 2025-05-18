package LC901_1200;
import java.util.*;
public class  LC976_LargestPerimeterTriangle {
    /**
     * Given an integer array nums, return the largest perimeter of a triangle with a non-zero area, formed from three
     * of these lengths. If it is impossible to form any triangle of a non-zero area, return 0.
     *
     * Input: nums = [2,1,2]
     * Output: 5
     *
     * Input: nums = [1,2,1]
     * Output: 0
     *
     * Constraints:
     *
     * 3 <= nums.length <= 10^4
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = n - 1; i >= 2; i--) {
            int a = nums[i - 2], b = nums[i - 1], c = nums[i];
            if (a + b > c) return a + b + c;
        }
        return 0;
    }
}