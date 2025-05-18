package LC001_300;
import java.util.*;
public class LC11_ContainerWithMostWater {
    /**
     * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical
     * lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines, which,
     * together with the x-axis forms a container, such that the container contains the most water.
     *
     * Input: height = [1,8,6,2,5,4,8,3,7]
     * Output: 49
     *
     * Input: height = [1,1]
     * Output: 1
     *
     * Constraints:
     *
     * n == height.length
     * 2 <= n <= 10^5
     * 0 <= height[i] <= 10^4
     *
     * @param height
     * @return
     */
    // time = O(n), space = O(1)
    public int maxArea(int[] height) {
        int n = height.length, res = 0;
        int l = 0, r = n - 1;
        while (l < r) {
            if (height[l] < height[r]) {
                res = Math.max(res, height[l] * (r - l));
                l++;
            } else {
                res = Math.max(res, height[r] * (r - l));
                r--;
            }
        }
        return res;
    }
}