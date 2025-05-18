package LC2700_3000;
import java.util.*;
public class LC2848_PointsThatIntersectWithCars {
    /**
     * You are given a 0-indexed 2D integer array nums representing the coordinates of the cars parking on a number
     * line. For any index i, nums[i] = [starti, endi] where starti is the starting point of the ith car and endi is
     * the ending point of the ith car.
     *
     * Return the number of integer points on the line that are covered with any part of a car.
     *
     * Input: nums = [[3,6],[1,5],[4,7]]
     * Output: 7
     *
     * Input: nums = [[1,3],[5,8]]
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * nums[i].length == 2
     * 1 <= starti <= endi <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 110;
    public int numberOfPoints(List<List<Integer>> nums) {
        boolean[] st = new boolean[N];
        for (List<Integer> x : nums) {
            int a = x.get(0), b = x.get(1);
            for (int i = a; i <= b; i++) st[i] = true;
        }
        int res = 0;
        for (int i = 1; i <= 100; i++) {
            if (st[i]) res++;
        }
        return res;
    }
}