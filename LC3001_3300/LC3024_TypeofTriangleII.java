package LC3001_3300;
import java.util.*;
public class LC3024_TypeofTriangleII {
    /**
     * You are given a 0-indexed integer array nums of size 3 which can form the sides of a triangle.
     *
     * A triangle is called equilateral if it has all sides of equal length.
     * A triangle is called isosceles if it has exactly two sides of equal length.
     * A triangle is called scalene if all its sides are of different lengths.
     * Return a string representing the type of triangle that can be formed or "none" if it cannot form a triangle.
     *
     * Input: nums = [3,3,3]
     * Output: "equilateral"
     *
     * Input: nums = [3,4,5]
     * Output: "scalene"
     *
     * Constraints:
     *
     * nums.length == 3
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(1), space = O(1)
    public String triangleType(int[] nums) {
        int a = nums[0], b = nums[1], c = nums[2];
        if (a + b <= c || a + c <= b || b + c <= a) return "none";
        if (a == b && b == c && c == a) return "equilateral";
        if (a == b || b == c || c == a) return "isosceles";
        return "scalene";
    }
}