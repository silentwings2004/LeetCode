package LC2700_3000;
import java.util.*;
public class LC2966_DivideArrayIntoArraysWithMaxDifference {
    /**
     * You are given an integer array nums of size n and a positive integer k.
     *
     * Divide the array into one or more arrays of size 3 satisfying the following conditions:
     *
     * Each element of nums should be in exactly one array.
     * The difference between any two elements in one array is less than or equal to k.
     * Return a 2D array containing all the arrays. If it is impossible to satisfy the conditions, return an empty
     * array. And if there are multiple answers, return any of them.
     *
     * Input: nums = [1,3,4,8,7,9,3,5,1], k = 2
     * Output: [[1,1,3],[3,4,5],[7,8,9]]
     *
     * Input: nums = [1,3,3,2,7,3], k = 3
     * Output: []
     *
     * Constraints:
     *
     * n == nums.length
     * 1 <= n <= 10^5
     * n is a multiple of 3.
     * 1 <= nums[i] <= 10^5
     * 1 <= k <= 10^5
     * @param nums
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int[][] res = new int[n / 3][3];
        for (int i = 0, j = 0; i < n; i += 3, j++) {
            int a = nums[i], b = nums[i + 1], c = nums[i + 2];
            if (c - a <= k) res[j] = new int[]{a, b, c};
            else return new int[0][0];
        }
        return res;
    }
}