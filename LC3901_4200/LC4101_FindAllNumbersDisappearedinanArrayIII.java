package LC3901_4200;
import java.util.*;
public class LC4101_FindAllNumbersDisappearedinanArrayIII {
    /**
     * You are given an integer array nums and two integers lower and upper.
     *
     * An integer is missing if it lies in the inclusive range [lower, upper] and does not appear in nums.
     *
     * Return a 2D integer array representing all missing integers grouped into contiguous ranges. Represent each range
     * as [start, end], including both endpoints, and return the ranges in increasing order of start.
     *
     * Consecutive missing integers must belong to the same range. If there are no missing integers, return an empty
     * array.
     *
     * Input: nums = [3,9,7], lower = 1, upper = 12
     * Output: [[1,2],[4,6],[8,8],[10,12]]
     *
     * Input: nums = [1,1,1000000000], lower = 5, upper = 1000000000
     * Output: [[5,999999999]]
     *
     * Input: nums = [2,3,5], lower = 2, upper = 3
     * Output: []
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= lower <= upper <= 10^9
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int[][] findDisappearedNumbers(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        List<int[]> q = new ArrayList<>();
        int v = lower;
        for (int x : nums) {
            if (x < v) continue;
            if (x == v) {
                v++;
                continue;
            }
            x = Math.min(x, upper + 1);
            if (v > x - 1) break;
            q.add(new int[]{v, x - 1});
            v = x + 1;
        }
        if (v <= upper) q.add(new int[]{v, upper});
        int m = q.size();
        int[][] res = new int[m][2];
        for (int i = 0; i < m; i++) res[i] = q.get(i);
        return res;
    }
}