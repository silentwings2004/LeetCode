package LC2700_3000;
import java.util.*;
public class LC2971_FindPolygonWiththeLargestPerimeter {
    /**
     * You are given an array of positive integers nums of length n.
     *
     * A polygon is a closed plane figure that has at least 3 sides. The longest side of a polygon is smaller than the
     * sum of its other sides.
     *
     * Conversely, if you have k (k >= 3) positive real numbers a1, a2, a3, ..., ak where a1 <= a2 <= a3 <= ... <= ak
     * and a1 + a2 + a3 + ... + ak-1 > ak, then there always exists a polygon with k sides whose lengths are a1, a2,
     * a3, ..., ak.
     *
     * The perimeter of a polygon is the sum of lengths of its sides.
     *
     * Return the largest possible perimeter of a polygon whose sides can be formed from nums, or -1 if it is not
     * possible to create a polygon.
     *
     * Input: nums = [5,5,5]
     * Output: 15
     *
     * Input: nums = [1,12,1,2,5,50,3]
     * Output: 12
     *
     * Input: nums = [5,5,50]
     * Output: -1
     *
     * Constraints:
     *
     * 3 <= n <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public long largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        long s = 0;
        for (int x : nums) s += x;
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            if (s - nums[i] > nums[i]) return s;
            s -= nums[i];
        }
        return -1;
    }
}