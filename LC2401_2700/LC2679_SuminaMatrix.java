package LC2401_2700;
import java.util.*;
public class LC2679_SuminaMatrix {
    /**
     * You are given a 0-indexed 2D integer array nums. Initially, your score is 0. Perform the following operations
     * until the matrix becomes empty:
     *
     * From each row in the matrix, select the largest number and remove it. In the case of a tie, it does not matter
     * which number is chosen.
     * Identify the highest number amongst all those removed in step 1. Add that number to your score.
     * Return the final score.
     *
     * Input: nums = [[7,2,1],[6,4,2],[6,5,3],[3,2,1]]
     * Output: 15
     *
     * Input: nums = [[1]]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 300
     * 1 <= nums[i].length <= 500
     * 0 <= nums[i][j] <= 10^3
     * @param nums
     * @return
     */
    // time = O(m * nlogn), space = O(logn);
    public int matrixSum(int[][] nums) {
        int m = nums.length, n = nums[0].length, res = 0;
        for (int[] x : nums) Arrays.sort(x);
        for (int j = 0; j < n; j++) {
            int t = 0;
            for (int i = 0; i < m; i++) {
                t = Math.max(t, nums[i][j]);
            }
            res += t;
        }
        return res;
    }
}